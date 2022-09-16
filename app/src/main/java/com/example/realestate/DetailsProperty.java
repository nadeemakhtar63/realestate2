package com.example.realestate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.Getsellerimages.RecyclerAdaptor;
import com.example.realestate.RecyclerView.ImageListRecyclerview;
import com.example.realestate.modelclasses.ImagesshowModelClass;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetailsProperty extends AppCompatActivity {
    RecyclerView recyclerView;
    Button offerbtn;
    private DatabaseReference mDatabaseUsers;
    FirebaseDatabase database;
    RecyclerAdaptor adaptor;
    private FirebaseAuth mAuth;
    EditText homename, priceoffersbid;
    ImageView imageshow;
    TextView deialName;
    TextView bedroom;
    TextView bathroom;
    TextView dimentions;
    TextView address;
    String location;
    TextView details;
    TextView price;
    ArrayList<String> imageslist;
    String imagegeturl;
    StorageReference storageReference;
    List<String> linkImages = new ArrayList<>();
    String priceval;
    ArrayList<ImagesshowModelClass> allimageslist = new ArrayList<>();
    ArrayList<String> listimages;
    ProgressDialog progressDialog;
    double loc;
    String propertyname;
    String bathroominfo;
    String bedroominfo;
    String dimin;
    String desc;
    String priceget;
    String docid;
    String propertytype;
    String area;
    String longstringget,latstringget,locationstringget;
    String need;
    String lng;
    String lat;
    String do_id;
    String reciverid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_property);
        DatabaseReference mDatabaseUsers;

        database = FirebaseDatabase.getInstance();
        imageslist = new ArrayList<>();
        deialName = findViewById(R.id.textView23);
        bedroom = findViewById(R.id.textbedrooms);
        bathroom = findViewById(R.id.textView24);
        dimentions = findViewById(R.id.sqfit);
        address = findViewById(R.id.houselocationtext);
//        location=findViewById(R.id.textView23);
        details = findViewById(R.id.textView30);
        price = findViewById(R.id.detailsprice);
        recyclerView = findViewById(R.id.imagedetailredesign);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Wait..Data Uploading...");
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        area=intent.getStringExtra("area");
        need=intent.getStringExtra("need");
        location=intent.getStringExtra("location");
        lat=intent.getStringExtra("lati");
        lng=intent.getStringExtra("long");
        reciverid=intent.getStringExtra("reciver_id");
        do_id=intent.getStringExtra("doc_id");
//        loc =Double.parseDouble(locationstringget);
        propertyname = intent.getStringExtra("nameproperty");
//      String imageget= intent.getStringExtra("proimage");
        bathroominfo = intent.getStringExtra("bathroom");
        bedroominfo = intent.getStringExtra("bedroom");
        dimin = intent.getStringExtra("dim");
        desc = intent.getStringExtra("desc");
        priceget = intent.getStringExtra("price");
        String activityinfo = intent.getStringExtra("activity");
        docid = intent.getStringExtra("docid");
        propertytype=intent.getStringExtra("PropertyType");
        deialName.setText(propertyname);
        bedroom.setText(bedroominfo + " Bedroom");
        bathroom.setText(bathroominfo + " Bathroom");
        dimentions.setText(dimin + "m");
        details.setText(desc);
//        listimages = (ArrayList<Uri>) getIntent().getSerializableExtra("listimage");
        price.setText(priceget);
        offerbtn = findViewById(R.id.button3);
        priceoffersbid = findViewById(R.id.priceoffer);
        priceoffersbid.setVisibility(View.GONE);

        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child("sell").child(reciverid).child(docid).child("imagelist");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot2) {
                for (DataSnapshot child2 : snapshot2.getChildren()) {
                    String keyimages = child2.getKey();
                    imagegeturl = snapshot2.child(keyimages).child("url").getValue().toString();
                    imageslist.add(snapshot2.child(keyimages).child("url").getValue().toString());
                    Toast.makeText(DetailsProperty.this, "" + imageslist.get(0), Toast.LENGTH_SHORT).show();
                    allimageslist.add(new ImagesshowModelClass(imagegeturl));
                   // listimages=allimageslist;
                }
                recyclerView.setHasFixedSize(true);
                ImageListRecyclerview myRecyclerAdapter = new ImageListRecyclerview(allimageslist, DetailsProperty.this);
                recyclerView.setAdapter(myRecyclerAdapter);
                myRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DetailsProperty.this, "error is" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

//      if (activityinfo.equals("recive_offer"))
//      {
//          priceoffersbid.setVisibility(View.VISIBLE);
//      }
        offerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });
    }

    private void showBottomSheetDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.send_offer);
        EditText getpricedilog = bottomSheetDialog.findViewById(R.id.editText2);
        ConstraintLayout wanted, seller;
        seller = bottomSheetDialog.findViewById(R.id.sendofferdilog);
        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priceval = getpricedilog.getText().toString().trim();
                if (priceval.isEmpty()) {
                    getpricedilog.setError("Enter Offer Price");
                } else {
                    saveData();
//                    UploadImageFileToFirebaseStorage();
                 }
            }
        });


        bottomSheetDialog.show();
    }

    public void detailonback(View view) {
        finish();
    }

    private void saveData() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());
//        if (imageslist.size() == linkImages.size()) {
        DatabaseReference mNewRef = database.getReference("OfferRecive").child(reciverid).push();
        mNewRef.child("title").setValue(propertyname);
        mNewRef.child("des").setValue(desc);
        mNewRef.child("date").setValue(strDate);
        mNewRef.child("area").setValue(area);
        mNewRef.child("dimention").setValue(dimin);
        mNewRef.child("price").setValue(priceval);
        mNewRef.child("mapaddres").setValue(loc);
        mNewRef.child("latitude").setValue(lat);
        mNewRef.child("longitut").setValue(lng);
        mNewRef.child("property_Type").setValue(propertytype);
        mNewRef.child("bedrooms").setValue(bedroominfo);
        mNewRef.child("bathrooms").setValue(bathroominfo);
        mNewRef.child("need").setValue(need);
        mNewRef.child("purchaser_offer").setValue(priceval);
        for (int i = 0; i < allimageslist.size(); i++) {
            mNewRef.child("imagelist").push().child("url").setValue(allimageslist.get(i));
        }
        DatabaseReference mNewRef2 = database.getReference("OfferSend").child(mAuth.getCurrentUser().getUid()).push();
        mNewRef2.child("title").setValue(propertyname);
        mNewRef2.child("des").setValue(desc);
        mNewRef2.child("date").setValue(strDate);
        mNewRef2.child("area").setValue(area);
        mNewRef2.child("dimention").setValue(dimin);
        mNewRef2.child("price").setValue(priceval);
        mNewRef2.child("mapaddres").setValue(loc);
        mNewRef2.child("latitude").setValue(lat);
        mNewRef2.child("longitut").setValue(lng);
        mNewRef2.child("property_Type").setValue(propertytype);
        mNewRef2.child("bedrooms").setValue(bedroominfo);
        mNewRef2.child("bathrooms").setValue(bathroominfo);
        mNewRef2.child("need").setValue(need);
        mNewRef2.child("purchaser_offer").setValue(priceval);
        for (int i = 0; i < allimageslist.size(); i++) {
            mNewRef2.child("imagelist").push().child("url").setValue(allimageslist.get(i));
        }
        Intent intent = new Intent(this, HomeNav.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            Toast.makeText(DetailsProperty.this, "Offer Send Successfully", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
//        }
    }



}