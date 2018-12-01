package edu.fsu.cs.mobile.nudge;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseUser user;
    Card printCard;
    List<Card> listCards;
    RecyclerView rec;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        rec =  (RecyclerView) view.findViewById(R.id.contacts_recyclerView);
        rec.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(llm);

        listCards = new ArrayList<>();

        ContactsAdapter ca = new ContactsAdapter(createList(1));
        rec.setAdapter(ca);

        return view;
    }

    private List<Card> createList(int size){
        List<Card> result = new ArrayList<Card>();

        for(int i = 1; i <= size; ++i){
            Card card = new Card();

            card.cardTitle = "title";
            card.displayName = "name";
            card.cellNumber = "cell";
            card.homeNumber = "home";
            card.workNumber = "work";
            card.personalEmail = "email";
            card.workEmail = "work";
            card.website = "website";
            card.linkedIn = "linkedin";
            card.facebook = "facebook";
            card.twitter = "twitter";

            result.add(card);
        }

        return result;
    }

}
