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
        Card card = cardList.get(i);

        contactsViewHolder.mName.setText(card.displayName);
        contactsViewHolder.mCell.setText(card.cellNumber);
        contactsViewHolder.mWorkNum.setText(card.workNumber);
        contactsViewHolder.mHomeNum.setText(card.homeNumber);
        contactsViewHolder.mEmail.setText(card.personalEmail);
        contactsViewHolder.mWorkEmail.setText(card.workEmail);
        contactsViewHolder.mWebsite.setText(card.website);
        contactsViewHolder.mLinkedin.setText(card.linkedIn);
        contactsViewHolder.mFacebook.setText(card.facebook);
        contactsViewHolder.mTwitter.setText(card.twitter);
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
