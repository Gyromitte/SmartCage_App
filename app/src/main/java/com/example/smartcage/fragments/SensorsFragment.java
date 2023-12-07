package com.example.smartcage.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcage.Adapters.SensorAdapter;
import com.example.smartcage.Models.Sensor;
import com.example.smartcage.R;
import com.example.smartcage.views.sensors.FoodScreen;
import com.example.smartcage.views.sensors.WaterScreen;

import java.util.ArrayList;
import java.util.List;

public class SensorsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sensors, container, false);

        List<Sensor> sensorList = new ArrayList<>();
        sensorList.add(new Sensor("Agua", R.drawable.water_droplet));
        sensorList.add(new Sensor("Alimento", R.drawable.cookie));
        sensorList.add(new Sensor("Gas", R.drawable.gas));
        sensorList.add(new Sensor("Proximidad", R.drawable.proximity));
        // TODO: Replace icon for temperature
        sensorList.add(new Sensor("Temperatura", R.drawable.proximity));

        SensorAdapter sa = new SensorAdapter(sensorList);
        RecyclerView rv = rootView.findViewById(R.id.rcSensors);

        // Configurar el listener para el clic en el adaptador
        sa.setOnSensorClickListener(position -> {

            switch (position){

            switch(position){

                case 0:
                    Intent intent = new Intent(getActivity(), WaterScreen.class);
                    startActivity(intent);
                    break;
                case 1:
                    Intent foodIntent = new Intent(getActivity(), FoodScreen.class);
                    startActivity(foodIntent);
            }

        });

        rv.setAdapter(sa);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);

        return rootView;
    }
}
