package com.example.infocovid.datalayer.datamodels;

import java.util.ArrayList;

public class Region {
    private int id;
    private String name;

    public Region(int id, String name, ArrayList<Data> data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    private ArrayList<Data> data;
}
