package com.example.smartclassapp.ProfileView;

public class Uploads {
    public String name;
    public String url;
    public String contact;
    public String carrier;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Uploads() {
    }

    public Uploads(String name, String contact, String carrier, String url) {
        this.name = name;
        this.url = url;
        this.contact = contact;
        this.carrier = carrier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}