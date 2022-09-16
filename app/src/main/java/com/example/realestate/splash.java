package com.example.realestate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class splash extends AppCompatActivity {
        FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        auth=FirebaseAuth.getInstance();
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (auth.getCurrentUser()==null)
                {
                    startActivity(new Intent(splash.this, MainActivity.class));
                }
                else
                {
                    startActivity(new Intent(splash.this, HomeNav.class));
                }
                finish();
            }
        }, secondsDelayed * 3000);
    }
}