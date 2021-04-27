package com.example.infocovid.datalayer.datamodels;

public class Data {
    private int id;
    private int confirmed;
    private int detahs;
    private int recovered;
    private int active;

    public Data(int id, int confirmed, int detahs, int recovered, int active, int indcidentRate, String date) {
        this.id = id;
        this.confirmed = confirmed;
        this.detahs = detahs;
        this.recovered = recovered;
        this.active = active;
        this.indcidentRate = indcidentRate;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDetahs() {
        return detahs;
    }

    public void setDetahs(int detahs) {
        this.detahs = detahs;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getIndcidentRate() {
        return indcidentRate;
    }

    public void setIndcidentRate(int indcidentRate) {
        this.indcidentRate = indcidentRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private int indcidentRate;
    private String date;
}
