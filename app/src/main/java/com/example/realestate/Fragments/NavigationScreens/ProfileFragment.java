package com.example.realestate.Fragments.NavigationScreens;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.realestate.NotificationScreen;
import com.example.realestate.ProfileEdit;
import com.example.realestate.R;
import com.example.realestate.myproperty.MyProperty;
import com.example.realestate.offerscreens.Recived_Offer;
import com.example.realestate.offerscreens.Send_Offer;

public class ProfileFragment extends Fragment {
 TextView profile,noti,myads,privicy,logout,sendoffer,reciveoffer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        profile=view.findViewById(R.id.profiletext);
       noti=view.findViewById(R.id.notification);
       myads=view.findViewById(R.id.myads);
       privicy=view.findViewById(R.id.priviceypolicey);
       logout=view.findViewById(R.id.logout);
       sendoffer=view.findViewById(R.id.offerendtet);
       reciveoffer=view.findViewById(R.id.OfferRecivedtet);
       profile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            Intent intent=new Intent(getContext(), ProfileEdit.class);
            startActivity(intent);
           }
       });
       noti.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)  {
            Intent i=new Intent(getContext(), NotificationScreen.class);
            startActivity(i);
           }
       });
        myads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), MyProperty.class);
                startActivity(intent);
            }
        });
        privicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        sendoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send=new Intent(getContext(), Send_Offer.class);
                startActivity(send);
            }
        });
        reciveoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recive=new Intent(getContext(), Recived_Offer.class);
                startActivity(recive);
            }
        });
    return  view;
    }
}