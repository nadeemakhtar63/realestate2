package com.example.realestate.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;
import com.example.realestate.modelclasses.homerfirtsmodel;

import java.util.ArrayList;

public class faveritrecyclerview  extends RecyclerView.Adapter<faveritrecyclerview.MyViewHolder> {

    Context context;
    ArrayList<homerfirtsmodel> datafirstlist;
    public faveritrecyclerview(Context context,ArrayList<homerfirtsmodel>homefisrtdata){
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
        homerfirtsmodel model=datafirstlist.get(1);
//        holder.favirity.setText(model.profavirit);
//        holder.location.setText(model.prolocation);
//        holder.nameproperty.setText(model.proname);
//        holder.price.setText(model.proprice);
//        holder.proimage.setImageResource(model.image);
    }

    public  int getItemViewType(final  int position){
        return R.layout.homefragment_card;

    }
    @Override
    public int getItemCount() {
        return datafirstlist.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameproperty,location,price,favirity;
        ImageView proimage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameproperty=itemView.findViewById(R.id.homefragmentpropertynamecard);
            location=itemView.findViewById(R.id.homefragmentlocationitem);
            price=itemView.findViewById(R.id.homefragmentlocationitemprice);
//            favirity=itemView.findViewById(R.id.homegramentfavert);
            nameproperty=itemView.findViewById(R.id.homefragmentpropertynamecard);
            proimage=itemView.findViewById(R.id.HomeFragmentitemsimageView);
        }
    }
}
