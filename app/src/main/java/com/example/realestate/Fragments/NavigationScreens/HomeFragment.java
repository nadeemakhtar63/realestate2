package com.example.realestate.Fragments.NavigationScreens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;
import com.example.realestate.RecyclerView.homerecyclerview;
import com.example.realestate.RecyclerView.homesecondRecyclerview;
import com.example.realestate.modelclasses.HomeSecondModel;
import com.example.realestate.modelclasses.homerfirtsmodel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView,recyclerView2;
    ArrayList<String> imageslist;
    String imagegeturl;
    String imagegeturl2;
    ArrayList<HomeSecondModel> listofsecondhomeModel=new ArrayList<>();
    ArrayList<homerfirtsmodel> listofhomeModel=new ArrayList<>();
    Query query;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.home_fragment, container, false);
        DatabaseReference mDatabaseUsers;
        FirebaseAuth mAuth;
        imageslist=new ArrayList<>();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("sell");
        mDatabaseUsers.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();
        recyclerView2=view.findViewById(R.id.homefirsthorirecyclevertical);
        recyclerView=view.findViewById(R.id.homefirsthorirecycle);
        DatabaseReference databaseReferencwanted = FirebaseDatabase.getInstance().getReference().child("wanted");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("sell");
        //query = databaseReference.child(mAuth.getCurrentUser().getUid()).equalTo("rent","need");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot child:snapshot.getChildren())
            {
                String keyvalue1=child.getKey();
                databaseReference.child(keyvalue1).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshotuid) {
                        for (DataSnapshot childuid:snapshotuid.getChildren()) {

            String keyvalue=childuid.getKey();
            String title=snapshotuid.child(keyvalue).child("title").getValue().toString();
            String des=snapshotuid.child(keyvalue).child("des").getValue().toString();
            String area=snapshotuid.child(keyvalue).child("area").getValue().toString();
            String bath=snapshotuid.child(keyvalue).child("bathrooms").getValue().toString();
            String bedrom=snapshotuid.child(keyvalue).child("bedrooms").getValue().toString();
            String dateget=snapshotuid.child(keyvalue).child("date").getValue().toString();
            String dimention=snapshotuid.child(keyvalue).child("dimention").getValue().toString();;
            double minrent=(Double.parseDouble(snapshotuid.child(keyvalue).child("minrent").getValue().toString()));
           double maxrent=(Double.parseDouble(snapshotuid.child(keyvalue).child("maxrent").getValue().toString()));
            String needfor=snapshotuid.child(keyvalue).child("need").getValue().toString();
            double longitut=(Double.parseDouble(snapshotuid.child(keyvalue).child("longitut").getValue().toString()));
            double latitud=(Double.parseDouble(snapshotuid.child(keyvalue).child("latitude").getValue().toString()));
//
//            query=databaseReference.child(keyvalue).child("imagelist");
                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child("sell").child(keyvalue1).child(keyvalue).child("imagelist");
            databaseReference2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot2) {
                    for (DataSnapshot child2:snapshot2.getChildren()) {
                            String keyimages=child2.getKey();
                        imagegeturl= snapshot2.child(keyimages).child("url").getValue().toString();
                         imageslist.add(snapshot2.child(keyimages).child("url").getValue().toString());


                    }
//                    Toast.makeText(getContext(), ""+imageslist.get(0), Toast.LENGTH_SHORT).show();
                    listofsecondhomeModel.add(new HomeSecondModel(keyvalue1,title,des,area,bath,bedrom,dateget,dimention,minrent,maxrent,needfor,"HomeActivity",longitut,latitud,imagegeturl,keyvalue));

                    recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView2.setHasFixedSize(true);
                    homesecondRecyclerview mysecondRecyclerAdapter=new homesecondRecyclerview(getContext(),listofsecondhomeModel);
                    recyclerView2.setAdapter(mysecondRecyclerAdapter);
                    mysecondRecyclerAdapter.notifyDataSetChanged();


                }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getContext(), "error is"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                    });


            }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

            }
            });
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReferencwanted.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot2) {
                for (DataSnapshot child2 : snapshot2.getChildren()) {
                    String keyvalue = child2.getKey();
                    databaseReferencwanted.child(keyvalue).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                String keyvalue2 = child.getKey();
                                String title = snapshot.child(keyvalue2).child("title").getValue().toString();
                                String des = snapshot.child(keyvalue2).child("des").getValue().toString();
                                String area = snapshot.child(keyvalue2).child("area").getValue().toString();
                                String bath = snapshot.child(keyvalue2).child("bathrooms").getValue().toString();
                                String bedrom = snapshot.child(keyvalue2).child("bedrooms").getValue().toString();
                                String dateget = snapshot.child(keyvalue2).child("date").getValue().toString();
                                String dimention = snapshot.child(keyvalue2).child("dimention").getValue().toString();
                                ;
                                double minrent =(Double.parseDouble( snapshot.child(keyvalue2).child("minrent").getValue().toString()));
                                double maxrent =(Double.parseDouble( snapshot.child(keyvalue2).child("maxrent").getValue().toString()));
                                String needfor = snapshot.child(keyvalue2).child("need").getValue().toString();
                                double longitut = (Double.parseDouble(snapshot.child(keyvalue2).child("longitut").getValue().toString()));
                                double latitud = (Double.parseDouble(snapshot.child(keyvalue2).child("latitude").getValue().toString()));
                                //
//            query=databaseReference.child(keyvalue).child("imagelist");
                                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child("sell").child(mAuth.getCurrentUser().getUid()).child(keyvalue).child("imagelist");
                                databaseReference2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        for (DataSnapshot child2 : snapshot2.getChildren()) {
                                            String keyimages = child2.getKey();
                                            imagegeturl = snapshot2.child(keyimages).child("url").getValue().toString();
                                            imageslist.add(snapshot2.child(keyimages).child("url").getValue().toString());


                                        }
//                    Toast.makeText(getContext(), ""+imageslist.get(0), Toast.LENGTH_SHORT).show();
                                        listofhomeModel.add(new homerfirtsmodel("" + title, "" + des, "" + area, "" + bath, "" + bedrom,
                                                "" + dateget, "" + dimention,  minrent,  maxrent, "" + needfor, "HomeActivity", longitut, latitud, "" + keyvalue));

                                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                                        recyclerView.setHasFixedSize(true);
                                        homerecyclerview myRecyclerAdapter = new homerecyclerview(getContext(), listofhomeModel);
                                        recyclerView.setAdapter(myRecyclerAdapter);
                                        myRecyclerAdapter.notifyDataSetChanged();


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(getContext(), "error is" + error.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });


                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        return  view;
    }
}