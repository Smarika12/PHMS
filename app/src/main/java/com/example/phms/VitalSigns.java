package com.example.phms;

public class VitalSigns {
    public Integer height;
    public Integer weight;
    public String bloodpressure;
    public String sugar;
    public String bloodgroup;


    public VitalSigns() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public VitalSigns(Integer height, Integer weight, String bloodpressure, String sugar, String bloodgroup ) {
        this.height = height;
        this.weight = weight;
        this.bloodpressure = bloodpressure;
        this.sugar = sugar;
        this.bloodgroup = bloodgroup;

    }
}
