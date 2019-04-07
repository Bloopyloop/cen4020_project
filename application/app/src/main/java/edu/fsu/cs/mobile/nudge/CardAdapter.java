package edu.fsu.cs.mobile.nudge;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import java.io.IOException;

import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.widget.Toast;
import android.content.Intent;

import android.util.Log;

import com.google.firebase.database.ValueEventListener;
import com.google.zxing.WriterException;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Card> cardList;
    private Context qrContext;
    Bitmap bmp;
    private String QRuid;
    ProgressBar progress;

    public CardAdapter(List<Card> cardList){
        this.cardList = cardList;
    }

    public int getItemCount() {
        return cardList.size();
    }

    public void onBindViewHolder(final CardViewHolder cardViewHolder, int i){
        //Card card = cardList.get(i);
        final QRCodeGenerator QRGen = new QRCodeGenerator(qrContext);
        final Card card = cardList.get(i);

        if ( card.cardTitle.isEmpty() && card.displayName.isEmpty() && card.cellNumber.isEmpty() &&
                card.workNumber.isEmpty() && card.homeNumber.isEmpty() && card.personalEmail.isEmpty() &&
                card.workEmail.isEmpty() && card.website.isEmpty() && card.linkedIn.isEmpty() &&
                card.facebook.isEmpty() && card.twitter.isEmpty()  ) {
            cardViewHolder.mTitle.setText("Click Add Card to Make your First Card! ");

            /*
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
            */

            cardViewHolder.mName.setVisibility(View.GONE);
            cardViewHolder.mCell.setVisibility(View.GONE);
            cardViewHolder.mWorkNum.setVisibility(View.GONE);
            cardViewHolder.mHomeNum.setVisibility(View.GONE);
            cardViewHolder.mEmail.setVisibility(View.GONE);
            cardViewHolder.mWorkEmail.setVisibility(View.GONE);
            cardViewHolder.mWebsite.setVisibility(View.GONE);
            cardViewHolder.mLinkedin.setVisibility(View.GONE);
            cardViewHolder.mFacebook.setVisibility(View.GONE);
            cardViewHolder.mTwitter.setVisibility(View.GONE);

            cardViewHolder.mQRButton.setVisibility(View.GONE);
            cardViewHolder.mNFCButton.setVisibility(View.GONE);
            cardViewHolder.mDelete.setVisibility(View.GONE);

        }
        else {
            if (card.cardTitle.isEmpty()){
                cardViewHolder.mTitle.setVisibility(View.GONE);
            }
            else {
                cardViewHolder.mTitle.setText(card.cardTitle);
            }
            if (card.displayName.isEmpty()){
                cardViewHolder.mName.setVisibility(View.GONE);
            }
            else {
                cardViewHolder.mName.setText("Name: " + card.displayName);
            }
            if (card.cellNumber.isEmpty()){
                cardViewHolder.mCell.setVisibility(View.GONE);
            }
            else {
                cardViewHolder.mCell.setText("Cell: " + card.cellNumber);
            }
            if ( card.workNumber.isEmpty()){
                cardViewHolder.mWorkNum.setVisibility(View.GONE);
            }
            else {
                cardViewHolder.mWorkNum.setText("Work Number: " + card.workNumber);
            }
            if (card.homeNumber.isEmpty()){
                cardViewHolder.mHomeNum.setVisibility(View.GONE);
            }
            else {
                cardViewHolder.mHomeNum.setText("Home Number: " + card.homeNumber);
            }
            if (card.personalEmail.isEmpty()){
                cardViewHolder.mEmail.setVisibility(View.GONE);
            }
            else {
                cardViewHolder.mEmail.setText("Email: " + card.personalEmail);
            }
            if (card.workEmail.isEmpty()){
                cardViewHolder.mWorkEmail.setVisibility(View.GONE);
            }
            else {
                cardViewHolder.mWorkEmail.setText("Work Email: " + card.workEmail);
            }
            if (card.website.isEmpty()){
                cardViewHolder.mWebsite.setVisibility(View.GONE);
            }
            else {
                cardViewHolder.mWebsite.setText("Website: " + card.website);
                Linkify.addLinks(cardViewHolder.mWebsite, Linkify.ALL);
            }
            if (card.linkedIn.isEmpty()){
                cardViewHolder.mLinkedin.setVisibility(View.GONE);
            }
            else {
                cardViewHolder.mLinkedin.setText("LinkedIn: " + card.linkedIn);
                Linkify.addLinks(cardViewHolder.mLinkedin, Linkify.ALL);
            }
            if (card.facebook.isEmpty()){
                cardViewHolder.mFacebook.setVisibility(View.GONE);
            }
            else {
                cardViewHolder.mFacebook.setText("Facebook: " + card.facebook);
                Linkify.addLinks(cardViewHolder.mFacebook, Linkify.ALL);
            }
            if (card.twitter.isEmpty()){
                cardViewHolder.mTwitter.setVisibility(View.GONE);
            }
            else {
                cardViewHolder.mTwitter.setText("Twitter: " + card.twitter);
                Linkify.addLinks(cardViewHolder.mTwitter, Linkify.ALL);
            }


            try {
                bmp = QRGen.generateQRCodeImage(card.getCardID());
                String path = QRGen.saveImage(bmp);
                cardViewHolder.mQR.setImageBitmap(bmp);
            } catch (WriterException e) {
                e.printStackTrace();
            }

            cardViewHolder.mQRButton.setOnClickListener(new View.OnClickListener() {

                Boolean qrVisible = false;
                @Override
                public void onClick(View view) {

                    if (!qrVisible) {
                        Log.i("onClick", "Button Pressed");
                        cardViewHolder.mQR.setVisibility(View.VISIBLE);
                        cardViewHolder.mQRButton.setText("Hide QR");
                        qrVisible = true;
                    }
                    else {
                        cardViewHolder.mQRButton.setText("Show QR");
                        cardViewHolder.mQR.setVisibility(View.GONE);
                        qrVisible = false;
                    }

                }
            });

            cardViewHolder.mNFCButton.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("cardID", card.cardID);
                    Intent intent = new Intent(view.getContext(), NFCsend.class);
                    intent.putExtras(bundle);
                    view.getContext().startActivity(intent);

                    // send card.cardID;

                }
            });

            cardViewHolder.mDelete.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    DatabaseReference ref1 = FirebaseDatabase.getInstance()
                            .getReference(user.getUid() + "/cards");

                    ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ref1.child(card.cardID).removeValue();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    DatabaseReference ref2 = FirebaseDatabase.getInstance()
                            .getReference("/cards");

                    ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ref2.child(card.cardID).removeValue();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

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
        ImageView mQR;
        Button mQRButton;
        Button mNFCButton;
        Button mDelete;

        public CardViewHolder(View view){
            super(view);
            Typeface typeface = view.getContext().getResources().getFont(R.font.montserrat_regular);
            Typeface typefaceBold = view.getContext().getResources().getFont(R.font.montserrat_bold);
            mTitle = (TextView) view.findViewById(R.id.title_card_textView);
            mTitle.setTypeface(typefaceBold);
            mName = (TextView) view.findViewById(R.id.name_card_textView);
            mName.setTypeface(typeface);
            mCell = (TextView) view.findViewById(R.id.cell_card_textView);
            mCell.setTypeface(typeface);
            mWorkNum = (TextView) view.findViewById(R.id.workNumber_card_textView);
            mWorkNum.setTypeface(typeface);
            mHomeNum = (TextView) view.findViewById(R.id.homeNumber_card_textView);
            mHomeNum.setTypeface(typeface);
            mEmail = (TextView) view.findViewById(R.id.personalEmail_card_textView);
            mEmail.setTypeface(typeface);
            mWorkEmail = (TextView) view.findViewById(R.id.workEmail_card_textView);
            mWorkEmail.setTypeface(typeface);
            mWebsite = (TextView) view.findViewById(R.id.website_card_textView);
            mWebsite.setTypeface(typeface);
            mLinkedin = (TextView) view.findViewById(R.id.linkedin_card_textView);
            mLinkedin.setTypeface(typeface);
            mFacebook = (TextView) view.findViewById(R.id.facebook_card_textView);
            mFacebook.setTypeface(typeface);
            mTwitter = (TextView) view.findViewById(R.id.twitter_card_textView);
            mTwitter.setTypeface(typeface);
            mQR = (ImageView) view.findViewById(R.id.qr_imageView);
            mQRButton = (Button) view.findViewById(R.id.show_qr_button);
            mNFCButton = (Button) view.findViewById(R.id.send_nfc_button);
            mDelete = (Button) view.findViewById(R.id.delete_button);
        }
    }
}
