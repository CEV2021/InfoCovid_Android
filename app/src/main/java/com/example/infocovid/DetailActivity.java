package com.example.infocovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.infocovid.datalayer.datamodels.PreferencesManager;
import com.example.infocovid.datalayer.datamodels.Region;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    //View
    BottomNavigationView nBottomNavigation;
    LineChart lineChart;
    TextView nuevosCasosTableDetailNumeroHoy;
    TextView curadosTableNumeroDetailHoy;
    TextView fallecidosTableNumeroDetailHoy;
    TextView nuevosCasosTableNumeroAntes;
    TextView curadosTableNumeroAntes;
    TextView fallecidosTableNumeroAntes;

    //String to set the texts
    String newCasesToday;
    String recoveredToday;
    String deathsToday;
    String newCasesLast;
    String recoveredLast;
    String deathsLast;

    PreferencesManager preferencesManager;
    ArrayList<Region> regions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);




        // control de navegacion por iconos
        nBottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        nBottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    startActivity(new Intent(DetailActivity.this, MainActivity.class));
                    return true;
                case R.id.menu_graphics:
                    startActivity(new Intent(DetailActivity.this, DetailActivity.class));
                    return true;
                case R.id.menu_search:
                    startActivity(new Intent (DetailActivity.this, SearchActivity.class));
                    return true;
                case R.id.menu_settings:
                    startActivity(new Intent (DetailActivity.this, SettingsActivity.class));
                    return true;

            }
            return false;
        });

        // Inicializamos
        regions = new ArrayList<>();
        regions = loadPreferences();

        refreshViews();
        drawChart();


    }

    /*
     * Cogemos los ultimos datos de muertos y recuperados
     * */
    public ArrayList<Region> loadPreferences() {

        ArrayList<Region> region = preferencesManager.loadPreferences(this);

        if (region != null) {
            return region;
        } else {
            return region = preferencesManager.loadPreferences(this);
        }

    }

    /*
     * QUEDA PENDIENTE DE HABLAR CON EL TEAM DE IOS QUE DATOS PINTAMOS PARA QUE SEAN LOS MISMOS, DEJO EL EJEMPLO DE COMO SE PINTAN
     * SEGURAMENTE PINTAREMOS LOS ÚLTIMOS 7 DÍAS
     * */
    public void drawChart() {


        lineChart = findViewById(R.id.lineChart);

        //Metemos datos
        ArrayList<Entry> deadValues = new ArrayList<>();
        ArrayList<Entry> recovered = new ArrayList<>();
        ArrayList<Entry> cases = new ArrayList<>();
        ArrayList<Entry> activeCases = new ArrayList<>();

        for (int i = 0; i < regions.get(0).getData().size()  ; i ++) { //LLenamos array
            deadValues.add(new Entry(i,regions.get(0).getData().get(i).getDeaths()));
            recovered.add(new Entry(i,regions.get(0).getData().get(i).getRecovered()));
            cases.add(new Entry(i,regions.get(0).getData().get(i).getConfirmed()));
            activeCases.add(new Entry(i,regions.get(0).getData().get(i).getActive()));
        }

        LineDataSet set1 = new LineDataSet(deadValues, regions.get(0).getName() + " deaaths");
        LineDataSet set2 = new LineDataSet(recovered, regions.get(0).getName() + " recovered");
        LineDataSet set3 = new LineDataSet(cases, regions.get(0).getName() + " confirmed");
        LineDataSet set4 = new LineDataSet(activeCases, regions.get(0).getName() + " active");

        set1.setFillAlpha(110);
        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set2.setFillAlpha(110);
        set2.setColor(Color.GREEN);
        set2.setLineWidth(3f);
        set3.setFillAlpha(110);
        set3.setColor(Color.BLUE);
        set3.setLineWidth(3f);
        set4.setFillAlpha(110);
        set4.setColor(Color.MAGENTA);
        set4.setLineWidth(3f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        //dataSets.add(set3);
        //dataSets.add(set4);

        LineData data = new LineData(dataSets);

        lineChart.setData(data);


    }

    //Mostramos los datos de las dos ultimas semanas

    public void refreshViews() {
        nuevosCasosTableDetailNumeroHoy = findViewById(R.id.nuevosCasosTableDetailNumero);
        curadosTableNumeroDetailHoy = findViewById(R.id.curadosTableNumeroDetailHoy);
        fallecidosTableNumeroDetailHoy = findViewById(R.id.fallecidosTableNumeroDetailHoy);
        nuevosCasosTableNumeroAntes = findViewById(R.id.nuevosCasosTableNumero);
        curadosTableNumeroAntes = findViewById(R.id.curadosTableNumero);
        fallecidosTableNumeroAntes = findViewById(R.id.fallecidosTableNumero);

        //Obtenemos el ultimo
        nuevosCasosTableDetailNumeroHoy.setText( newCasesToday = String.valueOf(regions.get(0).getData().get(regions.get(0).getData().size() - 1).getActive()));
        curadosTableNumeroDetailHoy.setText(recoveredToday = String.valueOf(regions.get(0).getData().get(regions.get(0).getData().size() - 1).getRecovered()));
        fallecidosTableNumeroDetailHoy.setText(deathsToday = String.valueOf(regions.get(0).getData().get(regions.get(0).getData().size() - 1).getDeaths()));

        //Obtenemos el penultimo
        nuevosCasosTableNumeroAntes.setText( newCasesLast = String.valueOf(regions.get(0).getData().get(regions.get(0).getData().size() - 2).getActive()));
        curadosTableNumeroAntes.setText(recoveredLast = String.valueOf(regions.get(0).getData().get(regions.get(0).getData().size() - 2).getRecovered()));
        fallecidosTableNumeroAntes.setText(deathsLast = String.valueOf(regions.get(0).getData().get(regions.get(0).getData().size() - 2).getDeaths()));
    }

}