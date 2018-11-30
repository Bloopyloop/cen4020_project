package edu.fsu.cs.mobile.nudge;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.zxing.WriterException;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Card> cardList;
    private Context qrContext;
    Bitmap bmp;

    public CardAdapter(List<Card> cardList){
        this.cardList = cardList;
    }

    public int getItemCount() {
        return cardList.size();
    }

    public void onBindViewHolder(final CardViewHolder cardViewHolder, int i){
        QRCodeGenerator QRGen = new QRCodeGenerator(qrContext);
        try {
            bmp = QRGen.generateQRCodeImage("Pootis");
            String path = QRGen.saveImage(bmp);
        }
        catch (WriterException e) {
            e.printStackTrace();
        }
        Card card = cardList.get(i);
        cardViewHolder.mTitle.setText(card.cardTitle);
        cardViewHolder.mName.setText(card.displayName);
        cardViewHolder.mCell.setText(card.cellNumber);
        cardViewHolder.mWorkNum.setText(card.workNumber);
        cardViewHolder.mHomeNum.setText(card.homeNumber);
        cardViewHolder.mEmail.setText(card.personalEmail);
        cardViewHolder.mWebsite.setText(card.website);
        cardViewHolder.mLinkedin.setText(card.linkedIn);
        cardViewHolder.mFacebook.setText(card.facebook);
        cardViewHolder.mTwitter.setText(card.twitter);
        cardViewHolder.mQRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cardViewHolder.mQRButton.isPressed()) {
                    cardViewHolder.mQRButton.setImageBitmap(bmp);
                }
                else {
                    cardViewHolder.mQRButton.setImageResource(R.drawable.viewqrbutton);
                }
            }
        });
    }

    public CardViewHolder onCreateViewHolder (ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_layout, viewGroup, false);

        qrContext = viewGroup.getContext();
        return new CardViewHolder(itemView);

    }
    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
        TextView mName;
        TextView mCell;
        TextView mWorkNum;
        TextView mHomeNum;
        TextView mEmail;
        TextView mWorkEmail;
        TextView mWebsite;
        TextView mLinkedin;
        TextView mFacebook;
        TextView mTwitter;
        ImageButton mQRButton;

        public CardViewHolder(View view){
            super(view);

            mTitle = (TextView) view.findViewById(R.id.title_card_textView);
            mName = (TextView) view.findViewById(R.id.name_card_textView);
            mCell = (TextView) view.findViewById(R.id.cell_card_textView);
            mWorkNum = (TextView) view.findViewById(R.id.workNumber_card_textView);
            mHomeNum = (TextView) view.findViewById(R.id.homeNumber_card_textView);
            mEmail = (TextView) view.findViewById(R.id.personalEmail_card_textView);
            mWorkEmail = (TextView) view.findViewById(R.id.workEmail_card_textView);
            mWebsite = (TextView) view.findViewById(R.id.website_card_textView);
            mLinkedin = (TextView) view.findViewById(R.id.linkedin_card_textView);
            mFacebook = (TextView) view.findViewById(R.id.facebook_card_textView);
            mTwitter = (TextView) view.findViewById(R.id.twitter_card_textView);
            mQRButton = (ImageButton) view.findViewById(R.id.QR_imageButton);

        }
    }
}
