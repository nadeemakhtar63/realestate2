package com.example.realestate.Fragments;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;
import com.example.realestate.RecyclerView.homerecyclerview;
import com.example.realestate.RecyclerView.homesecondRecyclerview;
import com.example.realestate.modelclasses.HomeSecondModel;
import com.example.realestate.modelclasses.homerfirtsmodel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class rental_bottom_sheet extends BottomSheetDialogFragment {
    BottomSheetDialog dialog;
    String fname,sname,dataCondition,sellOrRent;
    View rootview;
    BottomSheetBehavior<View> bottomSheetBehavior;
    private RecyclerView recyclerView,recyclerView2;
    ArrayList<HomeSecondModel> listofsecondhomeModel=new ArrayList<>();
    ArrayList<homerfirtsmodel> listofhomeModel=new ArrayList<>();
    ArrayList<String> imageslist;
    String imagegeturl;
    String imagegeturl2;
    public rental_bottom_sheet(String fname, String sname,String datagetCondition,String sellOrwanted) {
        this.fname = fname;
        this.sname = sname;
        this.dataCondition=datagetCondition;
        this.sellOrRent=sellOrwanted;
    }

    @NonNull
    @Override

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog= (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

    rootview=inflater.inflate(R.layout.rental_resource_design,container,false);
        TextView firstname=rootview.findViewById(R.id.textView6);
        TextView secondname=rootview.findViewById(R.id.textView7);
        firstname.setText(fname);
        secondname.setText(sname);
        ImageView backpress=rootview.findViewById(R.id.imageView);
            backpress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

//    datainitilized();
//        datainitilizedrecyclersecond();
        recyclerView2=rootview.findViewById(R.id.homefirsthorirecyclevertical);
        recyclerView=rootview.findViewById(R.id.homefirsthorirecycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        homerecyclerview myRecyclerAdapter=new homerecyclerview(getContext(),listofhomeModel);
        recyclerView.setAdapter(myRecyclerAdapter);
        myRecyclerAdapter.notifyDataSetChanged();
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        recyclerView2.setHasFixedSize(true);
        homesecondRecyclerview mysecondRecyclerAdapter=new homesecondRecyclerview(getContext(),listofsecondhomeModel);
        recyclerView2.setAdapter(mysecondRecyclerAdapter);
        mysecondRecyclerAdapter.notifyDataSetChanged();


        DatabaseReference mDatabaseUsers;
        FirebaseAuth mAuth;
        imageslist=new ArrayList<>();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child(sellOrRent);
        mDatabaseUsers.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseReferencwanted = FirebaseDatabase.getInstance().getReference().child(sellOrRent);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(sellOrRent);
        //query = databaseReference.child(mAuth.getCurrentUser().getUid()).equalTo("rent","need");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    String keyvalue1 = child.getKey();

                    databaseReference.child(keyvalue1).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                String keyvalue = child.getKey();
                                String title = snapshot.child(keyvalue).child("title").getValue().toString();
                                String des = snapshot.child(keyvalue).child("des").getValue().toString();
                                String area = snapshot.child(keyvalue).child("area").getValue().toString();
                                String bath = snapshot.child(keyvalue).child("bathrooms").getValue().toString();
                                String bedrom = snapshot.child(keyvalue).child("bedrooms").getValue().toString();
                                String dateget = snapshot.child(keyvalue).child("date").getValue().toString();
                                String dimention = snapshot.child(keyvalue).child("dimention").getValue().toString();
                                ;
                                double minrent = (Double.parseDouble(snapshot.child(keyvalue).child("minrent").getValue().toString()));
                                double maxrent = (Double.parseDouble(snapshot.child(keyvalue).child("maxrent").getValue().toString()));
                                String needfor = snapshot.child(keyvalue).child("need").getValue().toString();
                                double longitut = (Double.parseDouble(snapshot.child(keyvalue).child("longitut").getValue().toString()));
                                double latitud = (Double.parseDouble(snapshot.child(keyvalue).child("latitude").getValue().toString()));
//
//            query=databaseReference.child(keyvalue).child("imagelist");
                                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child(sellOrRent).child(keyvalue1).child(keyvalue).child("imagelist");
                                databaseReference2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                        for (DataSnapshot child2 : snapshot2.getChildren()) {
                                            String keyimages = child2.getKey();
                                            imagegeturl = snapshot2.child(keyimages).child("url").getValue().toString();
                                            imageslist.add(snapshot2.child(keyimages).child("url").getValue().toString());


                                        }
//                    Toast.makeText(getContext(), ""+imageslist.get(0), Toast.LENGTH_SHORT).show();
                                        listofsecondhomeModel.add(new HomeSecondModel(keyvalue1,title, des, area, bath, bedrom, dateget, dimention, minrent, maxrent, needfor, "HomeActivity", longitut, latitud, imagegeturl, keyvalue));

                                        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                                        recyclerView2.setHasFixedSize(true);
                                        homesecondRecyclerview mysecondRecyclerAdapter = new homesecondRecyclerview(getContext(), listofsecondhomeModel);
                                        recyclerView2.setAdapter(mysecondRecyclerAdapter);
                                        mysecondRecyclerAdapter.notifyDataSetChanged();


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
//        datainitilized();
//        datainitilizedrecyclersecond();
                    databaseReferencwanted.child(mAuth.getCurrentUser().getUid()).orderByChild("need").equalTo(dataCondition).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                String keyvalue = child.getKey();
                                String title = snapshot.child(keyvalue).child("title").getValue().toString();
                                String des = snapshot.child(keyvalue).child("des").getValue().toString();
                                String area = snapshot.child(keyvalue).child("area").getValue().toString();
                                String bath = snapshot.child(keyvalue).child("bathrooms").getValue().toString();
                                String bedrom = snapshot.child(keyvalue).child("bedrooms").getValue().toString();
                                String dateget = snapshot.child(keyvalue).child("date").getValue().toString();
                                String dimention = snapshot.child(keyvalue).child("dimention").getValue().toString();
                                ;
                                double minrent = (Double.parseDouble(snapshot.child(keyvalue).child("minrent").getValue().toString()));
                                double maxrent = (Double.parseDouble(snapshot.child(keyvalue).child("maxrent").getValue().toString()));
                                String needfor = snapshot.child(keyvalue).child("need").getValue().toString();
                                double longitut = (Double.parseDouble(snapshot.child(keyvalue).child("longitut").getValue().toString()));
                                double latitud = (Double.parseDouble(snapshot.child(keyvalue).child("latitude").getValue().toString()));
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
                                                "" + dateget, "" + dimention, minrent, maxrent, "" + needfor, "HomeActivity", longitut, latitud, "" + keyvalue));

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


        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomSheetBehavior=BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        ConstraintLayout layout=dialog.findViewById(R.id.bottomsheetlayout);
        assert  layout!=null;
        layout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
    }
}
