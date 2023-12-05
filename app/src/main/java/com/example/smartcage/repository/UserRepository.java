package com.example.smartcage.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcage.ApiService;
import com.example.smartcage.Models.ApiResponse;
import com.example.smartcage.Models.User;
import com.example.smartcage.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private ApiService apiService;
    private MutableLiveData<ApiResponse> registrationResponse;

    public UserRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
        registrationResponse = new MutableLiveData<>();
    }

    public MutableLiveData<ApiResponse> getRegistrationResponse() {
        return registrationResponse;
    }

    public void registerUser(User user) {
        Call<ApiResponse> call = apiService.registerUser(user);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    registrationResponse.setValue(response.body());
                } else {
                    // Manejar el error de la respuesta no exitosa
                    registrationResponse.setValue(new ApiResponse(false, "Error en el servidor: " + response.code()));
                    Log.e("Registration Error", response.message());
                }
            }


            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Manejar el fallo en la llamada
                registrationResponse.setValue(new ApiResponse(false, "Error de red: " + t.getMessage()));
                Log.e("Registration Failure", t.getMessage());
            }

        });
    }
}
