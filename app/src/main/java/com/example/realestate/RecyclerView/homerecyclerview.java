package com.example.realestate.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.DetailsProperty;
import com.example.realestate.R;
import com.example.realestate.modelclasses.homerfirtsmodel;

import java.util.ArrayList;

public class homerecyclerview extends RecyclerView.Adapter<homerecyclerview.MyViewHolder> {

    Context context;
    ArrayList<homerfirtsmodel> datafirstlist;
    public homerecyclerview(Context context,ArrayList<homerfirtsmodel>homefisrtdata){
        this.context=context;
        this.datafirstlist=homefisrtdata;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        homerfirtsmodel model=datafirstlist.get(position);
//        holder.favirity.setText(model.profavirit);
        holder.location.setText(String.valueOf(model.getLatitud()));
        holder.nameproperty.setText(model.getTitle());
        holder.price.setText(model.getMinrent()+"-"+model.getMaxrent());

        holder.bathroom.setText(model.getBath());
        holder.bedroom.setText(model.getBedrom());
        holder.need.setText("For "+model.getNeedfor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new  Intent(context, DetailsProperty.class);
                intent.putExtra("location",model.getLatitud());
                intent.putExtra("dim",model.getDimention());
                intent.putExtra("desc",model.getDes());
                intent.putExtra("nameproperty",model.getTitle());
//                intent.putExtra("proimage",model.getImage());
                intent.putExtra("bathroom",model.getBath());
                intent.putExtra("bedroom",model.getBedrom());
                intent.putExtra("price",model.getMinrent()+"-"+model.getMaxrent());
                intent.putExtra("docid",model.getDocumentId());
                intent.putExtra("activity",model.getActivityname());

                context.startActivity(intent);
            }
        });
    }

    public  int getItemViewType(final  int position){
        return R.layout.homefragment_card;

    }
    @Override
    public int getItemCount() {
        return datafirstlist.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameproperty,location,price,bedroom,bathroom,need;

        ImageView proimage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            nameproperty=itemView.findViewById(R.id.homefragmentpropertynamecard);
            location=itemView.findViewById(R.id.homefragmentlocationitem);
            price=itemView.findViewById(R.id.homefragmentlocationitemprice);
            nameproperty=itemView.findViewById(R.id.homefragmentpropertynamecard);
            bedroom=itemView.findViewById(R.id.bedroomfirst);
            bathroom=itemView.findViewById(R.id.bathroomfirst);
            need=itemView.findViewById(R.id.forget);
        }
    }
}
