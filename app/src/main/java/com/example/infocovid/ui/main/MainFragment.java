package com.example.infocovid.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.infocovid.R;
import com.example.infocovid.datalayer.model.Data;
import com.example.infocovid.datalayer.model.Region;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




    // Views
    View root;
    TextView regionNameTextView;
    ImageView covidStatusImageView;
    TextView cumulativeIncidenceTextView;
    TextView totalCasesTextView;
    TextView newCasesTextView;
    TextView totalCuredTextView;
    TextView totalDeceasedTextView;

    // View model for this view
    private MainViewModel mainViewModel;

    public MainFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Setting the view model
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Inflating the fragment and storing a reference to the View
        // - quite helpful to use as Context provider!
        root = inflater.inflate(R.layout.fragment_main, container, false);

        // Storing views into fragment variables
        regionNameTextView = root.findViewById(R.id.regionName);
        covidStatusImageView = root.findViewById(R.id.covidStatus);
        cumulativeIncidenceTextView = root.findViewById(R.id.cumulativeIncidenceValue);
        totalCasesTextView = root.findViewById(R.id.regionDataTotalCasesValue);
        newCasesTextView = root.findViewById(R.id.regionDataNewCasesValue);
        totalCuredTextView = root.findViewById(R.id.regionDataTotalCuredValue);
        totalDeceasedTextView = root.findViewById(R.id.regionDataTotalDeceasedValue);

        // Here we are declaring the "subscriber" for the MVVM
        // - every time the model gets changed and notified, then this fragment gets drawn again
        // - this way we don't have to handle data changes by ourselves
        mainViewModel.getData().observe(getViewLifecycleOwner(), new Observer<Region>() {
            @Override
            public void onChanged(@Nullable Region currentRegion) {

                // First we check if we have received something to update the fragment
                // - if we have a region set as current, then we start rendering the data on the view
                // - if the region is null, then the fragment keeps the placeholder data
                // - todo: recover the visibility setup from master to hide the elements and show a "no data" message or something
                if (currentRegion != null) {
                    // setting the region name
                    regionNameTextView.setText(currentRegion.getName());

                    // Here we get the index of the latest data set
                    int latest = currentRegion.getData().size() - 1;
                    // ...an the dataset as well
                    Data latestData = currentRegion.getData().get(latest);

                    // Now we set the image depending on the incidence
                    // @todo: set also de image description
                    if (currentRegion.getData().get(latest).getIncidentRate() < 50) {
                        covidStatusImageView.setImageResource(R.mipmap.ic_greencovid);
                    } else if (currentRegion.getData().get(latest).getIncidentRate() < 150) {
                        covidStatusImageView.setImageResource(R.mipmap.ic_yellowcovid);
                    } else {
                        covidStatusImageView.setImageResource(R.mipmap.ic_redcovid);
                    }

                    // and then we continue setting the data on the fragment
                    cumulativeIncidenceTextView.setText(String.format("%.2f", latestData.getIncidentRate()));
                    totalCasesTextView.setText(String.valueOf(latestData.getConfirmed()));
                    newCasesTextView.setText(String.valueOf(latestData.getActive()));
                    totalCuredTextView.setText(String.valueOf(latestData.getRecovered()));
                    totalDeceasedTextView.setText(String.valueOf(latestData.getDeaths()));
                }
            }
        });

        // Now we return the view we've just built
        // - if there are any data changes, then the view will get automatically updated when notified
        return root;
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
}