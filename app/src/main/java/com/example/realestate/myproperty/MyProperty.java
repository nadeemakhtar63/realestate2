package com.example.realestate.myproperty;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class MyProperty extends AppCompatActivity {
    private RecyclerView recyclerView,recyclerView2;
    ArrayList<String> imageslist;
    String imagegeturl;
    String imagegeturl2;
    ArrayList<HomeSecondModel> listofsecondhomeModel=new ArrayList<>();
    ArrayList<homerfirtsmodel> listofhomeModel=new ArrayList<>();
    Query query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_property);
        DatabaseReference mDatabaseUsers;
        FirebaseAuth mAuth;
        imageslist=new ArrayList<>();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("sell");
        mDatabaseUsers.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();
        recyclerView2=findViewById(R.id.myprohomefirsthorirecyclevertical);
        recyclerView=findViewById(R.id.myprohomefirsthorirecycle);
        DatabaseReference databaseReferencwanted = FirebaseDatabase.getInstance().getReference().child("wanted");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("sell");
        //query = databaseReference.child(mAuth.getCurrentUser().getUid()).equalTo("rent","need");
        databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child:snapshot.getChildren())
                {
                    String keyvalue=child.getKey();
                    String title=snapshot.child(keyvalue).child("title").getValue().toString();
                    String des=snapshot.child(keyvalue).child("des").getValue().toString();
                    String area=snapshot.child(keyvalue).child("area").getValue().toString();
                    String bath=snapshot.child(keyvalue).child("bathrooms").getValue().toString();
                    String bedrom=snapshot.child(keyvalue).child("bedrooms").getValue().toString();
                    String dateget=snapshot.child(keyvalue).child("date").getValue().toString();
                    String dimention=snapshot.child(keyvalue).child("dimention").getValue().toString();;
                    double minrent=(Double.parseDouble(snapshot.child(keyvalue).child("rent").getValue().toString()));
                    double maxrent=(Double.parseDouble(snapshot.child(keyvalue).child("rent").getValue().toString()));
                    String needfor=snapshot.child(keyvalue).child("need").getValue().toString();
                    double longitut=(Double.parseDouble(snapshot.child(keyvalue).child("longitut").getValue().toString()));
                    double latitud=(Double.parseDouble(snapshot.child(keyvalue).child("latitude").getValue().toString()));
//
//            query=databaseReference.child(keyvalue).child("imagelist");
                    DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child("sell").child(mAuth.getCurrentUser().getUid()).child(keyvalue).child("imagelist");
                    databaseReference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            for (DataSnapshot child2:snapshot2.getChildren()) {
                                String keyimages=child2.getKey();
                                imagegeturl= snapshot2.child(keyimages).child("url").getValue().toString();
                                imageslist.add(snapshot2.child(keyimages).child("url").getValue().toString());


                            }
//                    Toast.makeText(getContext(), ""+imageslist.get(0), Toast.LENGTH_SHORT).show();
                            listofsecondhomeModel.add(new HomeSecondModel(mAuth.getCurrentUser().getUid(),title,des,area,bath,bedrom,dateget,dimention,minrent,maxrent,needfor,"HomeActivity",longitut,latitud,imagegeturl,keyvalue));

                            recyclerView2.setLayoutManager(new LinearLayoutManager(MyProperty.this));
                            recyclerView2.setHasFixedSize(true);
                            homesecondRecyclerview mysecondRecyclerAdapter=new homesecondRecyclerview(MyProperty.this,listofsecondhomeModel);
                            recyclerView2.setAdapter(mysecondRecyclerAdapter);
                            mysecondRecyclerAdapter.notifyDataSetChanged();


                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MyProperty.this, "error is"+error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        datainitilized();
//        datainitilizedrecyclersecond();
        databaseReferencwanted.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child:snapshot.getChildren())
                {
                    String keyvalue=child.getKey();
                    String title=snapshot.child(keyvalue).child("title").getValue().toString();
                    String des=snapshot.child(keyvalue).child("des").getValue().toString();
                    String area=snapshot.child(keyvalue).child("area").getValue().toString();
                    String bath=snapshot.child(keyvalue).child("bathrooms").getValue().toString();
                    String bedrom=snapshot.child(keyvalue).child("bedrooms").getValue().toString();
                    String dateget=snapshot.child(keyvalue).child("date").getValue().toString();
                    String dimention=snapshot.child(keyvalue).child("dimention").getValue().toString();;
                    double minrent=(Double.parseDouble(snapshot.child(keyvalue).child("rent").getValue().toString()));
                    double maxrent=(Double.parseDouble(snapshot.child(keyvalue).child("maxrent").getValue().toString()));
                    String needfor=snapshot.child(keyvalue).child("need").getValue().toString();
                    double longitut=(Double.parseDouble(snapshot.child(keyvalue).child("longitut").getValue().toString()));
                    double latitud=(Double.parseDouble(snapshot.child(keyvalue).child("latitude").getValue().toString()));
//
//            query=databaseReference.child(keyvalue).child("imagelist");
                    DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child("sell").child(mAuth.getCurrentUser().getUid()).child(keyvalue).child("imagelist");
                    databaseReference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            for (DataSnapshot child2:snapshot2.getChildren()) {
                                String keyimages=child2.getKey();
                                imagegeturl= snapshot2.child(keyimages).child("url").getValue().toString();
                                imageslist.add(snapshot2.child(keyimages).child("url").getValue().toString());


                            }
//                    Toast.makeText(getContext(), ""+imageslist.get(0), Toast.LENGTH_SHORT).show();
                            listofhomeModel.add(new homerfirtsmodel(""+title,""+des,""+area,""+bath,""+bedrom,
                                    ""+dateget,""+dimention,minrent,maxrent,""+needfor,"HomeActivity",longitut,latitud,""+keyvalue));

                            recyclerView.setLayoutManager(new LinearLayoutManager(MyProperty.this,LinearLayoutManager.HORIZONTAL,false));
                            recyclerView.setHasFixedSize(true);
                            homerecyclerview myRecyclerAdapter=new homerecyclerview(MyProperty.this,listofhomeModel);
                            recyclerView.setAdapter(myRecyclerAdapter);
                            myRecyclerAdapter.notifyDataSetChanged();


                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MyProperty.this, "error is"+error.toString(), Toast.LENGTH_SHORT).show();
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