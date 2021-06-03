package com.example.infocovid.datalayer.datamodels;

public class Data {
    private int id;
    private int confirmed;
    private int deaths;
    private int recovered;
    private int active;
    private double incidentRate;
    private String date;

    public Data(int id, int confirmed, int detahs, int recovered, int active, double incidentRate, String date) {
        this.id = id;
        this.confirmed = confirmed;
        this.deaths = detahs;
        this.recovered = recovered;
        this.active = active;
        this.incidentRate = incidentRate;
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

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
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

    public double getIncidentRate() {
        return incidentRate;
    }

    public void setIncidentRate(double incidentRate) {
        this.incidentRate = incidentRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
