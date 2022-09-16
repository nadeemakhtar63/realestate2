package com.example.realestate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterScreen extends AppCompatActivity {
        Button btnregister;
        private  String suname,snumber,spswrd,scpswrd;
        private TextInputEditText name,phone,password,cpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        name=findViewById(R.id.username);
        phone=findViewById(R.id.phoneNumber);
        password=findViewById(R.id.pswrd);
        cpassword=findViewById(R.id.cpass);
        btnregister=findViewById(R.id.btn_regcontinue);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suname = name.getText().toString();
                snumber = phone.getText().toString();
                spswrd = password.getText().toString();
                scpswrd = cpassword.getText().toString();
                if (suname.isEmpty()) {
                    name.setError("Enter Name");
                } else if (snumber.isEmpty()) {
                    phone.setError("Enter Mobile Number");
                } else if (snumber.length() < 10) {
                    phone.setError("Invalid Number");
                } else if (spswrd.isEmpty()) {
                    password.setError("Password can't be empty");
                } else if (scpswrd.isEmpty()) {
                    cpassword.setError("confirm password can't be empty");
                } else if (!spswrd.matches(scpswrd)) {
                    cpassword.setError("password not match");
                    password.setError("password not match");
                } else {
                    Intent intent = new Intent(RegisterScreen.this, OTPVerifyActivity.class);
                    intent.putExtra("name",name.getText().toString());
                    intent.putExtra("password",name.getText().toString());
                    intent.putExtra("uphone",snumber);
                    startActivity(intent);
                }
            }
        });
    }
}