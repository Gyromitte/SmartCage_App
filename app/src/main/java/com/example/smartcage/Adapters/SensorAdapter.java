package com.example.smartcage.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcage.Models.Sensor;
import com.example.smartcage.R;

import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorHolder> {
    private List<Sensor> sensorList;

    public SensorAdapter(List<Sensor> sensorList) {
        this.sensorList = sensorList;
    }

    @NonNull
    @Override
    public SensorAdapter.SensorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater ly = LayoutInflater.from(parent.getContext());
        View view = ly.inflate(R.layout.sensor_item, parent, false);
        return new SensorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorAdapter.SensorHolder holder, int position) {
        Sensor s = sensorList.get(position);
        holder.setData(s);
    }

    @Override
    public int getItemCount() {
        return sensorList.size();
    }

    public class SensorHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView icon;

        Sensor sensor;
        public SensorHolder(@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            icon = itemView.findViewById(R.id.icon);
        }

        public void setData(Sensor s)
        {
            sensor = s;
            name.setText(s.getNombre());
            icon.setImageResource(s.getIconResId());
        }
    }


}
