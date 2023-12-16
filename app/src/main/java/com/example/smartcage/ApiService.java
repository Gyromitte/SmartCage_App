package com.example.smartcage;

import com.example.smartcage.Models.ApiResponse;
import com.example.smartcage.Models.Cage;
import com.example.smartcage.Models.JwtResponse;
import com.example.smartcage.Models.LogoutResponse;
import com.example.smartcage.Models.SensorResponse;
import com.example.smartcage.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("api/register")
    Call<ApiResponse.RegistrationResponse> registerUser(@Body User user);

    @FormUrlEncoded
    @POST("api/login")
    Call<JwtResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("api/cages")
    Call<List<Cage>> getCages(@Header("Authorization") String token);

    @GET("cages/sensors/{sensor_route}")
    Call<SensorResponse> obtenerDatos(@Path("sensor_route") String jaulaId,
    @Header("Authorization") String authorization);

    @POST("datos/{sensor_route}/{dato}")
    Call<SensorResponse> enviarDatos(@Path("sensor_route") String sensorRoute, @Path("dato") String dato,
                                     @Header("Authorization") String authorization);

    @POST("logout")
    Call<LogoutResponse> logout();

}
