package com.example.infocovid.datalayer.model;

public class Municipio {
    String nombre;
    Provincia provincia;
    Comunidad comunidad;

    public Municipio() {
        this.nombre = "";
    }

    public Municipio(String nombre) {
        this.nombre = nombre;
    }

    public Municipio(String nombre, Provincia provincia, Comunidad comunidad) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.comunidad = comunidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }
}
