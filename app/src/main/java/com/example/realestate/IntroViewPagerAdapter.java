package com.example.realestate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.PagerAdapter;

import com.example.realestate.RecyclerView.MyRecyclerAdapter;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter{
    Context mContext ;
    List<com.example.realestate.ScreenItem> mListScreen;
//    RecyclerView recyclerView;
    List<ApplicationInfo> applicationInfos ;
     public IntroViewPagerAdapter(Context mContext, List<com.example.realestate.ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);

        ConstraintLayout imgSlide = layoutScreen.findViewById(R.id.backgroundimageset);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView description = layoutScreen.findViewById(R.id.intro_description);
//        recyclerView = layoutScreen.findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,linearLayoutManager.getOrientation()));



        if (position != 4){
            imgSlide.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            description.setVisibility(View.VISIBLE);
//            recyclerView.setVisibility(View.GONE);
        }
        if (position == 4){
            imgSlide.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            description.setVisibility(View.GONE);
//            recyclerView.setVisibility(View.VISIBLE);

            Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            applicationInfos = mContext.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);

            MyRecyclerAdapter adapter = new MyRecyclerAdapter(mContext,applicationInfos );
//            recyclerView.setAdapter(adapter);
        }

        if (position != 4){
            title.setText(mListScreen.get(position).getTitle());
            description.setText(mListScreen.get(position).getDescription());
            imgSlide.setBackground(mListScreen.get(position).getScreenImg());
        }

        container.addView(layoutScreen);
        return layoutScreen;

    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
