package com.example.realestate.offerscreens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;
import com.example.realestate.RecyclerView.SendOfferListAdopter;
import com.example.realestate.modelclasses.HomeSecondModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SendOffersFragment extends Fragment {
    ArrayList<HomeSecondModel> arrayList = new ArrayList<>();
    RecyclerView listRecyclerView;
    private RecyclerView.LayoutManager listLayoutManager;
    DatabaseReference mDatabaseUsers;
    FirebaseAuth mAuth;
    String imagegeturl;
    ArrayList<String> imageslist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_send_offers, container, false);
//        listRecyclerView=view.findViewById(R.id.sendoffer);
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("OfferSend");
        mDatabaseUsers.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();
//        Toast.makeText(getContext(), ""+mAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
        mDatabaseUsers.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
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

                    double minrent = (Double.parseDouble(snapshotuid.child(keyvalue).child("price").getValue().toString()));
                    double maxrent = (Double.parseDouble(snapshotuid.child(keyvalue).child("price").getValue().toString()));
                    String needfor = snapshotuid.child(keyvalue).child("need").getValue().toString();
//                    double longitut = (Double.parseDouble(snapshotuid.child(keyvalue).child("longitut").getValue().toString()));
//                    double latitud = (Double.parseDouble(snapshotuid.child(keyvalue).child("latitude").getValue().toString()));
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
                            arrayList.add(new HomeSecondModel(keyvalue, title, des, area, bath, bedrom, dateget, dimention, minrent, maxrent, needfor, "HomeActivity", 3344, 3322 , imagegeturl, keyvalue));

//                            arrayList.add(new HomeSecondModel("r8sroWUDoHZ89denWYBqnWKNMMt1","abc","def","akjjs","4","5","09/20/2022","654",3454,32333,"sell","SendOffer",322222,23333,"",""));
                            listRecyclerView = view.findViewById(R.id.sendoffer);
                            listRecyclerView.setHasFixedSize(true);
                            listLayoutManager = new LinearLayoutManager(getContext());
                            SendOfferListAdopter receivedofferListAdapter = new SendOfferListAdopter(arrayList,getContext());
                            listRecyclerView.setAdapter(receivedofferListAdapter);
                            listRecyclerView.setLayoutManager(listLayoutManager);
                            receivedofferListAdapter.notifyDataSetChanged();


//                            recyclerView = findViewById(R.id.homefirsthorirecycle);
//                            recyclerView2.setLayoutManager(new LinearLayoutManager(Send_Offer.this, RecyclerView.HORIZONTAL, false));
//                            recyclerView2.setHasFixedSize(true);
//                            homesecondRecyclerview mysecondRecyclerAdapter = new homesecondRecyclerview(Send_Offer.this, listofsecondhomeModel);
//                            recyclerView2.setAdapter(mysecondRecyclerAdapter);
//                            mysecondRecyclerAdapter.notifyDataSetChanged();
//

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

            //    private void datainitilizedrecyclersecond() {
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","recive_offer",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","recive_offer",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","recive_offer",R.drawable.intro2));
//
//    }

        });




        return  view;
    }
}