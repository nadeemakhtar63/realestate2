package com.example.realestate.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;

public class homefragmentviewholder extends RecyclerView.ViewHolder {
    private TextView nameproperty,location,price,favirity;
    public homefragmentviewholder(@NonNull View itemView) {
        super(itemView);
        nameproperty=itemView.findViewById(R.id.homefragmentpropertynamecard);
        location=itemView.findViewById(R.id.homefragmentlocationitem);
        price=itemView.findViewById(R.id.homefragmentlocationitemprice);
        favirity=itemView.findViewById(R.id.homegramentfavert);
        nameproperty=itemView.findViewById(R.id.homefragmentpropertynamecard);
    }

    public TextView getNameproperty() {
        return nameproperty;
    }

    public void setNameproperty(TextView nameproperty) {
        this.nameproperty = nameproperty;
    }

    public TextView getLocation() {
        return location;
    }

    public void setLocation(TextView location) {
        this.location = location;
    }

    public TextView getPrice() {
        return price;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }

    public TextView getFavirity() {
        return favirity;
    }

    public void setFavirity(TextView favirity) {
        this.favirity = favirity;
    }
}
