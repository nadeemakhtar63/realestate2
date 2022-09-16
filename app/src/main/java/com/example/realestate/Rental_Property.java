package com.example.realestate;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.Fragments.rental_bottom_sheet;
import com.example.realestate.RecyclerView.homesecondRecyclerview;
import com.example.realestate.modelclasses.HomeSecondModel;

import java.util.ArrayList;
public class Rental_Property extends AppCompatActivity {
    private RecyclerView recyclerView,recyclerView2;
    ArrayList<HomeSecondModel> listofsecondhomeModel=new ArrayList<>();
    ConstraintLayout layoutclick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_property);
    layoutclick=findViewById(R.id.sheet);
//        datainitilizedrecyclersecond();
        recyclerView2=findViewById(R.id.homefirsthorirecycle);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        recyclerView2.setHasFixedSize(true);
        homesecondRecyclerview mysecondRecyclerAdapter=new homesecondRecyclerview(this,listofsecondhomeModel);
        recyclerView2.setAdapter(mysecondRecyclerAdapter);
        mysecondRecyclerAdapter.notifyDataSetChanged();

        layoutclick.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        showBottomSheetDialog();

    }
});
    }

    private void showBottomSheetDialog() {
        rental_bottom_sheet bottomsheetfragment=new rental_bottom_sheet("RENTAL","PROPERTY","rent","sell");
        bottomsheetfragment.show(getSupportFragmentManager(),bottomsheetfragment.getTag());

    }

//    private void datainitilizedrecyclersecond() {
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Rent_Property",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Rent_Property",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Rent_Property",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Rent_Property",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Rent_Property",R.drawable.intro2));
//        listofsecondhomeModel.add(new HomeSecondModel("Necent Studio Apartment","E 88th St, Brooklyn","$254.00","2 Bedroom","2 Bathroom","Rent_Property",R.drawable.intro2));
//
//    }
}