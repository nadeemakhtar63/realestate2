package com.example.realestate.offerscreens;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;
import com.example.realestate.RecyclerView.homesecondRecyclerview;
import com.example.realestate.modelclasses.HomeSecondModel;

import java.util.ArrayList;

public class Recived_Offer extends AppCompatActivity {
    private RecyclerView recyclerView,recyclerView2;
    ArrayList<HomeSecondModel> listofsecondhomeModel=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recived_offer);
        recyclerView2=findViewById(R.id.homefirsthorirecyclevertical);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setHasFixedSize(true);
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

}