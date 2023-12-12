package com.example.smartcage.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcage.Adapters.CageAdapter;
import com.example.smartcage.Models.Cage;
import com.example.smartcage.R;

import java.util.ArrayList;
import java.util.List;

public class CageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_cage_fragment, container, false);

        List<Cage> cagesList = new ArrayList<>();
        cagesList.add(new Cage("Agua", "R.drawable.water_droplet"));
        cagesList.add(new Cage("Alimento", "R.drawable.cookie"));
        cagesList.add(new Cage("Agua", "R.drawable.water_droplet"));
        cagesList.add(new Cage("Agua", "R.drawable.water_droplet"));
        cagesList.add(new Cage("Agua", "R.drawable.water_droplet"));

        CageAdapter sa = new CageAdapter(cagesList);
        RecyclerView rv = rootView.findViewById(R.id.rcCages);

        rv.setAdapter(sa);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);

        return rootView;
    }
}