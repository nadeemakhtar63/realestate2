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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.realestate.DetailsProperty;
import com.example.realestate.R;
import com.example.realestate.modelclasses.HomeSecondModel;

import java.util.ArrayList;

public class homesecondRecyclerview extends RecyclerView.Adapter<homesecondRecyclerview.MyViewHolder> {
    Context context;
    ArrayList<HomeSecondModel> datafirstlist;
    public homesecondRecyclerview(Context context, ArrayList<HomeSecondModel>homefisrtdata){
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
        HomeSecondModel model=datafirstlist.get(position);
//        holder.favirity.setText(model.profavirit);
        holder.location.setText(String.valueOf(model.getLatitud()));
        holder.nameproperty.setText(model.getTitle());
        holder.price.setText(String.valueOf(model.getMaxrent()));

        holder.bathroom.setText(model.getBath()+" Bathroom");
        holder.bedroom.setText(model.getBedrom()+" Bedroom");
        holder.need.setText("For "+model.getNeedfor());


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.intro1)
                .error(R.drawable.intro1);



        Glide.with(context).load(model.getImage()).apply(options).into(holder.proimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new  Intent(context, DetailsProperty.class);
//                intent.putExtra("location",String.valueOf(model.getLatitud()));
                intent.putExtra("dim",model.getDimention());
                intent.putExtra("desc",model.getDes());
                intent.putExtra("nameproperty",model.getTitle());
                intent.putExtra("listimage",model.getImage());
                intent.putExtra("bathroom",model.getBath());
                intent.putExtra("bedroom",model.getBedrom());
                intent.putExtra("price",model.getMaxrent()+"-"+model.getMinrent());
                intent.putExtra("docid",model.getDocumentId());
                intent.putExtra("activity",model.getActivityname());
                intent.putExtra("area",model.getArea());
               intent.putExtra("need",model.getNeedfor());
               intent.putExtra("reciver_id",model.getUserid());
//               intent.putExtra("long",String.valueOf(model.getLongitut()));
//                intent.putExtra("lati",String.valueOf(model.getLatitud()));
//                intent.putExtras(loct);
//               intent.putExtra("lati",model.getLatitud());
               intent.putExtra("doc_id",model.getDocumentId());
                context.startActivity(intent);
            }
        });
    }
    public  int getItemViewType(final  int position){
        return R.layout.homesecondrecyclerview;
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
         nameproperty=itemView.findViewById(R.id.homefragmentpropertysecondnamecard);
         location=itemView.findViewById(R.id.homefragmentsecondlocationitem);
            price=itemView.findViewById(R.id.homefragmentsecondlocationitemprice);
          bedroom=itemView.findViewById(R.id.bedroom);
         bathroom=itemView.findViewById(R.id.bathroom);
         need=itemView.findViewById(R.id.forneed);
//         nameproperty=itemView.findViewById(R.id.homefragmentpropertynamecard);
         proimage=itemView.findViewById(R.id.HomeFragmentseconditemsimageView);
        }
    }
}
