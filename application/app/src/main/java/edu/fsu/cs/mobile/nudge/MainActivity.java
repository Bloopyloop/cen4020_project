package edu.fsu.cs.mobile.nudge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // method to handle SignIn Button click
    public void signInButton(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }


}
