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
        ref = database.getReference();
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
                String key = ref.child("cards").push().getKey();
                Map<String, String> myMap = new HashMap<String, String>();


                myMap.put("UID", uid);
                myMap.put("CardTitle", mCardTitle.getText().toString());
                myMap.put("DisplayName", mDisplayName.getText().toString());
                myMap.put("CellPhone", mCellNum.getText().toString());
                myMap.put("WorkPhone", mWorkNum.getText().toString());
                myMap.put("HomePhone", mHomeNum.getText().toString());
                myMap.put("Email", mEmail.getText().toString());
                myMap.put("WorkEmail", mWorkEmail.getText().toString());
                myMap.put("Website", mWebsite.getText().toString());
                myMap.put("LinkedIn", mLinkedin.getText().toString());
                myMap.put("Facebook", mFacebook.getText().toString());
                myMap.put("Twitter", mTwitter.getText().toString() );


                Log.i("MakeCard - Work", mWorkNum.getText().toString());
                Log.i("MakeCard - Home", mHomeNum.getText().toString());

                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("/cards/" + key, myMap);


                ref.updateChildren(childUpdates);

                ((HomeActivity) getActivity()).setupNavigation();
            }
        });

        return view;
    }

}
