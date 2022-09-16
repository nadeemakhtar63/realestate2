package com.example.realestate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class registersecond extends AppCompatActivity {
        Button registersecond;
        private TextInputEditText number,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String numbers = intent.getStringExtra("uphone");
        setContentView(R.layout.activity_registersecond);
        registersecond=findViewById(R.id.btn_regcontinue);
        number=findViewById(R.id.phoneNumber);
        password=findViewById(R.id.pswrd);
        number.setText(numbers);
        registersecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registersecond.this,OTPVerifyActivity.class);
                startActivity(intent);
            }
        });
    }
}