package com.example.realestate.modelclasses;

import android.media.Image;

public class RecentSearchModel {
    String propertname,PropertyLocation;
    Image dbimage,flyimage;

    public RecentSearchModel(String propertname, String propertyLocation, Image dbimage, Image flyimage) {
        this.propertname = propertname;
        PropertyLocation = propertyLocation;
        this.dbimage = dbimage;
        this.flyimage = flyimage;
    }

    public String getPropertname() {
        return propertname;
    }

    public void setPropertname(String propertname) {
        this.propertname = propertname;
    }

    public String getPropertyLocation() {
        return PropertyLocation;
    }

    public void setPropertyLocation(String propertyLocation) {
        PropertyLocation = propertyLocation;
    }

    public Image getDbimage() {
        return dbimage;
    }

    public void setDbimage(Image dbimage) {
        this.dbimage = dbimage;
    }

    public Image getFlyimage() {
        return flyimage;
    }

    public void setFlyimage(Image flyimage) {
        this.flyimage = flyimage;
    }
}
