package com.example.smartcage;

import com.example.smartcage.Models.ApiResponse;
import com.example.smartcage.Models.JwtResponse;
import com.example.smartcage.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/register")
    Call<ApiResponse.RegistrationResponse> registerUser(@Body User user);

    @FormUrlEncoded
    @POST("api/login")
    Call<JwtResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );
}