package com.example.smartcage.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartcage.ApiService;
import com.example.smartcage.Models.Cage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCageRepository {
    private ApiService apiService;

    public CreateCageRepository(ApiService apiService){ this.apiService = apiService;}

    public LiveData<Cage> createCage(ApiService.CreateCageRequest createCageRequest, String token){
        MutableLiveData<Cage> mutableLiveData = new MutableLiveData<>();
        apiService.createCage(createCageRequest, token).enqueue(new Callback<Cage>() {
            @Override
            public void onResponse(Call<Cage> call, Response<Cage> response) {
                if(response.code() == 201){
                    mutableLiveData.setValue(response.body());
                }else{
                    Log.e("CageRepository", "Error al crear la jaula onResponse" + response.code());
                    Log.e("TOKEN", "TOKEN: " + token);
                }
            }

            @Override
            public void onFailure(Call<Cage> call, Throwable t) {
                Log.e("CageRepository", "Error al crear la jaula " + t.getMessage());
                Log.e("TOKEN", "TOKEN: " + token);
            }
        });
        return mutableLiveData;
    }
}
