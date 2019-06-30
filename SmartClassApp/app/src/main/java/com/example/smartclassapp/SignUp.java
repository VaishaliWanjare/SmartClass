package com.example.smartclassapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {

    Button email_button, phone_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //facebook_button = (Button) findViewById(R.id.facebook_button);
        phone_button = (Button) findViewById(R.id.phone_button);
        email_button = (Button) findViewById(R.id.email_button);


        /*facebook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, FacebookAuth.class));
            }
        });*/

        email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, EmailActivity.class));
            }
        });

        phone_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, PhoneAuth.class));
            }
        });

    }
}
