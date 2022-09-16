package com.example.realestate.Fragments.NavigationScreens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;
import com.example.realestate.RecyclerView.homesecondRecyclerview;
import com.example.realestate.modelclasses.HomeSecondModel;
import com.example.realestate.modelclasses.homerfirtsmodel;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    private RecyclerView recyclerView,recyclerView2;
    ArrayList<HomeSecondModel> listofsecondhomeModel=new ArrayList<>();
    ArrayList<homerfirtsmodel> listofhomeModel=new ArrayList<>();

    public ChatFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_chat, container, false);
//        datainitilized();
//        datainitilizedrecyclersecond();
        recyclerView2=view.findViewById(R.id.homefirsthorirecyclevertical);
        recyclerView=view.findViewById(R.id.homefirsthorirecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
//        homerecyclerview myRecyclerAdapter=new homerecyclerview(getContext(),listofhomeModel);
//        recyclerView.setAdapter(myRecyclerAdapter);
//        myRecyclerAdapter.notifyDataSetChanged();


        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setHasFixedSize(true);
        homesecondRecyclerview mysecondRecyclerAdapter=new homesecondRecyclerview(getContext(),listofsecondhomeModel);
        recyclerView2.setAdapter(mysecondRecyclerAdapter);
        mysecondRecyclerAdapter.notifyDataSetChanged();

        return  view;
    }

//    private void datainitilizedrecyclersecond() {
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Favorite",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Favorite",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Favorite",R.drawable.intro2));

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