package com.example.smartcage.views.sensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcage.ApiService;
import com.example.smartcage.R;
import com.example.smartcage.SharedPreferencesManager;
import com.example.smartcage.repository.SensorRepository;
import com.example.smartcage.request.ApiClient;
import com.example.smartcage.viewModel.SensorViewModel;

public class GasScreen extends AppCompatActivity {

    private SensorViewModel sensorViewModel;
    private ImageView image;

    private int valor=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_screen);

        // Referencias
        TextView estado = findViewById(R.id.estado);
        TextView value = findViewById(R.id.gasValue);
        ImageView image = findViewById(R.id.image);

        // Instancias
        ApiService apiService = ApiClient.getApiService();
        String token = "bearer" + new SharedPreferencesManager(this).getToken();
        SensorRepository sensorRepository = new SensorRepository(apiService);
        sensorViewModel = new ViewModelProvider(this).get(SensorViewModel.class);

        // Obtener los datos al iniciar el activity
        String jaulaId = "jaula.gas";
        sensorViewModel.fetchSensorData(jaulaId);


        // TODO: Cambiar imagenes y textos dependiendo del valor

        // Observar los cambios desde el ViewModel
        sensorViewModel.getSensorData("jaula.gas", token).observe(this, sensorResponse -> {
            if(sensorResponse != null){
                // Mostrar el valor en el textView
                value.setText(sensorResponse.data.value);
            }
        });



    }
}