package com.jksdairyhub.jksdairyhub.App.Models;

import java.util.ArrayList;

public class Customer {
    private int Id;
    private String Name,OwnerName,Contact,Latitude,Longitude,Place,Category,GstNo,Side,Info;
    private ArrayList<Customer> customerArrayList;

    public Customer() {
    }

    public Customer(ArrayList<Customer> customerArrayList) {
        this.customerArrayList = customerArrayList;
    }

    public Customer(int id, String name, String ownerName, String contact, String latitude, String longitude, String place, String category, String gstNo, String side, String info) {
        Id = id;
        Name = name;
        OwnerName = ownerName;
        Contact = contact;
        Latitude = latitude;
        Longitude = longitude;
        Place = place;
        Category = category;
        GstNo = gstNo;
        Side = side;
        Info = info;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public String getContact() {
        return Contact;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public String getPlace() {
        return Place;
    }

    public String getCategory() {
        return Category;
    }

    public String getGstNo() {
        return GstNo;
    }

    public String getSide() {
        return Side;
    }

    public String getInfo() {
        return Info;
    }
}
