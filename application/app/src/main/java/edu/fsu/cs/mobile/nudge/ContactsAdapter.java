package edu.fsu.cs.mobile.nudge;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>{

    private List<Card> cardList;

    public ContactsAdapter(List<Card> cardList){
        this.cardList = cardList;
    }

    public int getItemCount() {
        return cardList.size();
    }

    public void onBindViewHolder(ContactsViewHolder contactsViewHolder, int i){
        final Card card = cardList.get(i);

        if ( card.cardTitle.isEmpty() && card.displayName.isEmpty() && card.cellNumber.isEmpty() &&
                card.workNumber.isEmpty() && card.homeNumber.isEmpty() && card.personalEmail.isEmpty() &&
                card.workEmail.isEmpty() && card.website.isEmpty() && card.linkedIn.isEmpty() &&
                card.facebook.isEmpty() && card.twitter.isEmpty()  ) {
            contactsViewHolder.mName.setText("You don't have any Contacts!");
            /*
            contactsViewHolder.mCell.setText("");
            contactsViewHolder.mWorkNum.setText("");
            contactsViewHolder.mHomeNum.setText("");
            contactsViewHolder.mEmail.setText("");
            contactsViewHolder.mWorkEmail.setText("");
            contactsViewHolder.mWebsite.setText("");
            contactsViewHolder.mLinkedin.setText("");
            contactsViewHolder.mFacebook.setText("");
            contactsViewHolder.mTwitter.setText("");
            */
            contactsViewHolder.mCell.setVisibility(View.GONE);
            contactsViewHolder.mWorkNum.setVisibility(View.GONE);
            contactsViewHolder.mHomeNum.setVisibility(View.GONE);
            contactsViewHolder.mEmail.setVisibility(View.GONE);
            contactsViewHolder.mWorkEmail.setVisibility(View.GONE);
            contactsViewHolder.mWebsite.setVisibility(View.GONE);
            contactsViewHolder.mLinkedin.setVisibility(View.GONE);
            contactsViewHolder.mFacebook.setVisibility(View.GONE);
            contactsViewHolder.mTwitter.setVisibility(View.GONE);
            contactsViewHolder.mDelete.setVisibility(View.GONE);

        }
        else {
            /*
            contactsViewHolder.mName.setText("Name: " + card.displayName);
            contactsViewHolder.mCell.setText("Cell: " + card.cellNumber);
            contactsViewHolder.mWorkNum.setText("Work Number: " + card.workNumber);
            contactsViewHolder.mHomeNum.setText("Home Number: " + card.homeNumber);
            contactsViewHolder.mEmail.setText("Email: " + card.personalEmail);
            contactsViewHolder.mWorkEmail.setText("Work Email: " + card.workEmail);
            contactsViewHolder.mWebsite.setText("Website: " + card.website);
            contactsViewHolder.mLinkedin.setText("LinkedIn: " +card.linkedIn);
            contactsViewHolder.mFacebook.setText("Facebook: " +card.facebook);
            contactsViewHolder.mTwitter.setText("Twitter: " + card.twitter);
            */

            if (card.displayName.isEmpty()){
                contactsViewHolder.mName.setVisibility(View.GONE);
            }
            else {
                contactsViewHolder.mName.setText("Name: " + card.displayName);
            }
            if (card.cellNumber.isEmpty()){
                contactsViewHolder.mCell.setVisibility(View.GONE);
            }
            else {
                contactsViewHolder.mCell.setText("Cell: " + card.cellNumber);
            }
            if ( card.workNumber.isEmpty()){
                contactsViewHolder.mWorkNum.setVisibility(View.GONE);
            }
            else {
                contactsViewHolder.mWorkNum.setText("Work Number: " + card.workNumber);
            }
            if (card.homeNumber.isEmpty()){
                contactsViewHolder.mHomeNum.setVisibility(View.GONE);
            }
            else {
                contactsViewHolder.mHomeNum.setText("Home Number: " + card.homeNumber);
            }
            if (card.personalEmail.isEmpty()){
                contactsViewHolder.mEmail.setVisibility(View.GONE);
            }
            else {
                contactsViewHolder.mEmail.setText("Email: " + card.personalEmail);
            }
            if (card.workEmail.isEmpty()){
                contactsViewHolder.mWorkEmail.setVisibility(View.GONE);
            }
            else {
                contactsViewHolder.mWorkEmail.setText("Work Email: " + card.workEmail);
            }
            if (card.website.isEmpty()){
                contactsViewHolder.mWebsite.setVisibility(View.GONE);
            }
            else {
                contactsViewHolder.mWebsite.setText("Website: " + card.website);
            }
            if (card.linkedIn.isEmpty()){
                contactsViewHolder.mLinkedin.setVisibility(View.GONE);
            }
            else {
                contactsViewHolder.mLinkedin.setText("LinkedIn: " + card.linkedIn);
            }
            if (card.facebook.isEmpty()){
                contactsViewHolder.mFacebook.setVisibility(View.GONE);
            }
            else {
                contactsViewHolder.mFacebook.setText("Facebook: " + card.facebook);
            }
            if (card.twitter.isEmpty()){
                contactsViewHolder.mTwitter.setVisibility(View.GONE);
            }
            else {
                contactsViewHolder.mTwitter.setText("Twitter: " + card.twitter);
            }

            contactsViewHolder.mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference(user.getUid() + "/contacts");
                    Query query = ref.orderByChild("cardID").equalTo(card.cardID);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                snapshot.getRef().removeValue();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            });
        }
    }

    public ContactsViewHolder onCreateViewHolder (ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contacts_view_layout, viewGroup, false);

        return new ContactsViewHolder(itemView);

    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {

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

        Button mDelete;

        public ContactsViewHolder(View view){
            super(view);

            mName = (TextView) view.findViewById(R.id.displayName_contact_textView);
            mCell = (TextView) view.findViewById(R.id.cell_contact_textView);
            mWorkNum = (TextView) view.findViewById(R.id.workNumber_contact_textView);
            mHomeNum = (TextView) view.findViewById(R.id.homeNumber_contact_textView);
            mEmail = (TextView) view.findViewById(R.id.personalEmail_contact_textView);
            mWorkEmail = (TextView) view.findViewById(R.id.workEmail_contact_textView);
            mWebsite = (TextView) view.findViewById(R.id.website_contact_textView);
            mLinkedin = (TextView) view.findViewById(R.id.linkedin_contact_textView);
            mFacebook = (TextView) view.findViewById(R.id.facebook_contact_textView);
            mTwitter = (TextView) view.findViewById(R.id.twitter_contact_textView);
            mDelete = (Button) view.findViewById(R.id.delete_contact_button);
        }
    }
}
