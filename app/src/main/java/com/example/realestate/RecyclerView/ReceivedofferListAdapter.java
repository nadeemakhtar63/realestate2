package com.example.realestate.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;
import com.example.realestate.chatmodule.AllUsersActivity;
import com.example.realestate.chatmodule.ConversationActivity;
import com.example.realestate.modelclasses.RecentSearchModel;
import com.example.realestate.modelclasses.RecivedOfferModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReceivedofferListAdapter extends RecyclerView.Adapter<ReceivedofferListAdapter.MyViewHolder> {
    public ArrayList<RecivedOfferModel> childModelArrayList;
    Context cxt;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        Button accept,reject;
        public  View viewItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.username_textview_chats);
            accept = itemView.findViewById(R.id.accept_bt);
            reject = itemView.findViewById(R.id.reject_bt);

            viewItem=itemView;

        }
    }

    public ReceivedofferListAdapter(ArrayList<RecivedOfferModel> arrayList, Context mContext) {
        this.cxt = mContext;
        this.childModelArrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recived_offer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecivedOfferModel currentItem = childModelArrayList.get(position);
        holder.name.setText(currentItem.getName());


        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(cxt, "dhfkjdhjg", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(cxt, ConversationActivity.class);
                intent.putExtra("first", true);
                intent.putExtra("name", currentItem.getName());
                intent.putExtra("url", "null");
                intent.putExtra("uid", currentItem.getUid());
                cxt.startActivity(intent);
            }
        });

//
//


//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
//        ref.addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        //Get map of users in datasnapshot
//
//                        String uname= dataSnapshot.child(currentItem.getUid()).child("Name").getValue().toString();
//                        String uemail= dataSnapshot.child(currentItem.getUid()).child("Email").getValue().toString();
//                        holder.name.setText(uname);
//                        holder.email.setText(uemail);
//                        holder.date.setText(currentItem.getDate());
//                        holder.coupons.setText(currentItem.getCoupons());
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        //handle databaseError
//                    }
//                });


    }

    @Override
    public int getItemCount() {
        return childModelArrayList.size();
    }

}
