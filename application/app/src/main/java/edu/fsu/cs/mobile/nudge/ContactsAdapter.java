package edu.fsu.cs.mobile.nudge;

import android.support.v7.widget.RecyclerView;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
            contactsViewHolder.mCell.setText("");
            contactsViewHolder.mWorkNum.setText("");
            contactsViewHolder.mHomeNum.setText("");
            contactsViewHolder.mEmail.setText("");
            contactsViewHolder.mWorkEmail.setText("");
            contactsViewHolder.mWebsite.setText("");
            contactsViewHolder.mLinkedin.setText("");
            contactsViewHolder.mFacebook.setText("");
            contactsViewHolder.mTwitter.setText("");

        }
        else {
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


        }
    }
}
