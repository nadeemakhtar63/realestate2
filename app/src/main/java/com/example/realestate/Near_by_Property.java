package com.example.realestate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.RecyclerView.homesecondRecyclerview;
import com.example.realestate.modelclasses.HomeSecondModel;
import com.example.realestate.modelclasses.homerfirtsmodel;

import java.util.ArrayList;

public class Near_by_Property extends AppCompatActivity {
    private RecyclerView recyclerView,recyclerView2;
    ArrayList<HomeSecondModel> listofsecondhomeModel=new ArrayList<>();
    ArrayList<homerfirtsmodel> listofhomeModel=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_property);
        recyclerView2=findViewById(R.id.homefirsthorirecyclevertical);
        recyclerView=findViewById(R.id.homefirsthorirecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
//        homerecyclerview myRecyclerAdapter=new homerecyclerview(this,listofhomeModel);
//        recyclerView.setAdapter(myRecyclerAdapter);
//        myRecyclerAdapter.notifyDataSetChanged();
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setHasFixedSize(true);
        homesecondRecyclerview mysecondRecyclerAdapter=new homesecondRecyclerview(this,listofsecondhomeModel);
        recyclerView2.setAdapter(mysecondRecyclerAdapter);
        mysecondRecyclerAdapter.notifyDataSetChanged();

    }
//    private void datainitilizedrecyclersecond() {
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Near_By_Property",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Near_By_Property",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Near_By_Property",R.drawable.intro2));
//
//    }
//    private void datainitilized() {
//        listofhomeModel.add(new homerfirtsmodel("jshff","fdkfkd","jfdjf",R.drawable.intro1));
//        listofhomeModel.add(new homerfirtsmodel("jshff","fdkfkd","jfdjf",R.drawable.intro1));
//        listofhomeModel.add(new homerfirtsmodel("jshff","fdkfkd","jfdjf",R.drawable.intro1));
//        listofhomeModel.add(new homerfirtsmodel("jshff","fdkfkd","jfdjf",R.drawable.intro1));
//        listofhomeModel.add(new homerfirtsmodel("jshff","fdkfkd","jfdjf",R.drawable.intro1));
//        listofhomeModel.add(new homerfirtsmodel("jshff","fdkfkd","jfdjf",R.drawable.intro1));
//        listofhomeModel.add(new homerfirtsmodel("jshff","fdkfkd","jfdjf",R.drawable.intro1));
//        listofhomeModel.add(new homerfirtsmodel("jshff","fdkfkd","jfdjf",R.drawable.intro1));
//        listofhomeModel.add(new homerfirtsmodel("jshff","fdkfkd","jfdjf",R.drawable.intro1));
//        listofhomeModel.add(new homerfirtsmodel("jshff","fdkfkd","jfdjf",R.drawable.intro1));
//        listofhomeModel.add(new homerfirtsmodel("jshff","fdkfkd","jfdjf",R.drawable.intro1));
//        listofhomeModel.add(new homerfirtsmodel("jshff","fdkfkd","jfdjf",R.drawable.intro1));
//        listofhomeModel.add(new homerfirtsmodel("jshff","fdkfkd","jfdjf",R.drawable.intro1));
//
////        }
//    }
}