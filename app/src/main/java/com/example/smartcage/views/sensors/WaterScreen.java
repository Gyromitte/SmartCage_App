package com.example.smartcage.views.sensors;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class WaterScreen extends AppCompatActivity {

    private static final int MAX_PROGRESO = 100;
    private ImageView imagenVasoLleno;
    private SensorViewModel sensorViewModel;
    private TextView estadoAgua;
    private Button add_water;

    private boolean ajusteRealizado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_screen);

        imagenVasoLleno = findViewById(R.id.water_bowl);
        add_water = findViewById(R.id.add_water);
        estadoAgua = findViewById(R.id.estado_agua);

        // Crear instancias necesarias: ApiService, SensorRepository y SensorViewModel
        ApiService apiService = ApiClient.getApiService();
        String token = "bearer" + new SharedPreferencesManager(this).getToken();
        SensorRepository sensorRepository = new SensorRepository(apiService);
        sensorViewModel = new ViewModelProvider(this).get(SensorViewModel.class);

        // Observar los cambios en los datos del sensor desde el ViewModel
        sensorViewModel.getSensorData("jaula.agua", token).observe(this, sensorResponse -> {
            if (sensorResponse != null) {
                // Aquí actualizas tu TextView con la respuesta del sensor
                int estado = Integer.parseInt(sensorResponse.data.value);
                if(estado == 1) {
                    estadoAgua.setText("El bowl está lleno");
                    add_water.setEnabled(false);
                    onWindowFocusChanged(true);
                } else {
                    estadoAgua.setText("El bowl está vacío");
                    add_water.setEnabled(true);
                    onWindowFocusChanged(false);
                }
            }
        });

        // Obtener datos del sensor al iniciar la actividad (puedes hacerlo en respuesta a algún evento)
        String jaulaId = "jaula.agua"; // Debes proporcionar el ID de la jaula aquí
        sensorViewModel.fetchSensorData(jaulaId);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // Image animation
    /*    if (!ajusteRealizado) {
            int progreso = 0;

            if (progreso >= 0 && progreso <= MAX_PROGRESO) {
                int alturaVisible = (imagenVasoLleno.getHeight() * progreso) / MAX_PROGRESO;
                imagenVasoLleno.setClipBounds(new Rect(0, imagenVasoLleno.getHeight() - alturaVisible,
                        imagenVasoLleno.getWidth(), imagenVasoLleno.getHeight()));
            } else {
                imagenVasoLleno.setVisibility(View.INVISIBLE);
            }

            ajusteRealizado = true;
        }*/

        if(!hasFocus) {
            imagenVasoLleno.setVisibility(View.INVISIBLE);
        } else {
            int alturaVisible = imagenVasoLleno.getHeight();
            imagenVasoLleno.setClipBounds(new Rect(0, imagenVasoLleno.getHeight() - alturaVisible,
                    imagenVasoLleno.getWidth(), imagenVasoLleno.getHeight()));

        }
    }
}
