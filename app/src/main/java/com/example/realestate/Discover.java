package com.example.realestate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.RecyclerView.homesecondRecyclerview;
import com.example.realestate.modelclasses.HomeSecondModel;

import java.util.ArrayList;

public class Discover extends AppCompatActivity {
    private RecyclerView recyclerView;
    AppCompatButton rentbotton,buing,wanted;
    ArrayList<HomeSecondModel> listofsecondhomeModel=new ArrayList<>();
    ImageView fillterdeimagebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        recyclerView=findViewById(R.id.discoverrecyclerviewsearch);
        fillterdeimagebutton=findViewById(R.id.imageView9);
        rentbotton=findViewById(R.id.appCompatButton);
        buing=findViewById(R.id.appCompatButton2);
        wanted=findViewById(R.id.appCompatButton3);
        buing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Discover.this,Buying_Property.class);
                startActivity(i);
            }
        });
        wanted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Discover.this, Wanted_Property_discover.class);
                startActivity(i);
            }
        });
        rentbotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Discover.this,Rental_Property.class);
                startActivity(i);
            }
        });
        fillterdeimagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Discover.this,FillteredActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        homesecondRecyclerview mysecondRecyclerAdapter=new homesecondRecyclerview(this,listofsecondhomeModel);
        recyclerView.setAdapter(mysecondRecyclerAdapter);
        mysecondRecyclerAdapter.notifyDataSetChanged();
        mysecondRecyclerAdapter.notifyDataSetChanged();
//        datainitilizedrecyclersecond();
    }
//    private void datainitilizedrecyclersecond() {
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Discover",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Discover",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Discover",R.drawable.intro2));

    }
