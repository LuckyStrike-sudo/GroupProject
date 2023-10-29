package com.example.boardswipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword;  //text from users from email and password inputs
    Button buttonLogin;                 //login Button
    FirebaseAuth mAuth;                 //firebase object to interact with the database and authentication
    ProgressBar progressBar;            //progress bar
    TextView textView;                  //'register now' clickable text

    @Override                           //code from firebase documentation
    public void onStart() {             //checks to see if user is already signed in
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class); //if already logged in just go to boardgames view in app
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();              //instances firebase object
        editTextEmail = findViewById(R.id.email);        //finds the input from key of xml file
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btn_login);      //same thing but for button 'login'
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.registerNow);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);   //when text is clicked go to register activity page
                startActivity(intent);    //start activity_register.xml page
                finish();                 //finish current activity
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);     //when clicked it displays progress bar (the loading circle)
                String email, password;

                email = String.valueOf(editTextEmail.getText());       //converts input to a string
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email)){          //if nothing is typed for email show the message ""Enter email" as a popup after the click
                    Toast.makeText(Login.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)) {          //same thing but for the case of password
                    Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)  //code from firebase documentation --> authenticates
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);   //stop displaying progress bar (loading circle)
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);    //After login, go to MainActivity page where it displays the board games
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });
    }
}