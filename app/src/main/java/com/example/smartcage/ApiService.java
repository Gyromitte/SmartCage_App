package com.example.smartcage;

import com.example.smartcage.Models.ApiResponse;
import com.example.smartcage.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/registro")
    Call<ApiResponse> registerUser(@Body User user);
}
