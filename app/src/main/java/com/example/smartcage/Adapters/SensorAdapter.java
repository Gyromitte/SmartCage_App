package com.example.smartcage.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcage.Models.Sensor;

import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorHolder> {
    private List<Sensor> sensorList;
    @NonNull
    @Override
    public SensorAdapter.SensorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SensorAdapter.SensorHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SensorHolder extends RecyclerView.ViewHolder {
        public SensorHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
