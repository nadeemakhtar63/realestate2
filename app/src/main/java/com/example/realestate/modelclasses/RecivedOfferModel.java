package com.example.realestate.modelclasses;
public class RecivedOfferModel {
    String name, uid, itemid;


    public RecivedOfferModel(String name, String uid, String itemid) {
        this.name = name;
        this.uid = uid;
        this.itemid = itemid;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getItemid() {
        return itemid;
    }
}

