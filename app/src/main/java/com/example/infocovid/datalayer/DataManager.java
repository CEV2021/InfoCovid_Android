package com.example.infocovid.datalayer;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.infocovid.datalayer.model.Comunidad;
import com.example.infocovid.datalayer.model.Provincia;
import com.example.infocovid.datalayer.model.Municipio;
import com.example.infocovid.datalayer.model.Datos;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.example.infocovid.MainActivity;
import com.example.infocovid.datalayer.connection.eldiario.Connection;

public class DataManager extends AsyncTask<String, Void, String> {

    SupportsDataManager activityCallback;


    // Constructor
    public DataManager(SupportsDataManager activityCallback) {
        // this is the activity we are going to return to
        // - we store it when creating the class so we always know where that instance was called from,
        //   therefore we know where to refresh the data
        this.activityCallback = activityCallback;

    }



    // this gets executed at the beginning of the async task (well: it says before, not at the beginning..)
    @Override
    protected void onPreExecute() {
        // we set the progress bar visible
        // notes:
        // - the bar is invisible to keep the space
        // - we don't know the progress so that's why we don't have an update method. We've set an
        // undetermined style instead to keep the user amused meanwhile

    }

    @Override
    protected String doInBackground(String... strings) {
        Connection connection = new Connection();
        this.activityCallback.setConnection(connection);
        this.activityCallback.refreshView();
       return "";
    }

    @Override
    protected void onPostExecute(String response) {
        // Calling the method on the main activity to process the response (list of bands)
        activityCallback.refreshView();
    }

}
