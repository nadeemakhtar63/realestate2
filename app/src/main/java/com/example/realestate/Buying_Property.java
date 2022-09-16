package com.example.realestate;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.Fragments.rental_bottom_sheet;
import com.example.realestate.RecyclerView.homesecondRecyclerview;
import com.example.realestate.modelclasses.HomeSecondModel;
import com.example.realestate.modelclasses.homerfirtsmodel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Buying_Property extends AppCompatActivity {
    private RecyclerView recyclerView, recyclerView2;
    ArrayList<HomeSecondModel> listofsecondhomeModel = new ArrayList<>();
    ConstraintLayout layoutclick;
    ArrayList<homerfirtsmodel> listofhomeModel = new ArrayList<>();
    ArrayList<String> imageslist;
    String imagegeturl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying_property);
        layoutclick = findViewById(R.id.sheet);
//        datainitilizedrecyclersecond();

        DatabaseReference mDatabaseUsers;
        FirebaseAuth mAuth;
        imageslist = new ArrayList<>();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("sell");
        mDatabaseUsers.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseReferencwanted = FirebaseDatabase.getInstance().getReference().child("wanted");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("sell");
        //query = databaseReference.child(mAuth.getCurrentUser().getUid()).equalTo("rent","need");
        databaseReference.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        for (DataSnapshot child : snapshot.getChildren()) {
                                                            String keyvalue1 = child.getKey();
                                                            databaseReference.child(keyvalue1).addValueEventListener(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshotuid) {
                                                                    for (DataSnapshot childuid : snapshotuid.getChildren()) {

                                                                        String keyvalue = childuid.getKey();
                                                                        String title = snapshotuid.child(keyvalue).child("title").getValue().toString();
                                                                        String des = snapshotuid.child(keyvalue).child("des").getValue().toString();
                                                                        String area = snapshotuid.child(keyvalue).child("area").getValue().toString();
                                                                        String bath = snapshotuid.child(keyvalue).child("bathrooms").getValue().toString();
                                                                        String bedrom = snapshotuid.child(keyvalue).child("bedrooms").getValue().toString();
                                                                        String dateget = snapshotuid.child(keyvalue).child("date").getValue().toString();
                                                                        String dimention = snapshotuid.child(keyvalue).child("dimention").getValue().toString();
                                                                        ;
                                                                        double minrent = (Double.parseDouble(snapshotuid.child(keyvalue).child("minrent").getValue().toString()));
                                                                        double maxrent = (Double.parseDouble(snapshotuid.child(keyvalue).child("maxrent").getValue().toString()));
                                                                        String needfor = snapshotuid.child(keyvalue).child("need").getValue().toString();
                                                                        double longitut = (Double.parseDouble(snapshotuid.child(keyvalue).child("longitut").getValue().toString()));
                                                                        double latitud = (Double.parseDouble(snapshotuid.child(keyvalue).child("latitude").getValue().toString()));
//
//            query=databaseReference.child(keyvalue).child("imagelist");
                                                                        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child("sell").child(keyvalue1).child(keyvalue).child("imagelist");
                                                                        databaseReference2.addValueEventListener(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                                                                for (DataSnapshot child2 : snapshot2.getChildren()) {
                                                                                    String keyimages = child2.getKey();
                                                                                    imagegeturl = snapshot2.child(keyimages).child("url").getValue().toString();
                                                                                    imageslist.add(snapshot2.child(keyimages).child("url").getValue().toString());
                                                                                }
//                    Toast.makeText(getContext(), ""+imageslist.get(0), Toast.LENGTH_SHORT).show();
                                                                                listofsecondhomeModel.add(new HomeSecondModel(keyvalue1, title, des, area, bath, bedrom, dateget, dimention, minrent, maxrent, needfor, "HomeActivity", longitut, latitud, imagegeturl, keyvalue));
                                                                                recyclerView2 = findViewById(R.id.homefirsthorirecycle);
                                                                                recyclerView2.setLayoutManager(new LinearLayoutManager(Buying_Property.this, RecyclerView.HORIZONTAL, false));
                                                                                recyclerView2.setHasFixedSize(true);
                                                                                homesecondRecyclerview mysecondRecyclerAdapter = new homesecondRecyclerview(Buying_Property.this, listofsecondhomeModel);
                                                                                recyclerView2.setAdapter(mysecondRecyclerAdapter);
                                                                                mysecondRecyclerAdapter.notifyDataSetChanged();
                                                                            }

                                                                            @Override
                                                                            public void onCancelled(@NonNull DatabaseError error) {
                                                                                Toast.makeText(Buying_Property.this, "error is" + error.toString(), Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        });


                                                                    }


                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });
                                                            layoutclick.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                    showBottomSheetDialog();

                                                                }
                                                            });
                                                        }
                                                    }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            private void showBottomSheetDialog() {
                                                        rental_bottom_sheet bottomsheetfragment = new rental_bottom_sheet("BUYING", "PROPERTY", "sell", "sell");
                                                        bottomsheetfragment.show(getSupportFragmentManager(), bottomsheetfragment.getTag());

                                                    }
                                                }
        );
    }
}