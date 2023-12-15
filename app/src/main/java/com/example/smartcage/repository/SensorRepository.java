package com.example.smartcage.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartcage.ApiService;
import com.example.smartcage.Models.Sensor;
import com.example.smartcage.Models.SensorResponse;
import com.example.smartcage.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SensorRepository {

    // Servicio
    private ApiService apiService;
    private SharedPreferencesManager sharedPreferencesManager;


    // Instancia de retrofit
    public SensorRepository(ApiService apiService){
        this.apiService = apiService;
    }
    public LiveData<SensorResponse> obtenerDatosDelSensor(String jaulaId, String token) {
        MutableLiveData<SensorResponse> mutableLiveData = new MutableLiveData<>();
        apiService.obtenerDatos(jaulaId, token).enqueue(new Callback<SensorResponse>() {
            @Override
            public void onResponse(Call<SensorResponse> call, Response<SensorResponse> response) {
                if(response.code() == 200){
                    mutableLiveData.setValue(response.body());
                }else
                {

                }
            }

            @Override
            public void onFailure(Call<SensorResponse> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

    public interface DatosSensorCallback {
        void onDatosRecibidos(SensorResponse datos);

        void onError(String mensaje);

        void onFailure(String mensajeError);
    }
}
