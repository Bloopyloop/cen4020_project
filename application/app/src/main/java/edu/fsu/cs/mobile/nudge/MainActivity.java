package edu.fsu.cs.mobile.nudge;

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

public class MainActivity extends AppCompatActivity {
    EditText mEmail;
    EditText mPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = (EditText) findViewById(R.id.email_editText);
        mPassword = (EditText) findViewById(R.id.password_editText);
        firebaseAuth = FirebaseAuth.getInstance();
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

        if(password.length() < 6){
            mPassword.setError("Field must be longer than 6 characters");
            submitFlag = false;
        }

        if(submitFlag){
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

    public void signUpButton(View view){
        // uncomment before submission
        //Intent intent = new Intent(this, SignUpActivity.class);
        //startActivity(intent);

        // short cut to home for testing purposes
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


}
