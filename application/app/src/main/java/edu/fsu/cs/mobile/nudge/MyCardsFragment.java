package edu.fsu.cs.mobile.nudge;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import java.util.ArrayList;
import android.util.Log;
import android.widget.Toast;
import java.util.Map;
import java.util.HashMap;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;


public class MyCardsFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseUser user;
    Card printCard;
    List<Card> listCards;
    RecyclerView rec;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cards, container, false);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("cards");
        user = FirebaseAuth.getInstance().getCurrentUser();

        rec =  (RecyclerView) view.findViewById(R.id.recyclerView);
        rec.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(llm);

        listCards = new ArrayList<>();


        //CardAdapter ca = new CardAdapter(createList(1));
        //rec.setAdapter(ca);


        return view;


    }

    @Override
    public void onStart() {
        super.onStart();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listCards.clear();

                for (DataSnapshot cardSnapshot : dataSnapshot.getChildren()){
                    printCard = cardSnapshot.getValue(Card.class);

                    listCards.add(printCard);

                    CardAdapter ca = new CardAdapter(listCards);
                    rec.setAdapter(ca);
                }

                Log.i("onDataChanged",  listCards.get(0).cardTitle);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    /* test function */
    private List<Card> createList(int size){
        List<Card> result = new ArrayList<Card>();

        for(int i = 1; i <= size; ++i){
            Card card = new Card();

            card.cardTitle = "Test Card Title";
            card.displayName = "Empty";
            card.cellNumber = "cellNumber";

            result.add(card);
        }

        return result;
    }



}
