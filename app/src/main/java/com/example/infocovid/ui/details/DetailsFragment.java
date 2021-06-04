package com.example.infocovid.ui.details;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

        //Save data
        ArrayList<Entry> ia = new ArrayList<>();
        //We catch the last index data array in teh currentRegion
        int count = currentRegion.getData().size() - 1;

        //We initialize our String array with Date

        String[] date = new String[4];
        date[3] = currentRegion.getData().get(count).getDate();
        date[2] = currentRegion.getData().get(count - 7).getDate();
        date[1] = currentRegion.getData().get(count - 14).getDate();
        date[0] = currentRegion.getData().get(count - 21).getDate();

        double[] actualIa = new double[4];
        actualIa[0] = currentRegion.getData().get(count - 21).getIncidentRate() - currentRegion.getData().get(count - 28).getIncidentRate();
        actualIa[1] = currentRegion.getData().get(count - 14).getIncidentRate() - currentRegion.getData().get(count - 21).getIncidentRate();
        actualIa[2] = currentRegion.getData().get(count - 7).getIncidentRate() - currentRegion.getData().get(count - 14).getIncidentRate();
        actualIa[3] = currentRegion.getData().get(count).getIncidentRate() - currentRegion.getData().get(count - 7).getIncidentRate();

        MyXValueFormatter xValueFormatter = new MyXValueFormatter(date);

        //Adding data to our Entries

        ia.add(new Entry(0,doubleToFloat(currentRegion.getData().get(count - 21).getIncidentRate()) -
                doubleToFloat(currentRegion.getData().get(count - 28).getIncidentRate())));
        ia.add(new Entry(1,doubleToFloat(currentRegion.getData().get(count - 14).getIncidentRate()) -
                doubleToFloat(currentRegion.getData().get(count - 21).getIncidentRate())));
        ia.add(new Entry(2, doubleToFloat(currentRegion.getData().get(count - 7).getIncidentRate()) -
                doubleToFloat(currentRegion.getData().get(count - 14).getIncidentRate())));
        ia.add(new Entry(3, doubleToFloat(currentRegion.getData().get(count).getIncidentRate()) -
                doubleToFloat(currentRegion.getData().get(count - 7).getIncidentRate())));

        //Customizing the Axis
        XAxis xAxis = covidDataLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(10);
        xAxis.setValueFormatter(xValueFormatter);

        YAxis rightAxis = covidDataLineChart.getAxisRight();
        rightAxis.setEnabled(false);



        LineDataSet set1 = new LineDataSet(ia, currentRegion.getName());

        set1.setLineWidth(2f);
        set1.setColor(Color.BLACK);
        set1.setDrawValues(false);
        set1.setDrawFilled(true);
        set1.setDrawCircles(true);
        set1.setDrawCircleHole(true);
        set1.setCircleColor(Color.GRAY);
        set1.setCircleColorHole(Color.WHITE);
        set1.setCircleRadius(8);
        set1.setCircleHoleRadius(6);

        if (actualIa[0] < 50) {
            set1.setFillColor(Color.GREEN);
        } else if (actualIa[1] < 150) {
            set1.setFillColor(Color.YELLOW);
        } else {
            set1.setFillColor(Color.RED);
        }

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);



        LineData data = new LineData(dataSets);

        Description description = new Description();
        description.setText("");
        covidDataLineChart.setDescription(description);
        covidDataLineChart.setDoubleTapToZoomEnabled(false);
        covidDataLineChart.setTouchEnabled(false);
        covidDataLineChart.setData(data);
    }

    public static Float doubleToFloat(double doubleValue){
        return (float) doubleValue;
    }


    /**
     * Static Class to Format de X Axis
     * */
    private static class MyXValueFormatter implements IAxisValueFormatter {

        String[] date;

        public MyXValueFormatter(String[] date){
            this.date = date;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            axis.setLabelCount(4, true);

            return this.date[(int) value];
        }
    }
}