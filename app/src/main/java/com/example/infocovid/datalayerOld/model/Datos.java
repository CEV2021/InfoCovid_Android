package com.example.infocovid.datalayerOld.model;

public class Datos {
    private Integer id;
    private Integer tasa;
    private Integer muertos;
    private Integer recuperados;
    private Integer covid14Dias;
    private Integer ia14Dias;
    private Integer covid14DiasAnt;
    private Integer ia14DiasAnt;
    private Integer variacion;
    private Integer suben;
    private Integer fecha;
    private Municipio municipio;
    private Provincia provincia;
    private Comunidad comunidad;

    public Datos() {
    }

    public Datos(Municipio municipio, Provincia provincia, Comunidad comunidad) {
        this.id = 0;
        this.tasa = 0;
        this.muertos = 0;
        this.recuperados = 0;
        this.covid14Dias = 0;
        this.ia14Dias = 0;
        this.covid14DiasAnt = 0;
        this.ia14DiasAnt = 0;
        this.variacion = 0;
        this.suben = 0;
        this.fecha = 0;
        this.municipio = municipio;
        this.provincia = provincia;
        this.comunidad = comunidad;
    }

    public Datos(Integer id, Integer tasa, Integer muertos, Integer recuperados, Integer covid14Dias, Integer ia14Dias, Integer covid14DiasAnt, Integer ia14DiasAnt, Integer variacion, Integer suben, Integer fecha) {
        this.id = id;
        this.tasa = tasa;
        this.muertos = muertos;
        this.recuperados = recuperados;
        this.covid14Dias = covid14Dias;
        this.ia14Dias = ia14Dias;
        this.covid14DiasAnt = covid14DiasAnt;
        this.ia14DiasAnt = ia14DiasAnt;
        this.variacion = variacion;
        this.suben = suben;
        this.fecha = fecha;
    }

    public Datos(Integer id, Integer tasa, Integer muertos, Integer recuperados, Integer covid14Dias, Integer ia14Dias, Integer covid14DiasAnt, Integer ia14DiasAnt, Integer variacion, Integer suben, Integer fecha, Municipio municipio, Provincia provincia, Comunidad comunidad) {
        this.id = id;
        this.tasa = tasa;
        this.muertos = muertos;
        this.recuperados = recuperados;
        this.covid14Dias = covid14Dias;
        this.ia14Dias = ia14Dias;
        this.covid14DiasAnt = covid14DiasAnt;
        this.ia14DiasAnt = ia14DiasAnt;
        this.variacion = variacion;
        this.suben = suben;
        this.fecha = fecha;
        this.municipio = municipio;
        this.provincia = provincia;
        this.comunidad = comunidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTasa() {

        return tasa;
    }

    public void setTasa(Integer tasa) {

        this.tasa = tasa;
    }

    public Integer getMuertos() {
        return muertos;
    }

    public void setMuertos(Integer muertos) {

        this.muertos = muertos;
    }

    public Integer getRecuperados() {
        return recuperados;
    }

    public void setRecuperados(Integer recuperados) {
        this.recuperados = recuperados;
    }

    public Integer getCovid14Dias() {
        return covid14Dias;
    }

    public void setCovid14Dias(Integer covid14Dias) {
        this.covid14Dias = covid14Dias;
    }

    public Integer getIa14Dias() {
        return ia14Dias;
    }

    public void setIa14Dias(Integer ia14Dias) {
        this.ia14Dias = ia14Dias;
    }

    public Integer getCovid14DiasAnt() {
        return covid14DiasAnt;
    }

    public void setCovid14DiasAnt(Integer covid14DiasAnt) {
        this.covid14DiasAnt = covid14DiasAnt;
    }

    public Integer getIa14DiasAnt() {
        return ia14DiasAnt;
    }

    public void setIa14DiasAnt(Integer ia14DiasAnt) {
        this.ia14DiasAnt = ia14DiasAnt;
    }

    public Integer getVariacion() {
        return variacion;
    }

    public void setVariacion(Integer variacion) {
        this.variacion = variacion;
    }

    public Integer getSuben() {
        return suben;
    }

    public void setSuben(Integer suben) {
        this.suben = suben;
    }

    public Integer getFecha() {
        return fecha;
    }

    public void setFecha(Integer fecha) {
        this.fecha = fecha;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
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
