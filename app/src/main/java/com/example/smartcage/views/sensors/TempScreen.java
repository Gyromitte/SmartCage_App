package com.example.smartcage.views.sensors;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcage.ApiService;
import com.example.smartcage.R;
import com.example.smartcage.SharedPreferencesManager;
import com.example.smartcage.repository.SensorRepository;
import com.example.smartcage.request.ApiClient;
import com.example.smartcage.viewModel.SensorViewModel;

public class TempScreen extends AppCompatActivity {

    private SensorViewModel sensorViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_screen);

        TextView temperatura = findViewById(R.id.temperatura);

        // Crear instancias necesarias: ApiService, SensorRepository y SensorViewModel
        ApiService apiService = ApiClient.getApiService();
        String token = "bearer" + new SharedPreferencesManager(this).getToken();
        SensorRepository sensorRepository = new SensorRepository(apiService);
        sensorViewModel = new ViewModelProvider(this).get(SensorViewModel.class);

        // Observar los cambios en los datos del sensor desde el ViewModel
        sensorViewModel.getSensorData("jaula.temperatura", token).observe(this, sensorResponse -> {
            if (sensorResponse != null) {
                // Aquí actualizas tu TextView con la respuesta del sensor
                temperatura.setText(sensorResponse.data.value);
            }
        });

        // Obtener datos del sensor al iniciar la actividad (puedes hacerlo en respuesta a algún evento)
        String jaulaId = "jaula.temperatura"; // Debes proporcionar el ID de la jaula aquí
        sensorViewModel.fetchSensorData(jaulaId);
    }
}