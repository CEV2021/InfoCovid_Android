package com.example.infocovid.ui.main;

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
import com.example.infocovid.datalayer.datamodels.Data;
import com.example.infocovid.datalayer.datamodels.RegionList;
import com.example.infocovid.ui.dashboard.DashboardViewModel;

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

    TextView nombreCiudad;
    TextView incidenciaAcumulada;
    TextView casosTotales;
    TextView nuevosCasos;
    TextView curados;
    TextView fallecidos;


    private MainViewModel mainViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        View root = inflater.inflate(R.layout.fragment_main, container, false);

//        final TextView textView = root.findViewById(R.id.text_dashboard);

        // datos a actualizar
        nombreCiudad = root.findViewById(R.id.ciudadNombre);
        incidenciaAcumulada = root.findViewById(R.id.incidenciaAcumuladaNumero);
        casosTotales = root.findViewById(R.id.casosTotalesTableNumero);
        nuevosCasos = root.findViewById(R.id.nuevosCasosTableNumero);
        curados = root.findViewById(R.id.curadosTableNumero);
        fallecidos = root.findViewById(R.id.fallecidosTableNumero);

        mainViewModel.getData().observe(getViewLifecycleOwner(), new Observer<RegionList>() {
            @Override
            public void onChanged(@Nullable RegionList list) {
                
//                textView.setText("");
            }
        });

        return root;
    }

    public MainFragment() {
        // Required empty public constructor
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