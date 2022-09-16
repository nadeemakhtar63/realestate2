package com.example.realestate.offerscreens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;
import com.example.realestate.RecyclerView.ReceivedofferListAdapter;
import com.example.realestate.modelclasses.RecivedOfferModel;

import java.util.ArrayList;

public class ReceivedOffersFragment extends Fragment {

    private RecyclerView listRecyclerView;
    private RecyclerView.LayoutManager listLayoutManager;
    ArrayList<RecivedOfferModel> arrayList = new ArrayList<>();;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_received_offers, container, false);

        arrayList.add(new RecivedOfferModel("Sajjad","r8sroWUDoHZ89denWYBqnWKNMMt1",""));
        listRecyclerView = view.findViewById(R.id.recieved_recyclerview);
        listRecyclerView.setHasFixedSize(true);
        listLayoutManager = new LinearLayoutManager(getContext());
        ReceivedofferListAdapter receivedofferListAdapter = new ReceivedofferListAdapter(arrayList,getContext());
        listRecyclerView.setAdapter(receivedofferListAdapter);
        listRecyclerView.setLayoutManager(listLayoutManager);
        receivedofferListAdapter.notifyDataSetChanged();
        return view;


    }
}