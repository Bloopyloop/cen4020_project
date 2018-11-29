package edu.fsu.cs.mobile.nudge;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;
import java.util.HashMap;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseUser;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;


public class MakeCardsFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseUser user;
    Button mButton;
    EditText mCardTitle;
    EditText mDisplayName;
    EditText mCellNum;
    EditText mWorkNum;
    EditText mHomeNum;
    EditText mEmail;
    EditText mWorkEmail;
    EditText mWebsite;
    EditText mLinkedin;
    EditText mFacebook;
    EditText mTwitter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("cards");

        user = FirebaseAuth.getInstance().getCurrentUser();
        View view = inflater.inflate(R.layout.fragment_make_card, container, false);

        mButton = (Button) view.findViewById(R.id.create_Button);

        mCardTitle = (EditText) view.findViewById(R.id.cardTitle_editText);
        mDisplayName = (EditText) view.findViewById(R.id.displayName_editText);
        mCellNum = (EditText) view.findViewById(R.id.cellNumber_editText);
        mWorkNum = (EditText) view.findViewById(R.id.workNumber_editText);
        mHomeNum = (EditText) view.findViewById(R.id.homeNumber_editText);
        mEmail = (EditText) view.findViewById(R.id.personalEmail_editText);
        mWorkEmail = (EditText) view.findViewById(R.id.workEmail_editText);
        mWebsite = (EditText) view.findViewById(R.id.website_editText);
        mLinkedin = (EditText) view.findViewById(R.id.linkedin_editText);
        mFacebook = (EditText) view.findViewById(R.id.facebook_editText);
        mTwitter = (EditText) view.findViewById(R.id.twitter_editText);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uid = user.getUid();
                String key = ref.push().getKey();

                Card card = new Card();

                card.setUid(uid);
                card.setCardTitle(mCardTitle.getText().toString());
                card.setDisplayName(mDisplayName.getText().toString());
                card.setCellNumber(mCellNum.getText().toString());
                card.setWorkNumber(mWorkNum.getText().toString());
                card.setHomeNumber(mHomeNum.getText().toString());
                card.setPersonalEmail(mEmail.getText().toString());
                card.setWorkEmail(mWorkEmail.getText().toString());
                card.setWebsite(mWebsite.getText().toString());
                card.setLinkedIn(mLinkedin.getText().toString());
                card.setFacebook(mFacebook.getText().toString());
                card.setTwitter(mTwitter.getText().toString());

                ref.child(key).setValue(card);

                ((HomeActivity) getActivity()).setupNavigation();
            }
        });

        return view;
    }

}
