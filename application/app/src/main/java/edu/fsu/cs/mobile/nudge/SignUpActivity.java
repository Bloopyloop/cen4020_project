package edu.fsu.cs.mobile.nudge;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;
import android.content.Intent;

import android.util.Log;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button mRegister;
    EditText mEmail;
    EditText mPassword;
    EditText mConfirm;

    String TAG = "Sign Up Auth:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        if ( mAuth == null ){
            Log.i("Firebase Auth", "NULL");
        }

        mRegister = (Button) findViewById(R.id.register_button);
        mEmail = (EditText) findViewById(R.id.email_editText);
        mPassword = (EditText) findViewById(R.id.password_editText);
        mConfirm = (EditText) findViewById(R.id.confirm_editView);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String confirm = mConfirm.getText().toString().trim();

                boolean formFilled = true;

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Enter email address!");
                    formFilled = false;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Enter password!");
                    formFilled = false;
                }

                if (TextUtils.isEmpty(confirm)) {
                    mConfirm.setError("Confirm password!");
                    formFilled = false;
                }

                if (!password.equals(confirm)) {
                    mPassword.setError("Passwords do not match");
                    mConfirm.setError("");
                    formFilled = false;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password must be at least 6 characters.");
                    formFilled = false;
                }

                if (formFilled) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.i(TAG, "SUCCESS");
                                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Log.i(TAG, "FAIL");
                                        Toast.makeText(getApplicationContext(), "Registration FAILED!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });// onCompleteListener
                }
            }
        });
    }

}
