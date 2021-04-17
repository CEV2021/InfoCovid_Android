package com.example.infocovid.datalayer.model;

import java.util.ArrayList;

public class Provincia {
    String nombre;
    ArrayList<Municipio> municipios;
    Comunidad comunidad;

    public Provincia() {
        this.nombre = "";
        this.municipios = new ArrayList<Municipio>();
    }

    public Provincia(String nombre) {
        this.nombre = nombre;
        this.municipios = new ArrayList<Municipio>();
    }

    public Provincia(String nombre, ArrayList<Municipio> municipios, Comunidad comunidad) {
        this.nombre = nombre;
        this.municipios = municipios;
        this.comunidad = comunidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(ArrayList<Municipio> municipios) {
        this.municipios = municipios;
    }

    public void addMunicipio(Municipio municipio){
        this.municipios.add(municipio);
    }

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

}
