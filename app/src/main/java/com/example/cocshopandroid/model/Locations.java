package com.example.cocshopandroid.model;

import java.io.Serializable;

public class Locations implements Serializable {
    String id , locationName;

    public Locations(String id, String locationName) {
        this.id = id;
        this.locationName = locationName;
    }

    public Locations() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
