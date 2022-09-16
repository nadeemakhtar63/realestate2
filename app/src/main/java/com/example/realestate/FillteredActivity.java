package com.example.realestate;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.RecyclerView.homesecondRecyclerview;
import com.example.realestate.modelclasses.HomeSecondModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FillteredActivity extends AppCompatActivity {
        ImageView demo,cumer,plot,aportment;
        String selected="Not Selected";
        String buyOrSell="NotSelect";
        Button btnsubmit;
        AppCompatButton buy,rent;
        EditText searchtext;
    ArrayList<HomeSecondModel> listofsecondhomeModel=new ArrayList<HomeSecondModel>();
    ArrayList<String> imageslist;
    String imagegeturl;
    private RecyclerView recyclerView;
    int minrent,maxrent;
    TextInputLayout dropdowntextget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filltered);
        String[] type = new String[] {"RS: 50,000-100,000", "RS: 100,000-1,50,000", "RS: 1,50,000-2,00,000", "RS: 2,00,000-2,50,000","RS: 2,50,000 - 3,00,000"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, type);
        AutoCompleteTextView editTextFilledExposedDropdown = findViewById(R.id.filled_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);

//        Toast.makeText(this, ""+editTextFilledExposedDropdown.getText().toString(), Toast.LENGTH_SHORT).show();
        searchtext=findViewById(R.id.editText);
        btnsubmit=findViewById(R.id.btnsubmit);
        dropdowntextget=findViewById(R.id.textInputLayout);
        buy=findViewById(R.id.appCompatButton4);
        rent=findViewById(R.id.appCompatButton5);
        demo=findViewById(R.id.imageButton2);
        cumer=findViewById(R.id.imageButton3);
        plot=findViewById(R.id.imageButton4);
        aportment=findViewById(R.id.imageButton5);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyOrSell="buy";
                buy.setBackground(getResources().getDrawable(R.drawable.propertysellcluickonchange));
                rent.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
            }
        });
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyOrSell="rent";
                rent.setBackground(getResources().getDrawable(R.drawable.propertysellcluickonchange));
                buy.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));

            }
        });
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected="Demonstrative";
                demo.setBackground(getResources().getDrawable(R.drawable.propertysellcluickonchange));
                cumer.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                plot.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                aportment.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
            }
        });
        cumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected="commercial";
                cumer.setBackground(getResources().getDrawable(R.drawable.propertysellcluickonchange));
                demo.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                plot.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                aportment.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
            }
        });
        plot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected="Plot";
                plot.setBackground(getResources().getDrawable(R.drawable.propertysellcluickonchange));
                cumer.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                demo.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                aportment.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
            }
        });
        aportment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected="Apportment";
                aportment.setBackground(getResources().getDrawable(R.drawable.propertysellcluickonchange));
                cumer.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                plot.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                demo.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
            }
        });
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchtet,dropdowntextgetval;
                searchtet=searchtext.getText().toString().trim();
                dropdowntextgetval=editTextFilledExposedDropdown.getText().toString();
