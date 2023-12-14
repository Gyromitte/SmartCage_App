package com.example.smartcage.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartcage.ApiService;
import com.example.smartcage.Models.Cage;
import com.example.smartcage.SharedPreferencesManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CageRepository {
    private ApiService apiService;
    private SharedPreferencesManager sharedPreferencesManager;

    public CageRepository(ApiService apiService, SharedPreferencesManager sharedPreferencesManager) {
        this.apiService = apiService;
        this.sharedPreferencesManager = sharedPreferencesManager;
    }

    public LiveData<List<Cage>> getCages() {
        MutableLiveData<List<Cage>> cagesLiveData = new MutableLiveData<>();
        String token = sharedPreferencesManager.getToken();
        apiService.getCages("Bearer " + token).enqueue(new Callback<List<Cage>>() {
            @Override
            public void onResponse(Call<List<Cage>> call, Response<List<Cage>> response) {
                if (response.isSuccessful()) {
                    cagesLiveData.setValue(response.body());
                } else {
                    cagesLiveData.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<Cage>> call, Throwable t) {
                cagesLiveData.setValue(null);
            }
        });

        return cagesLiveData;
    }
}
