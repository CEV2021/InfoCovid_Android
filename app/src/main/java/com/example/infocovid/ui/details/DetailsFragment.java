package com.example.infocovid.ui.details;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.infocovid.R;
import com.example.infocovid.datalayer.datamodels.RegionList;
import com.example.infocovid.datalayer.model.Region;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {

    private DetailsViewModel detailsViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Views
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

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_details, container, false);

        nuevosCasosTableDetailNumeroHoy = root.findViewById(R.id.nuevosCasosTableDetailNumero);
        curadosTableNumeroDetailHoy = root.findViewById(R.id.curadosTableNumeroDetailHoy);
        fallecidosTableNumeroDetailHoy = root.findViewById(R.id.fallecidosTableNumeroDetailHoy);
        nuevosCasosTableNumeroAntes = root.findViewById(R.id.nuevosCasosTableNumero);
        curadosTableNumeroAntes = root.findViewById(R.id.curadosTableNumero);
        fallecidosTableNumeroAntes = root.findViewById(R.id.fallecidosTableNumero);

        detailsViewModel.getData().observe(getViewLifecycleOwner(), new Observer<Region>() {
            @Override
            public void onChanged(@Nullable Region currentRegion) {
                if (currentRegion != null) {
                    // Here we get the latest data set
                    int latest = currentRegion.getData().size() -1;
                    nuevosCasosTableDetailNumeroHoy.setText(String.valueOf(currentRegion.getData().get(latest).getActive()));
                    curadosTableNumeroDetailHoy.setText(String.valueOf(currentRegion.getData().get(latest).getRecovered()));
                    fallecidosTableNumeroDetailHoy.setText(String.valueOf(currentRegion.getData().get(latest).getDeaths()));

                    // Here we get the second last
                    int secondLast = currentRegion.getData().size() -2;
                    nuevosCasosTableNumeroAntes.setText(String.valueOf(currentRegion.getData().get(secondLast).getActive()));
                    curadosTableNumeroAntes.setText(String.valueOf(currentRegion.getData().get(secondLast).getRecovered()));
                    fallecidosTableNumeroAntes.setText(String.valueOf(currentRegion.getData().get(secondLast).getDeaths()));

                    // Pintamos la grafica
//                    drawChart(root, regionList);
                }
            }
        });
        return root;
    }

    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /*
     * QUEDA PENDIENTE DE HABLAR CON EL TEAM DE IOS QUE DATOS PINTAMOS PARA QUE SEAN LOS MISMOS, DEJO EL EJEMPLO DE COMO SE PINTAN
     * SEGURAMENTE PINTAREMOS LOS ÚLTIMOS 7 DÍAS
     * */
    public void drawChart(View root, RegionList regionList) {


        lineChart = root.findViewById(R.id.lineChart);

        //Metemos datos
        ArrayList<Entry> deadValues = new ArrayList<>();
        ArrayList<Entry> recovered = new ArrayList<>();
        ArrayList<Entry> cases = new ArrayList<>();
        ArrayList<Entry> activeCases = new ArrayList<>();

        for (int i = 0; i < regionList.regions.get(0).getData().size()  ; i ++) { //LLenamos array
            deadValues.add(new Entry(i,regionList.regions.get(0).getData().get(i).getDeaths()));
            recovered.add(new Entry(i,regionList.regions.get(0).getData().get(i).getRecovered()));
            cases.add(new Entry(i,regionList.regions.get(0).getData().get(i).getConfirmed()));
            activeCases.add(new Entry(i,regionList.regions.get(0).getData().get(i).getActive()));
        }

        LineDataSet set1 = new LineDataSet(deadValues, regionList.regions.get(0).getName() + " deaths");
        LineDataSet set2 = new LineDataSet(recovered, regionList.regions.get(0).getName() + " recovered");
        LineDataSet set3 = new LineDataSet(cases, regionList.regions.get(0).getName() + " confirmed");
        LineDataSet set4 = new LineDataSet(activeCases, regionList.regions.get(0).getName() + " active");

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
}