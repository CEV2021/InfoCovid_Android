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
import com.example.infocovid.datalayer.model.Data;
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



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




    // Views
    View root;
    LineChart covidDataLineChart;

    TextView latestNewCasesTextView;
    TextView latestTotalCuredTextView;
    TextView latestTotalDeceasedTextView;

    TextView previousNewCasesTextView;
    TextView previousTotalCuredTextView;
    TextView previousTotalDeceasedTextView;

    // View model for this fragment
    private DetailsViewModel detailsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        root = inflater.inflate(R.layout.fragment_details, container, false);

        latestNewCasesTextView = root.findViewById(R.id.latestRegionDataNewCasesValue);
        latestTotalCuredTextView = root.findViewById(R.id.latestRegionDataTotalCuredValue);
        latestTotalDeceasedTextView = root.findViewById(R.id.latestRegionDataTotalDeceasedValue);

        previousNewCasesTextView = root.findViewById(R.id.previousRegionDataNewCasesValue);
        previousTotalCuredTextView = root.findViewById(R.id.previousRegionDataTotalCuredValue);
        previousTotalDeceasedTextView = root.findViewById(R.id.previousRegionDataTotalDeceasedValue);

        covidDataLineChart = root.findViewById(R.id.covidDataLineChart);

        detailsViewModel.getData().observe(getViewLifecycleOwner(), new Observer<Region>() {
            @Override
            public void onChanged(@Nullable Region currentRegion) {
                if (currentRegion != null) {

                    // processing the latest data (if we have data, of course)
                    if (currentRegion.getData().size() != 0 ) {
                        drawChart(currentRegion);

                        // Here we get the index of the latest data set
                        int latest = currentRegion.getData().size() - 1;
                        // ...an the dataset as well
                        Data latestData = currentRegion.getData().get(latest);

                        latestNewCasesTextView.setText(String.valueOf(latestData.getActive()));
                        latestTotalCuredTextView.setText(String.valueOf(latestData.getRecovered()));
                        latestTotalDeceasedTextView.setText(String.valueOf(latestData.getDeaths()));

                        // ...an now we get the previous dataset as well
                        Data previousData = currentRegion.getData().get(latest);

                        // Here we get the second last
                        previousNewCasesTextView.setText(String.valueOf(previousData.getActive()));
                        previousTotalCuredTextView.setText(String.valueOf(previousData.getRecovered()));
                        previousTotalDeceasedTextView.setText(String.valueOf(previousData.getDeaths()));
                    }
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
     * @todo: check comment below:
     * QUEDA PENDIENTE DE HABLAR CON EL TEAM DE IOS QUE DATOS PINTAMOS PARA QUE SEAN LOS MISMOS, DEJO EL EJEMPLO DE COMO SE PINTAN
     * SEGURAMENTE PINTAREMOS LOS ÚLTIMOS 7 DÍAS
     * */
    public void drawChart(Region currentRegion) {

        //Metemos datos
        ArrayList<Entry> deceasedValues = new ArrayList<>();
        ArrayList<Entry> curedValues = new ArrayList<>();
        ArrayList<Entry> casesValues = new ArrayList<>();
        ArrayList<Entry> activeValues = new ArrayList<>();


        for (int i = 0; i < currentRegion.getData().size()  ; i ++) { //LLenamos array
            deceasedValues.add(new Entry(i,currentRegion.getData().get(i).getDeaths()));
            curedValues.add(new Entry(i,currentRegion.getData().get(i).getRecovered()));
            casesValues.add(new Entry(i,currentRegion.getData().get(i).getConfirmed()));
            activeValues.add(new Entry(i,currentRegion.getData().get(i).getActive()));
        }

        LineDataSet set1 = new LineDataSet(deceasedValues, currentRegion.getName() + " deaths");
        LineDataSet set2 = new LineDataSet(curedValues, currentRegion.getName() + " recovered");
        LineDataSet set3 = new LineDataSet(casesValues, currentRegion.getName() + " confirmed");
        LineDataSet set4 = new LineDataSet(activeValues, currentRegion.getName() + " active");

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

        covidDataLineChart.setData(data);
    }
}