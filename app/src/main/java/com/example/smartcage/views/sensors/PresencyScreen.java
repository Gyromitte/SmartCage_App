package com.example.smartcage.views.sensors;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcage.ApiService;
import com.example.smartcage.R;
import com.example.smartcage.SharedPreferencesManager;
import com.example.smartcage.repository.SensorRepository;
import com.example.smartcage.request.ApiClient;
import com.example.smartcage.viewModel.SensorViewModel;

public class PresencyScreen extends AppCompatActivity {

    private SensorViewModel sensorViewModel;
    private ImageView presency_Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presency_screen);

        TextView estadoPresencia = findViewById(R.id.estado_agua);
        ImageView presency_Image = findViewById(R.id.presency_Image);

        // Crear instancias necesarias: ApiService, SensorRepository y SensorViewModel
        ApiService apiService = ApiClient.getApiService();
        String token = "bearer" + new SharedPreferencesManager(this).getToken();
        SensorRepository sensorRepository = new SensorRepository(apiService);
        sensorViewModel = new ViewModelProvider(this).get(SensorViewModel.class);

        // Observar los cambios en los datos del sensor desde el ViewModel
        sensorViewModel.getSensorData("jaula.ultrasonico", token).observe(this, sensorResponse -> {
            if (sensorResponse != null) {
                int estado = Integer.parseInt(sensorResponse.data.value);
                if(estado == 1) {
                    presency_Image.setImageResource(R.drawable.presencyes);
                    estadoPresencia.setText("Tu mascota se \n encuentra en la jaula");
                } else {
                    presency_Image.setImageResource(R.drawable.missing);
                    estadoPresencia.setText("Tu mascota no se \n encuentra en la jaula");
                }
            }
        });

        // Obtener datos del sensor al iniciar la actividad (puedes hacerlo en respuesta a algún evento)
        String jaulaId = "jaula.agua"; // Debes proporcionar el ID de la jaula aquí
        sensorViewModel.fetchSensorData(jaulaId);
    }

}
