package com.example.infocovid.ui.settings;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infocovid.MainActivity;
import com.example.infocovid.R;
import com.example.infocovid.datalayer.model.RegionList;
import com.example.infocovid.datalayer.model.MySettings;
import com.example.infocovid.datalayer.model.Point;
import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.datalayer.model.Region;
import com.example.infocovid.datalayer.model.SearchData;
import com.example.infocovid.datalayer.model.adapters.FavoriteRegionsAdapter;
import com.example.infocovid.ui.main.MainViewModel;
import com.example.infocovid.utils.GPSTracker;
import com.example.infocovid.utils.GeneralHelper;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Views
    View root;
    Switch switchUsarUbicacion;
    Switch switchNotificaciones;
    Switch switchActivarWidget;
    TextView dataFrom;
    TextView avoid;
    TextView contact;
    TextView manual;

    Activity activity;
    private GPSTracker gpsTracker;


    private SettingsViewModel settingsViewModel;

    // Location
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // this is sample code, in case we want to send some data
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        root = inflater.inflate(R.layout.fragment_settings, container, false);

        // TextViews
        switchUsarUbicacion = root.findViewById(R.id.switchUsarUbicacion);
        switchNotificaciones = root.findViewById(R.id.switchNotificaciones);
        switchActivarWidget = root.findViewById(R.id.switchActivarWidget);
        dataFrom = root.findViewById(R.id.deDondeVienenLosDatos);
        avoid = root.findViewById(R.id.comoEvitarContagios);
        contact = root.findViewById(R.id.contacto);
        manual = root.findViewById(R.id.manual);

        goLink();

        settingsViewModel.getData().observe(getViewLifecycleOwner(), new Observer<MySettings>() {
            @Override
            public void onChanged(@Nullable MySettings mySettings) {
                if (mySettings != null) {
                    // Setting the right value on the switchers
                    switchUsarUbicacion.setChecked(mySettings.getAllowLocation());
                    switchNotificaciones.setChecked(mySettings.getAllowNotifications());
                    switchActivarWidget.setChecked(mySettings.getEnableWidget());
                }

                // Adding the listeners for the switchers
                switchUsarUbicacion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Region gpsRegion = null;
                        // first we check if we have to notify the user about the permissions
                        if (isChecked) {
                            try {
                                if (ActivityCompat.checkSelfPermission(root.getContext(), mPermission) != PackageManager.PERMISSION_GRANTED) {

                                    ActivityCompat.requestPermissions(getActivity(), new String[]{mPermission}, REQUEST_CODE_PERMISSION);
                                    // If any permission above not allowed by user, this condition will execute every time, else your else part will work
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                if (ActivityCompat.checkSelfPermission(root.getContext(), mPermission) == PackageManager.PERMISSION_GRANTED) {

                                    gpsRegion = GeneralHelper.getCurrentRegionByGPS(root.getContext());

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        } else {
                            GeneralHelper.removeCurrentRegionByGPS(root.getContext());
                        }

                        if (isChecked && gpsRegion == null) {
                            switchUsarUbicacion.setChecked(false);
                        } else {
                            // Now we call the method to save the settings
                            saveSettings();
                        }

                    }
                });
                switchNotificaciones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        saveSettings();
                    }
                });
                switchActivarWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        saveSettings();
                    }
                });
            }
        });

        return root;
    }

    /**
     * Method to display our website's in the users browsers
     * */

    public void goLink(){

            dataFrom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "http://infocovid.epizy.com/obtencion_datos.html";
                    Uri _link  = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, _link);
                    startActivity(intent);
                }
            });

            avoid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "http://infocovid.epizy.com/evitar_contagios.html";
                    Uri _link  = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, _link);
                    startActivity(intent);
                }
            });

            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "http://infocovid.epizy.com/index.html#footer";
                    Uri _link  = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, _link);
                    startActivity(intent);
                }
            });

            manual.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "http://infocovid.epizy.com/manual_de_uso.html";
                    Uri _link  = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, _link);
                    startActivity(intent);
                }
            });
    }

    /**
     *
     */
    public void saveSettings() {

        // Getting the values of the switchers and using them to create a new settings object
        MySettings newSettings = new MySettings(switchUsarUbicacion.isChecked(), switchNotificaciones.isChecked(), switchActivarWidget.isChecked());

        // Here we notify the user to get it out of the way (no possible issues can pop from saving the settings)
        Snackbar.make(root, "Settings saved automatically", Snackbar.LENGTH_SHORT).show();
        // And now we call the viewModel to store the data
        settingsViewModel.setData(newSettings);
    }

}