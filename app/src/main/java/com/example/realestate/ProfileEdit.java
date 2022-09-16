package com.example.realestate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileEdit extends AppCompatActivity {
    ImageView getimage,profileaddimage;
    int RESULT_LOAD_IMG=0;
    Button btnregister;
    private  String suname,snumber,spswrd,scpswrd;
    private TextInputEditText name,phone,address,deliveryadres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        profileaddimage=findViewById(R.id.circleImageView);
        getimage=findViewById(R.id.addprofileimage);
        name=findViewById(R.id.username);
        phone=findViewById(R.id.phoneNumber);
        address=findViewById(R.id.pswrd);
        deliveryadres=findViewById(R.id.cpass);



        getimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });


        btnregister=findViewById(R.id.btn_regcontinue);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        suname = name.getText().toString();
        snumber = phone.getText().toString();
        spswrd = address.getText().toString();
        scpswrd = deliveryadres.getText().toString();
        if (suname.isEmpty()) {
            name.setError("Enter Name");
        } else if (snumber.isEmpty()) {
            phone.setError("Enter Mobile Number");
        } else if (snumber.length() < 11) {
            phone.setError("Invalid Number");
        } else if (spswrd.isEmpty()) {
            address.setError("Address can't be empty");
        } else if (scpswrd.isEmpty()) {
            deliveryadres.setError("Delivery Address empty");
        } else {
            Intent intent = new Intent(ProfileEdit.this, OTPVerifyActivity.class);
            intent.putExtra("uphone",snumber);
            intent.putExtra("update","updatescreen");
            startActivity(intent);
        }
    }
});
    }
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                profileaddimage.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(ProfileEdit.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(ProfileEdit.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}