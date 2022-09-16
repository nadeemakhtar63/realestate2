package com.example.realestate.modelclasses;
public class HomeSecondModel {
    String title,des,area,bath,bedrom,dateget,dimention,needfor,activityname;
    double longitut,latitud,minrent,maxrent;
    String image,documentId,userid;
    public HomeSecondModel(String uid,String title, String des, String area, String bath, String bedrom, String dateget, String dimention, double minrent, double maxrent, String needfor, String activityname, double longitut, double latitud, String image,String documentId)
    {
        this.userid=uid;
        this.title = title;
        this.des = des;
        this.area = area;
        this.bath = bath;
        this.bedrom = bedrom;
        this.dateget = dateget;
        this.dimention = dimention;
        this.minrent = minrent;
        this.maxrent = maxrent;
        this.needfor = needfor;
        this.activityname = activityname;
        this.longitut = longitut;
        this.latitud = latitud;
        this.image = image;
        this.documentId=documentId;
    }

    public String getUserid() {
        return userid;
    }

    public String getDocumentId() {
        return documentId;
    }
    public String getTitle() {
        return title;
    }
    public String getDes() {
        return des;
    }
    public String getArea() {
        return area;
    }
    public String getBath() {
        return bath;
    }
    public String getBedrom() {
        return bedrom;
    }
    public String getDateget() {
        return dateget;
    }
    public String getDimention() {
        return dimention;
    }
    public double getMinrent() {
        return minrent;
    }
    public double getMaxrent() {
        return maxrent;
    }
    public String getNeedfor() {
        return needfor;
    }
    public String getActivityname() {
        return activityname;
    }
    public double getLongitut() {
        return longitut;
    }
    public double getLatitud() {
        return latitud;
    }
    public String getImage() {
        return image;
    }
}
