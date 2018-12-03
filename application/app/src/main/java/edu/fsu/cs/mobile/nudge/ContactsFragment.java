package edu.fsu.cs.mobile.nudge;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import static android.app.Activity.RESULT_OK;
import static android.support.v4.content.ContextCompat.checkSelfPermission;
import android.util.Log;



public class ContactsFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference ref_put;
    DatabaseReference ref_get;


    FirebaseUser user;
    Card printCard;
    List<Card> listCards;
    RecyclerView rec;

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 1888;

    Button mQRButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        rec =  (RecyclerView) view.findViewById(R.id.contacts_recyclerView);
        rec.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(llm);

        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        ref_put = database.getReference(user.getUid() + "/contacts");



        mQRButton = (Button) view.findViewById(R.id.qr_button);



        mQRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            200);
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 200);
                }
            }
        });

        listCards = new ArrayList<>();

        // load empty card
        //ContactsAdapter ca = new ContactsAdapter(createList(1));
        //rec.setAdapter(ca);

        // query from database, get contacts
        // make another query to get Card based on contacts.children values
        // create ContactsAdapter
        // add ProgressBar

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        ref_put.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCards.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot cardSnapshot : dataSnapshot.getChildren()) {
                        printCard = cardSnapshot.getValue(Card.class);

                        listCards.add(printCard);
                        //progress.setVisibility(View.VISIBLE);
                        ContactsAdapter ca = new ContactsAdapter(listCards);
                        rec.setAdapter(ca);
                        //progress.setVisibility(View.GONE);
                    }

                }
                else {
                    ContactsAdapter ca = new ContactsAdapter(createList(1));
                    rec.setAdapter(ca);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private List<Card> createList(int size){
        List<Card> result = new ArrayList<Card>();

        for(int i = 1; i <= size; ++i){
            Card card = new Card();

            card.cardTitle = "";
            card.displayName = "";
            card.cellNumber = "";
            card.homeNumber = "";
            card.workNumber = "";
            card.personalEmail = "";
            card.workEmail = "";
            card.website = "";
            card.linkedIn = "";
            card.facebook = "";
            card.twitter = "";

            result.add(card);
        }

        return result;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("postPicture B", "permission_granted");
                Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 200);
            } else {
                Log.i("postPicture B", "permission_denied" );
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }

    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        //if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
        if (resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            runDetector(photo);
            Log.i("postPicture", "onActivityResult");
        }
    }

    private void runDetector(Bitmap bitmap) {
        Log.i("postPicture", "runDetector");

        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionBarcodeDetectorOptions options =
                new FirebaseVisionBarcodeDetectorOptions.Builder()
                        .setBarcodeFormats(
                                FirebaseVisionBarcode.FORMAT_QR_CODE
                        )
                        .build();
        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
                .getVisionBarcodeDetector(options);
        Task<List<FirebaseVisionBarcode>> result = detector.detectInImage(image)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionBarcode> barcodes) {
                        for(FirebaseVisionBarcode barcode: barcodes) {
                            Rect bounds = barcode.getBoundingBox();
                            Point[] corners = barcode.getCornerPoints();
                            String rawValue = barcode.getRawValue();
                            int valueType = barcode.getValueType();
                            Toast.makeText(getActivity(), rawValue, Toast.LENGTH_LONG).show();
                            Log.i("postPicture", rawValue);

                            final String key = ref_put.push().getKey();

                            if (key != null) {
                                //ref_put.child(key).setValue(rawValue);
                                ref_get = database.getReference("/cards");
                                ref_get.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        List<Card> cardsList = new ArrayList<>();
                                        Card tempCard;

                                        if (dataSnapshot.exists()) {
                                            for (DataSnapshot cardSnapshot : dataSnapshot.getChildren()) {
                                                tempCard = cardSnapshot.getValue(Card.class);

                                                ref_put.child(key).setValue(tempCard);
                                            }
                                        }

                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Error, unable to find contact.", Toast.LENGTH_LONG).show();
                        Log.i("postPicture", "Error");
                    }
                });
    }

}
