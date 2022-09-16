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
import com.example.realestate.modelclasses.RecentSearchModel;

import java.util.ArrayList;

public class RecentSearchRecyclerview extends RecyclerView.Adapter<RecentSearchRecyclerview.MyViewHolder> {
    Context context;
    ArrayList<RecentSearchModel> datafirstlist;

    @NonNull
    @Override
    public RecentSearchRecyclerview.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
        return new RecentSearchRecyclerview.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecentSearchRecyclerview.MyViewHolder holder, int position) {
        RecentSearchModel model=datafirstlist.get(1);
//        holder.favirity.setText(model.profavirit);
//        holder..setText(model.getProlocation());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameproperty,locationProperty;
        ImageView dbimage,flyicon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameproperty=itemView.findViewById(R.id.homefragmentpropertysecondnamecard);
            locationProperty=itemView.findViewById(R.id.homefragmentsecondlocationitem);
            dbimage=itemView.findViewById(R.id.HomeFragmentseconditemsimageView);
            flyicon=itemView.findViewById(R.id.HomeFragmentseconditemsimageView);
        }
    }
}
