package com.example.infocovid.datalayer.model;

import com.example.infocovid.datalayer.datamodels.Data;

import java.util.ArrayList;

public class Region {

    private Integer id;
    private String name;
    private ArrayList<Data> data;

    public Region(int id, String name, ArrayList<Data> data ) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


}
