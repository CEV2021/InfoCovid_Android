//package com.example.infocovid.datalayer.connection;
//
//import android.util.Log;
//
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class Connection {
//    private String url = "https://lab.eldiario.es/elections-maps/coronavirus-mapa-municipios/datos-municipios-confirmados-coronavirus.csv";
//    private String csvData = "";
//
//    private ArrayList<Comunidad> comunidades;
//    private ArrayList<Provincia> provincias;
//    private ArrayList<Municipio> municipios;
//    private ArrayList<Datos> datos;
//
//    public Connection() {
//        this.datos = new ArrayList<Datos>();
//        this.municipios = new ArrayList<Municipio>();
//        this.provincias = new ArrayList<Provincia>();
//        this.comunidades = new ArrayList<Comunidad>();
//
//        this.csvData = this.getCsv();
//        this.parseCsv();
//    }
//
//    void refreshData() {
//        this.datos = new ArrayList<Datos>();
//        this.municipios = new ArrayList<Municipio>();
//        this.provincias = new ArrayList<Provincia>();
//        this.comunidades = new ArrayList<Comunidad>();
//        // @todo: don't clear the data if a connection was not possible!
//        this.csvData = this.getCsv();
//        this.parseCsv();
//    }
//
//    public ArrayList<Comunidad> getComunidades() {
//        return comunidades;
//    }
//
//    public ArrayList<Provincia> getProvincias() {
//        return provincias;
//    }
//
//    public ArrayList<Municipio> getMunicipios() {
//        return municipios;
//    }
//
//    public ArrayList<Datos> getDatos() {
//        return datos;
//    }
//
//    public Datos getDatos(Integer idDatos) {
//        Iterator<Datos> iterator = this.datos.iterator();
//        while (iterator.hasNext()) {
//            Datos tempDatos = iterator.next();
//            if (tempDatos.getId().equals(idDatos)) {
//                return tempDatos;
//            }
//        }
//        return null;
//    }
//
//    public Datos getDatos(String nombreMunicipio) {
//        Iterator<Datos> iterator = this.datos.iterator();
//        while (iterator.hasNext()) {
//            Datos tempDatos = iterator.next();
//            if (tempDatos.getMunicipio().getNombre().equals(nombreMunicipio)) {
//                return tempDatos;
//            }
//        }
//        return null;
//    }
//
//    public Comunidad getComunidad(Integer idComunidad) {
//        Iterator<Comunidad> iterator = this.comunidades.iterator();
//        while (iterator.hasNext()) {
//            Comunidad tempComunidad = iterator.next();
//            if (tempComunidad.getId().equals(idComunidad)) {
//                return tempComunidad;
//            }
//        }
//        return null;
//    }
//
//    public Comunidad getComunidad(String nombreComunidad) {
//        Iterator<Comunidad> iterator = this.comunidades.iterator();
//        while (iterator.hasNext()) {
//            Comunidad tempComunidad = iterator.next();
//            if (tempComunidad.getId().equals(nombreComunidad)) {
//                return tempComunidad;
//            }
//        }
//        return null;
//    }
//
//    private String getCsv() {
//        String body = "";
//
//        // creating the connection object
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request petition = new Request.Builder().url(this.url).build();
//        try {
//            Response response = okHttpClient.newCall(petition).execute();
//            body = response.body().string();
//        } catch (IOException e) {
//            // handeling the exception
//            // - we do nothing because we'll return an empty body anyway
//            Log.w("Connection", "Error getting the csv data");
//        }
//
//        // returning the result
//        return body;
//    }
//
//    public void parseCsv() {
//        List resultList = new ArrayList();
//
//        String[] csvLines = this.csvData.split("\\r?\\n");
//        String[] csvHeaders;
//
//        Boolean flagHeader = true;
//        Comunidad currentComunidad = new Comunidad();
//        Provincia currentProvincia = new Provincia();
//        Municipio currentMunicipio = new Municipio();
//
//    }
//
//
//}
