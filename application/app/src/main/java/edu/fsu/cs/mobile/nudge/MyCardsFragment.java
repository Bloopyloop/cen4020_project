package edu.fsu.cs.mobile.nudge;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import java.util.ArrayList;

public class MyCardsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cards, container, false);

        RecyclerView rec =  (RecyclerView) view.findViewById(R.id.recyclerView);
        rec.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(llm);


        CardAdapter ca = new CardAdapter(createList(3));
        rec.setAdapter(ca);

        return view;


    }

    private List<Card> createList(int size){
        List<Card> result = new ArrayList<Card>();

        for(int i = 1; i <= size; ++i){
            Card card = new Card();

            card.cardTitle = "Test Card Title";
            card.displayName = "Display Name";
            card.cellNumber = "cellNumber";

            result.add(card);
        }

        return result;
    }

}
