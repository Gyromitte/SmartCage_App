package com.example.smartcage.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcage.ApiService;
import com.example.smartcage.Models.ApiResponse;
import com.example.smartcage.Models.JwtResponse;
import com.example.smartcage.Models.User;
import com.example.smartcage.request.ApiClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private final ApiService apiService;

    public UserRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public MutableLiveData<ApiResponse.RegistrationResponse> registerUser(User user) {
        MutableLiveData<ApiResponse.RegistrationResponse> registrationResponseMutableLiveData = new MutableLiveData<>();

        Call<ApiResponse.RegistrationResponse> call = apiService.registerUser(user);

        call.enqueue(new Callback<ApiResponse.RegistrationResponse>() {
            @Override
            public void onResponse(Call<ApiResponse.RegistrationResponse> call, Response<ApiResponse.RegistrationResponse> response) {
                if (response.isSuccessful()) {
                    registrationResponseMutableLiveData.setValue(response.body());
                } else {
                    Log.e("API Response", "Error: " + response.code());
                    try {
                        Log.e("API Response", "Error: " + response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse.RegistrationResponse> call, Throwable t) {
                Log.e("API Failure", "Error: " + t.getMessage());
            }
        });

        return registrationResponseMutableLiveData;
    }

    public MutableLiveData<String> loginUser(String email, String password) {
        MutableLiveData<String> tokenLiveData = new MutableLiveData<>();

        Call<JwtResponse> call = apiService.loginUser(email, password);

        call.enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken();
                    tokenLiveData.setValue(token);
                } else {
                    // Manejar el error en el inicio de sesión
                    tokenLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
                // Manejar la falla en la comunicación con el servidor
                tokenLiveData.setValue(null);
            }
        });

        return tokenLiveData;
    }
}
