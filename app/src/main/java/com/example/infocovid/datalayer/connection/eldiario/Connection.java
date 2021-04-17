package com.example.infocovid.datalayer.connection.eldiario;

import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.infocovid.datalayer.model.Comunidad;
import com.example.infocovid.datalayer.model.Datos;
import com.example.infocovid.datalayer.model.Municipio;
import com.example.infocovid.datalayer.model.Provincia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Connection {
    private String url = "https://lab.eldiario.es/elections-maps/coronavirus-mapa-municipios/datos-municipios-confirmados-coronavirus.csv";
    private String csvData = "";

    private ArrayList<Comunidad> comunidades;
    private ArrayList<Provincia> provincias;
    private ArrayList<Municipio> municipios;
    private ArrayList<Datos> datos;

    public Connection() {
        this.datos = new ArrayList<Datos>();
        this.municipios = new ArrayList<Municipio>();
        this.provincias = new ArrayList<Provincia>();
        this.comunidades = new ArrayList<Comunidad>();

        this.csvData = this.getCsv();
        this.parseCsv();
    }

    void refreshData() {
        this.datos = new ArrayList<Datos>();
        this.municipios = new ArrayList<Municipio>();
        this.provincias = new ArrayList<Provincia>();
        this.comunidades = new ArrayList<Comunidad>();
        // @todo: don't clear the data if a connection was not possible!
        this.csvData = this.getCsv();
        this.parseCsv();
    }

    public ArrayList<Comunidad> getComunidades() {
        return comunidades;
    }

    public ArrayList<Provincia> getProvincias() {
        return provincias;
    }

    public ArrayList<Municipio> getMunicipios() {
        return municipios;
    }

    public ArrayList<Datos> getDatos() {
        return datos;
    }

    public Datos getDatos(Integer idDatos) {
        Iterator<Datos> iterator = this.datos.iterator();
        while (iterator.hasNext()) {
            Datos tempDatos = iterator.next();
            if (tempDatos.getId().equals(idDatos)) {
                return tempDatos;
            }
        }
        return null;
    }

    public Datos getDatos(String nombreMunicipio) {
        Iterator<Datos> iterator = this.datos.iterator();
        while (iterator.hasNext()) {
            Datos tempDatos = iterator.next();
            if (tempDatos.getMunicipio().getNombre().equals(nombreMunicipio)) {
                return tempDatos;
            }
        }
        return null;
    }

    public Comunidad getComunidad(Integer idComunidad) {
        Iterator<Comunidad> iterator = this.comunidades.iterator();
        while (iterator.hasNext()) {
            Comunidad tempComunidad = iterator.next();
            if (tempComunidad.getId().equals(idComunidad)) {
                return tempComunidad;
            }
        }
        return null;
    }

    public Comunidad getComunidad(String nombreComunidad) {
        Iterator<Comunidad> iterator = this.comunidades.iterator();
        while (iterator.hasNext()) {
            Comunidad tempComunidad = iterator.next();
            if (tempComunidad.getId().equals(nombreComunidad)) {
                return tempComunidad;
            }
        }
        return null;
    }

    private String getCsv() {
        String body = "";

        // creating the connection object
        OkHttpClient okHttpClient = new OkHttpClient();
        Request petition = new Request.Builder().url(this.url).build();
        try {
            Response response = okHttpClient.newCall(petition).execute();
            body = response.body().string();
        } catch (IOException e) {
            // handeling the exception
            // - we do nothing because we'll return an empty body anyway
            Log.w("Connection", "Error getting the csv data");
        }

        // returning the result
        return body;
    }

    public void parseCsv() {
        List resultList = new ArrayList();

        String[] csvLines = this.csvData.split("\\r?\\n");
        String[] csvHeaders;

        Boolean flagHeader = true;
        Comunidad currentComunidad = new Comunidad();
        Provincia currentProvincia = new Provincia();
        Municipio currentMunicipio = new Municipio();

        for (String csvLine : csvLines) {
            if (!flagHeader) {
                String[] csvLineCells = csvLine.split(";", -1);

                Integer datosId = Integer.parseInt(!csvLineCells[0].isEmpty() ? csvLineCells[0] : "0");
                String nombreComunidad = new String(!csvLineCells[1].isEmpty() ? csvLineCells[1] : "");
                Integer idCOmunidad = Integer.parseInt(!csvLineCells[2].isEmpty() ? csvLineCells[2] : "0");
                String nombreProvincia = new String(!csvLineCells[3].isEmpty() ? csvLineCells[3] : "");
                String nombreMunicipio = new String(!csvLineCells[4].isEmpty() ? csvLineCells[4] : "");
                Integer tasaCovid = Integer.parseInt(!csvLineCells[5].isEmpty() ? csvLineCells[5] : "0");
                Integer muertos = Integer.parseInt(!csvLineCells[6].isEmpty() ? csvLineCells[6] : "0");
                Integer recuperados = Integer.parseInt(!csvLineCells[7].isEmpty() ? csvLineCells[7] : "0");
                Integer covid14Dias = Integer.parseInt(!csvLineCells[8].isEmpty() ? csvLineCells[8] : "0");
                Integer ia14Dias = Integer.parseInt(!csvLineCells[9].isEmpty() ? csvLineCells[9] : "0");
                Integer covid14DiasAnt = Integer.parseInt(!csvLineCells[10].isEmpty() ? csvLineCells[10] : "0");
                Integer ia14DiasAnt = Integer.parseInt(!csvLineCells[11].isEmpty() ? csvLineCells[11] : "0");
                Integer variacion = Integer.parseInt(!csvLineCells[12].isEmpty() ? csvLineCells[12] : "0");
                Integer suben = Integer.parseInt(!csvLineCells[13].isEmpty() ? csvLineCells[13] : "0");
                Integer fecha = Integer.parseInt(!csvLineCells[14].isEmpty() ? csvLineCells[14] : "0");

                Datos datos = new Datos(datosId, tasaCovid, muertos, recuperados, covid14Dias, ia14Dias, covid14DiasAnt, ia14DiasAnt, variacion, suben, fecha);
                this.datos.add(datos);


                // first we updae all "current" variables
                if (!currentMunicipio.getNombre().equals(nombreMunicipio)) {
                    currentMunicipio = new Municipio(nombreMunicipio);
                    this.municipios.add(currentMunicipio);
                }

                if (!currentProvincia.getNombre().equals(nombreProvincia)) {
                    currentProvincia = new Provincia(nombreProvincia);
                    this.provincias.add(currentProvincia);
                }

                if (!currentComunidad.getNombre().equals(nombreComunidad)) {
                    currentComunidad = new Comunidad(idCOmunidad, nombreComunidad);
                    this.comunidades.add(currentComunidad);
                }

                if (currentMunicipio.getNombre().equals("Voto") && currentComunidad.getNombre().equals("Cantabria")) {
                    String another = "moline";
                    Integer mynumber = 99;
                    Log.w("Logger", "WTF is going on");
                }

                // Now we link the data on all the containers
                currentMunicipio.setComunidad(currentComunidad);
                currentMunicipio.setProvincia(currentProvincia);

                currentProvincia.addMunicipio(currentMunicipio);
                currentProvincia.setComunidad(currentComunidad);

                currentComunidad.addMunicipio(currentMunicipio);
                currentComunidad.addProvincia(currentProvincia);

                datos.setComunidad(currentComunidad);
                datos.setProvincia(currentProvincia);
                datos.setMunicipio(currentMunicipio);


            } else {
                csvHeaders = csvLine.split(",");
                flagHeader = false;
            }
        }
    }


}
