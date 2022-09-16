package com.example.realestate.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.realestate.R;
import com.example.realestate.chatmodule.ConversationActivity;
import com.example.realestate.modelclasses.HomeSecondModel;

import java.util.ArrayList;

public class SendOfferListAdopter extends RecyclerView.Adapter<SendOfferListAdopter.MyViewHolder> {
    public ArrayList<HomeSecondModel> childModelArrayList;
    Context cxt;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameproperty,location,price,bedroom,bathroom,need;
        AppCompatButton sold,delete,update;
        ImageView proimage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameproperty=itemView.findViewById(R.id.homefragmentpropertysecondnamecard);
            location=itemView.findViewById(R.id.homefragmentsecondlocationitem);
            price=itemView.findViewById(R.id.homefragmentsecondlocationitemprice);
            bedroom=itemView.findViewById(R.id.bedroom);
            bathroom=itemView.findViewById(R.id.bathroom);
            need=itemView.findViewById(R.id.forneed);
            sold=itemView.findViewById(R.id.accept_bt);
            delete=itemView.findViewById(R.id.delete_bt);
            update=itemView.findViewById(R.id.update_bt);


//         nameproperty=itemView.findViewById(R.id.homefragmentpropertynamecard);
            proimage=itemView.findViewById(R.id.HomeFragmentseconditemsimageView);

            itemView=itemView;

        }
    }

    public SendOfferListAdopter(ArrayList<HomeSecondModel> arrayList, Context mContext) {
        this.cxt = mContext;
        this.childModelArrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sendofferslayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HomeSecondModel currentItem = childModelArrayList.get(position);
        holder.nameproperty.setText(currentItem.getTitle());
        holder.location.setText(currentItem.getArea());
        holder.bathroom.setText(currentItem.getBath());
        holder.bedroom.setText(currentItem.getBedrom());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.intro1)
                .error(R.drawable.intro1);
        Glide.with(cxt).load(currentItem.getImage()).apply(options).into(holder.proimage);
        holder.sold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(cxt, "dhfkjdhjg", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(cxt, ConversationActivity.class);
                intent.putExtra("first", true);
//                intent.putExtra("name", currentItem.getName());
//                intent.putExtra("url", "null");
//                intent.putExtra("uid", currentItem.getUid());
                cxt.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(cxt, "dhfkjdhjg", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(cxt, ConversationActivity.class);
                intent.putExtra("first", true);
//                intent.putExtra("name", currentItem.getName());
//                intent.putExtra("url", "null");
//                intent.putExtra("uid", currentItem.getUid());
                cxt.startActivity(intent);
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(cxt, "dhfkjdhjg", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(cxt, ConversationActivity.class);
                intent.putExtra("first", true);
//                intent.putExtra("name", currentItem.getName());
//                intent.putExtra("url", "null");
//                intent.putExtra("uid", currentItem.getUid());
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
