package com.example.realestate.offerscreens;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;
import com.example.realestate.RecyclerView.homesecondRecyclerview;
import com.example.realestate.modelclasses.HomeSecondModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Send_Offer extends AppCompatActivity {
    private RecyclerView recyclerView,recyclerView2;
    DatabaseReference mDatabaseUsers;
    FirebaseAuth mAuth;
    ArrayList<HomeSecondModel> listofsecondhomeModel=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_offer);
        recyclerView2=findViewById(R.id.homefirsthorirecyclevertical);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setHasFixedSize(true);
       // mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("sell").child(mAuth.cu);
        mDatabaseUsers.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();




        homesecondRecyclerview mysecondRecyclerAdapter=new homesecondRecyclerview(this,listofsecondhomeModel);
        recyclerView2.setAdapter(mysecondRecyclerAdapter);
        mysecondRecyclerAdapter.notifyDataSetChanged();
//        datainitilizedrecyclersecond();
    }
//    private void datainitilizedrecyclersecond() {
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","recive_offer",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","recive_offer",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","recive_offer",R.drawable.intro2));
//
//    }
    public void offersendback(View view) {
        finish();
    }
}