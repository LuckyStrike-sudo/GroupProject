package com.example.boardswipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {     //All of the Activity.java files come with this, just ignore it
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        user = auth.getCurrentUser();   //get the user
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);  //logout and go to login page if null
            startActivity(intent);              //start login activity page
            finish();                           //finish current activity
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();      //sign out user from firebase
                Intent intent = new Intent(getApplicationContext(), Login.class);  //logout and go to login page
                startActivity(intent);              //start login activity page
                finish();                           //finish current activity
            }
        });

    }
} //Sign up Page