package edu.fsu.cs.mobile.nudge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.util.Log;
import edu.fsu.cs.mobile.nudge.nfc.OutcomingNfcManager;


public class NFCsend extends AppCompatActivity implements OutcomingNfcManager.NfcActivity{

    Button mCancel;

    private NfcAdapter nfcAdapter;
    private OutcomingNfcManager outcomingNfccallback;
    String cardID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcsend);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        mCancel = findViewById(R.id.cancel_button);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NFCsend.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        if (bundle != null){
            cardID = bundle.getString("cardID");
            Log.i("12345", cardID);
        }

        Log.i("12345", "second Log");


        if (!isNfcSupported()) {
            Toast.makeText(this, "Nfc is not supported on this device", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (!nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC disabled on this device. Turn on to proceed", Toast.LENGTH_SHORT).show();
        }

        // encapsulate sending logic in a separate class
        this.outcomingNfccallback = new OutcomingNfcManager(this);
        this.nfcAdapter.setOnNdefPushCompleteCallback(outcomingNfccallback, this);
        this.nfcAdapter.setNdefPushMessageCallback(outcomingNfccallback, this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    private boolean isNfcSupported() {
        this.nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        return this.nfcAdapter != null;
    }

    @Override
    public String getOutcomingMessage() {
        //return this.tvOutcomingMessage.getText().toString();
        return cardID;
    }

    @Override
    public void signalResult() {
        // this will be triggered when NFC message is sent to a device.
        // should be triggered on UI thread. We specify it explicitly
        // cause onNdefPushComplete is called from the Binder thread
        /*
        runOnUiThread(() ->
                Toast.makeText(NFCsend.this, "Completed Beam", Toast.LENGTH_SHORT).show());
                */

        Intent intent = new Intent(NFCsend.this, HomeActivity.class);
        startActivity(intent);
    }



}