//                Toast.makeText(FillteredActivity.this, ""+dropdowntextgetval, Toast.LENGTH_SHORT).show();
                if(searchtet.isEmpty()){
                    searchtext.setError("Search Property");
                }
                else if(selected=="Not Selected")
                {
                    Toast.makeText(FillteredActivity.this, "Please Select Property Type", Toast.LENGTH_SHORT).show();
                }
                else  if (buyOrSell=="NotSelect")
                {
                    Toast.makeText(FillteredActivity.this, "Please Select anyone from rent or buy", Toast.LENGTH_SHORT).show();
                }
                else if(dropdowntextgetval.isEmpty())
                {
                    dropdowntextget.setError("Please Select Range");
                }
                else {
//                    , , "RS: 1,50,000-2,00,000", "RS: 2,00,000-2,50,000","RS: 2,50,000 - 3,00,000"
                    if(dropdowntextgetval.equals("RS: 50,000-100,000"))
                    {
                        minrent=50000;
                        maxrent=100000;
                    }
                    else  if(dropdowntextgetval.equals("RS: 100,000-1,50,000"))
                    {
                        minrent=100000;
                        maxrent=150000;
                    }
                    else  if(dropdowntextgetval.equals("RS: 1,50,000-2,00,000"))
                    {
                        minrent=150000;
                        maxrent=200000;
                    }
                    else  if(buyOrSell.equals("RS: 2,00,000-2,50,000"))
                    {
                        minrent=200000;
                        maxrent=250000;
                    }
                    else  if(dropdowntextgetval.equals("RS: 2,50,000 - 3,00,000"))
                    {
                        minrent=300000;
                        maxrent=350000;
                    }
//                    Toast.makeText(FillteredActivity.this, "buyorsell is:"+buyOrSell, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(FillteredActivity.this, "mintrnt is:"+minrent, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(FillteredActivity.this, "maxrent is:"+maxrent, Toast.LENGTH_SHORT).show();


                    DatabaseReference mDatabaseUsers;
                    FirebaseAuth mAuth;
                    mDatabaseUsers = FirebaseDatabase.getInstance().getReference();
                    mDatabaseUsers.keepSynced(true);
                    mAuth = FirebaseAuth.getInstance();
                    DatabaseReference mRef = mDatabaseUsers.child("sell");
                    mRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child:snapshot.getChildren()) {
                                String keyvalue = child.getKey();
                                //  Toast.makeText(FillteredActivity.this, "" + keyvalue.toString(), Toast.LENGTH_SHORT).show();
//                                mRef.child(keyvalue).orderByChild("need").equalTo(buyOrSell).orderByChild("area").equalTo(searchtext.getText().toString()).orderByChild("minrent").equalTo(minrent) ;
                                mRef.child(keyvalue).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapsho) {
                                        for (DataSnapshot child2 : snapsho.getChildren()) {
                                            String secondkey = child2.getKey();
//                                            Toast.makeText(FillteredActivity.this, "" + snapshot2.child(secondkey).child("area").getValue().toString(), Toast.LENGTH_SHORT).show();
//                                            String keyvalue=child.getKey();
                                            String title = snapsho.child(secondkey).child("title").getValue().toString();
                                            String des = snapsho.child(secondkey).child("des").getValue().toString();
                                            String area = snapsho.child(secondkey).child("area").getValue().toString();
                                            String bath = snapsho.child(secondkey).child("bathrooms").getValue().toString();
                                            String bedrom = snapsho.child(secondkey).child("bedrooms").getValue().toString();
                                            String dateget = snapsho.child(secondkey).child("date").getValue().toString();
                                            String proprtytype = snapsho.child(secondkey).child("property_Type").getValue().toString();
                                            String dimention = snapsho.child(secondkey).child("dimention").getValue().toString();
                                            double minrentget = (Double.parseDouble(snapsho.child(secondkey).child("minrent").getValue().toString()));
                                            double maxrentget = (Double.parseDouble(snapsho.child(secondkey).child("maxrent").getValue().toString()));
                                            String needfor = snapsho.child(secondkey).child("need").getValue().toString();
                                            double longitut = (Double.parseDouble(snapsho.child(secondkey).child("longitut").getValue().toString()));
                                            double latitud = (Double.parseDouble(snapsho.child(secondkey).child("latitude").getValue().toString()));
                                            DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child("sell").child(mAuth.getCurrentUser().getUid()).child(keyvalue).child("imagelist");
                                            databaseReference2.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                                    for (DataSnapshot child2 : snapshot2.getChildren()) {
                                                        String keyimages = child2.getKey();
                                                        imagegeturl = snapshot2.child(keyimages).child("url").getValue().toString();
                                                        imageslist.add(snapshot2.child(keyimages).child("url").getValue().toString());
                                                    }
                                                    Toast.makeText(FillteredActivity.this, ""+minrentget, Toast.LENGTH_SHORT).show();
                                                     Toast.makeText(FillteredActivity.this, ""+maxrentget, Toast.LENGTH_SHORT).show();
////
                                                    if((maxrentget<=maxrent && maxrentget>=minrent) || (minrentget<=maxrent && minrentget>=minrent)){

                                                    if (buyOrSell.equals(needfor )&&(selected.equals(proprtytype))) {
                                                        if ((searchtet.contains(area))) {
                                                            listofsecondhomeModel.add(new HomeSecondModel(keyvalue,"" + title, "" + des, "" + area, "" + bath, "" + bedrom,
                                                                    "" + dateget, "" + dimention, minrent, maxrent, "" + needfor, "HomeActivity", longitut, latitud, "" + keyvalue, secondkey));
                                                            recyclerView = findViewById(R.id.fitterdrecylerview);
                                                            recyclerView.setLayoutManager(new LinearLayoutManager(FillteredActivity.this, RecyclerView.HORIZONTAL, false));
                                                            recyclerView.setHasFixedSize(true);
                                                            homesecondRecyclerview mysecondRecyclerAdapter = new homesecondRecyclerview(FillteredActivity.this, listofsecondhomeModel);
                                                            recyclerView.setAdapter(mysecondRecyclerAdapter);
                                                            mysecondRecyclerAdapter.notifyDataSetChanged();
                                                        }
                                                   else
                                                        {
                                                            Toast.makeText(FillteredActivity.this, ""+"enter text is "+searchtet+" and database match with "+area, Toast.LENGTH_SHORT).show();
//
                                                        }
                                                    }
                                                    else{
//                                                        Toast.makeText(FillteredActivity.this, ""+"enter text is "+searchtet+" and database match with "+area, Toast.LENGTH_SHORT).show();
                                                        Toast.makeText(FillteredActivity.this, "Your Search "+needfor+" not eqaul to "+buyOrSell+" and are"+area, Toast.LENGTH_SHORT).show();
                                                    }
                                                    }
                                                    else{
                                                        Toast.makeText(FillteredActivity.this, "Your Search is Empty", Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(FillteredActivity.this, "error is" + error.toString(), Toast.LENGTH_SHORT).show();
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
        });

    }

    public void filterdback(View view) {
        finish();
    }
}