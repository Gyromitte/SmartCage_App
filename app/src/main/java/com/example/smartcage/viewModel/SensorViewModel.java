package com.example.smartcage.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcage.ApiService;
import com.example.smartcage.Models.SensorResponse;
import com.example.smartcage.Retrofit.RetrofitRequest;
import com.example.smartcage.repository.SensorRepository;

public class SensorViewModel extends ViewModel {
    private SensorRepository sensorRepository;
    private MutableLiveData<SensorResponse> sensorData = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<String> failureMessage = new MutableLiveData<>();

    public SensorViewModel() {
        this.sensorRepository = new SensorRepository(RetrofitRequest.getRetrofit().create(ApiService.class));
    }

    public LiveData<SensorResponse> getSensorData(String jaulaId, String token) {
        return sensorRepository.obtenerDatosDelSensor(jaulaId, token);
    }

    public LiveData<SensorResponse> sendData(String jaulaId, String token, String dato){
        return sensorRepository.enviarDatos(jaulaId, token, dato);
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<String> getFailureMessage() {
        return failureMessage;
    }

    public void fetchSensorData(String jaulaId) {

    }
}
