package com.example.smartcage.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcage.Adapters.CageAdapter;
import com.example.smartcage.ApiService;
import com.example.smartcage.Models.Cage;
import com.example.smartcage.R;
import com.example.smartcage.SharedPreferencesManager;
import com.example.smartcage.repository.CageRepository;
import com.example.smartcage.request.ApiClient;
import com.example.smartcage.viewModel.CageViewModel;

import java.util.ArrayList;

public class CageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_cage_fragment, container, false);

        RecyclerView rv = rootView.findViewById(R.id.rcCages);
        CageAdapter cageAdapter = new CageAdapter(new ArrayList<>());
        rv.setAdapter(cageAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);

        ApiService apiService = ApiClient.getApiService();
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(requireContext());
        CageRepository cageRepository = new CageRepository(apiService, sharedPreferencesManager);
        CageViewModel cageViewModel = new ViewModelProvider(this).get(CageViewModel.class);
        cageViewModel.init(cageRepository);

        cageViewModel.getCages().observe(getViewLifecycleOwner(), cages -> {
            if (cages != null) {
                cageAdapter.setCages(cages);
                cageAdapter.setOnCageClickListener(position -> {
                    Cage selectedCage = cages.get(position);

                    SensorsFragment sensorsFragment = new SensorsFragment();
                    Bundle args = new Bundle();
                    args.putInt("cageId", selectedCage.getId());
                    sensorsFragment.setArguments(args);

                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_containter, sensorsFragment)
                            .addToBackStack(null)
                            .commit();
                });
            } else {
                Toast.makeText(getActivity(), "Error al cargar las jaulas", Toast.LENGTH_SHORT).show();
            }
        });

        cageViewModel.loadCages();

        return rootView;
    }
}