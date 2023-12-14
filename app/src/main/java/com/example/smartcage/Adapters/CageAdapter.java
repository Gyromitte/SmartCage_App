package com.example.smartcage.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcage.Models.Cage;
import com.example.smartcage.R;

import java.util.List;

public class CageAdapter extends RecyclerView.Adapter<CageAdapter.CageHolder> {
    private List<Cage> cageList;

    public CageAdapter(List<Cage> cageList) {
        this.cageList = cageList;
    }

    @NonNull
    @Override
    public CageAdapter.CageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater ly = LayoutInflater.from(parent.getContext());
        View view = ly.inflate(R.layout.cage_item, parent, false);
        return new CageAdapter.CageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CageAdapter.CageHolder holder, int position) {
        Cage s = cageList.get(position);
        holder.setData(s);
    }

    @Override
    public int getItemCount() {
        return cageList.size();
    }

    public void setCages(List<Cage> newCages) {
        cageList.clear();
        cageList.addAll(newCages);
        notifyDataSetChanged();
    }

    public class CageHolder extends RecyclerView.ViewHolder {
        Cage cage;
        TextView name;
        TextView description;

        public CageHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
        }

        public void setData(Cage s) {
            cage = s;
            name.setText(s.getName());
            description.setText(s.getDescription());
        }
    }
}
