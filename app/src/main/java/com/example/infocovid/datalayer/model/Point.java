package com.example.infocovid.datalayer.model;

public class Point {
    public double latitude;
    public double longitude;

    public Point() {
        this.latitude = 0;
        this.longitude = 0;
    }

    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
