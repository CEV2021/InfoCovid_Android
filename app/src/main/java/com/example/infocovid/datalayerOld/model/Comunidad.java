package com.example.infocovid.datalayerOld.model;

import java.util.ArrayList;

public class Comunidad {
    private Integer id;
    private String nombre;
    private ArrayList<Provincia> provincias;
    private ArrayList<Municipio> municipios;

    public Comunidad() {
        this.id = 0;
        this.nombre = "";
        this.provincias = new ArrayList<Provincia>();
        this.municipios = new ArrayList<Municipio>();
    }

    public Comunidad(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.provincias = new ArrayList<Provincia>();
        this.municipios = new ArrayList<Municipio>();
    }

    public Comunidad(Integer id, String nombre, ArrayList<Provincia> provincias, ArrayList<Municipio> municipios) {
        this.id = id;
        this.nombre = nombre;
        this.provincias = provincias;
        this.municipios = municipios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(ArrayList<Provincia> provincias) {
        this.provincias = provincias;
    }

    public void addProvincia(Provincia provincia) {
        this.provincias.add(provincia);
    }

    public ArrayList<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(ArrayList<Municipio> municipios) {
        this.municipios = municipios;
    }

    public void addMunicipio(Municipio municipio) {
        this.municipios.add(municipio);
    }
}
