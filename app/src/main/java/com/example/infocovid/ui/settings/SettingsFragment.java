package com.example.infocovid.ui.settings;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.infocovid.R;
import com.example.infocovid.datalayer.datamodels.RegionList;
import com.example.infocovid.datalayer.model.MySettings;
import com.example.infocovid.datalayer.model.PreferencesManager;
import com.example.infocovid.ui.main.MainViewModel;

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
    Switch switchUsarUbicacion;
    Switch switchNotificaciones;
    Switch switchActivarWidget;

    private SettingsViewModel settingsViewModel;

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

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        // TextViews
        switchUsarUbicacion = root.findViewById(R.id.switchUsarUbicacion);
        switchNotificaciones = root.findViewById(R.id.switchNotificaciones);
        switchActivarWidget = root.findViewById(R.id.switchActivarWidget);

        settingsViewModel.getData().observe(getViewLifecycleOwner(), new Observer<MySettings>() {
            @Override
            public void onChanged(@Nullable MySettings mySettings) {
                if (mySettings != null) {
                    switchUsarUbicacion.setChecked(mySettings.getAllowLocation());
                    switchNotificaciones.setChecked(mySettings.getAllowNotifications());
                    switchActivarWidget.setChecked(mySettings.getEnableWidget());
                }
            }
        });

        // listeners for the switchers
        switchUsarUbicacion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveSettings();
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

        return root;
    }

    public void saveSettings() {

        MySettings newSettings = new MySettings(switchUsarUbicacion.isChecked(), switchNotificaciones.isChecked(), switchActivarWidget.isChecked());
        settingsViewModel.setData(newSettings);
    }

}