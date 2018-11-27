package edu.fsu.cs.mobile.nudge;

import android.support.v7.widget.RecyclerView;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Card> cardList;

    public CardAdapter(List<Card> cardList){
        this.cardList = cardList;
    }

    public int getItemCount() {
        return cardList.size();
    }

    public void onBindViewHolder(CardViewHolder cardViewHolder, int i){
        Card card = cardList.get(i);
        cardViewHolder.mTitle.setText(card.cardTitle);
        cardViewHolder.mName.setText(card.displayName);
        cardViewHolder.mCell.setText(card.cellNumber);
    }

    public CardViewHolder onCreateViewHolder (ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_layout, viewGroup, false);

        return new CardViewHolder(itemView);

    }
    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
        TextView mName;
        TextView mCell;

        public CardViewHolder(View view){
            super(view);

            mTitle = (TextView) view.findViewById(R.id.title_textView);
            mName = (TextView) view.findViewById(R.id.name_textView);
            mCell = (TextView) view.findViewById(R.id.cell_textView);

        }
    }
}
