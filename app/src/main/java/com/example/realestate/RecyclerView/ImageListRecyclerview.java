package com.example.realestate.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.realestate.R;
import com.example.realestate.modelclasses.ImagesshowModelClass;

import java.util.ArrayList;
public class ImageListRecyclerview extends RecyclerView.Adapter<ImageListRecyclerview.MyViewHolder> {
    public ArrayList<ImagesshowModelClass> childModelArrayList;
    Context cxt;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView videoImage;


        public MyViewHolder(View itemView) {
            super(itemView);
            videoImage = itemView.findViewById(R.id.imagedetailrecycler);

        }
    }

    public ImageListRecyclerview(ArrayList<ImagesshowModelClass>  arrayList,Context context) {
        this.childModelArrayList = arrayList;
        this.cxt=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_top_layout_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ImagesshowModelClass currentItem = childModelArrayList.get(position);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.img2)
                .error(R.drawable.intro2);



        Glide.with(cxt).load(currentItem.getImage()).apply(options).into(holder.videoImage);

//        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//// Set video url as data source
//        retriever.setDataSource(currentItem.getUrl(), new HashMap<String, String>());
//// Get frame at 2nd second as Bitmap image
//        Bitmap bitmap = retriever.getFrameAtTime(2000000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
//// Display the Bitmap image in an ImageView
//        holder.videoImage.setImageBitmap(bitmap);
//        holder.viewItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(cxt, PdfViewerActivity.class);
//                intent.putExtra("pdfurl",currentItem.getBookurl());
//                intent.putExtra("name",currentItem.getBook_name());
//                intent.putExtra("imageurl",currentItem.getImage_url());
//                intent.putExtra("activity","notdownload");
//                cxt.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return childModelArrayList.size();
    }

}
