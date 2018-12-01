package edu.fsu.cs.mobile.nudge;

import android.support.v7.widget.RecyclerView;
import java.util.List;
import java.io.IOException;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Bitmap;

import com.google.zxing.WriterException;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Card> cardList;
    private Context qrContext;
    Bitmap bmp;
    private String QRuid;

    public CardAdapter(List<Card> cardList){
        this.cardList = cardList;
    }

    public int getItemCount() {
        return cardList.size();
    }

    public void onBindViewHolder(final CardViewHolder cardViewHolder, int i){
        final QRCodeGenerator QRGen = new QRCodeGenerator(qrContext);
        final Card card = cardList.get(i);
        if(card.cardTitle.isEmpty() && card.displayName.isEmpty() && card.cellNumber.isEmpty() &&
                card.workNumber.isEmpty() && card.homeNumber.isEmpty() && card.personalEmail.isEmpty() &&
                card.workEmail.isEmpty() && card.website.isEmpty() && card.linkedIn.isEmpty() &&
                card.facebook.isEmpty() && card.twitter.isEmpty()) {
            cardViewHolder.mTitle.setText("Click Add Card to Make your First Card! ");
            cardViewHolder.mName.setText("");
            cardViewHolder.mCell.setText("");
            cardViewHolder.mWorkNum.setText("");
            cardViewHolder.mHomeNum.setText("");
            cardViewHolder.mEmail.setText("");
            cardViewHolder.mWorkEmail.setText("");
            cardViewHolder.mWebsite.setText("");
            cardViewHolder.mLinkedin.setText("");
            cardViewHolder.mFacebook.setText("");
            cardViewHolder.mTwitter.setText("");
            cardViewHolder.mQRButton.setVisibility(View.INVISIBLE);
        }
        else {
            cardViewHolder.mTitle.setText("Title: " + card.cardTitle);
            cardViewHolder.mName.setText("Name: " + card.displayName);
            cardViewHolder.mCell.setText("Cell: " + card.cellNumber);
            cardViewHolder.mWorkNum.setText("Work Number: " + card.workNumber);
            cardViewHolder.mHomeNum.setText("Home Number: " + card.homeNumber);
            cardViewHolder.mEmail.setText("Email: " + card.personalEmail);
            cardViewHolder.mWorkEmail.setText("Work Email: " + card.workEmail);
            cardViewHolder.mWebsite.setText("Website: " + card.website);
            cardViewHolder.mLinkedin.setText("LinkedIn: " + card.linkedIn);
            cardViewHolder.mFacebook.setText("Facebook: " + card.facebook);
            cardViewHolder.mTwitter.setText("Twitter: " + card.twitter);
            cardViewHolder.mQRButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardViewHolder.mQRButton.isPressed()) {
                        try {
                            bmp = QRGen.generateQRCodeImage(card.getUid());
                            String path = QRGen.saveImage(bmp);
                        } catch (WriterException e) {
                            e.printStackTrace();
                        }
                        cardViewHolder.mQRButton.setImageBitmap(bmp);
                    } else {
                        cardViewHolder.mQRButton.setImageResource(R.drawable.viewqrbutton);
                    }
                }
            });
        }
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
