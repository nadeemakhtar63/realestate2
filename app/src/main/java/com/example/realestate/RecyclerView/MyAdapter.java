package com.example.realestate.RecyclerView;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.realestate.Fragments.ChatFragment;
import com.example.realestate.offerscreens.ReceivedOffersFragment;
import com.example.realestate.offerscreens.SendOffersFragment;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;
            case 1:
                SendOffersFragment sendOffersFragment = new SendOffersFragment();
                return sendOffersFragment;
            case 2:
                ReceivedOffersFragment receivedOffersFragment = new ReceivedOffersFragment();
                return receivedOffersFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
