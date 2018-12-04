package edu.fsu.cs.mobile.nudge;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.util.Log;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    EditText mEmail;
    EditText mPassword;
    private FirebaseAuth firebaseAuth;
    private VideoView aVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = (EditText) findViewById(R.id.email_editText);
        mPassword = (EditText) findViewById(R.id.password_editText);
        firebaseAuth = FirebaseAuth.getInstance();

        aVideoView = (VideoView) findViewById(R.id.nVideoView);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+ R.raw.nudgevideo);

        aVideoView.setVideoURI(uri);
        aVideoView.start();

        aVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    // method to handle SignIn Button click
    public void signInButton(View view){
        // query database - check if user exists
        // if not prompt error
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        Boolean submitFlag = true;

        if(email.matches("")){
            mEmail.setError("Field cannot be blank");
            submitFlag = false;
        }
        if(password.matches("")){
            mPassword.setError("Field cannot be blank");
            submitFlag = false;
        }


        if(submitFlag){
            Log.i("submit", "submit Flag == true");
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener
                    (new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Sign In Failed!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

    }

    public void signUpButton(View view) {
        // uncomment before submission
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);

        // short cut to home for testing purposes
        //Intent intent = new Intent(this, HomeActivity.class);
        //startActivity(intent);
    }


}
