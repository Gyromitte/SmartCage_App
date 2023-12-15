package com.example.smartcage.views.sensors;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button presencia;
    private String accion;
    private int valor=0;
    PresencyScreen presencyScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presency_screen);

        TextView estadoPresencia = findViewById(R.id.estado_agua);
        ImageView presency_Image = findViewById(R.id.presency_Image);
        Button presencia = findViewById(R.id.boton);

        // Crear instancias necesarias: ApiService, SensorRepository y SensorViewModel
        ApiService apiService = ApiClient.getApiService();
        String token = "bearer" + new SharedPreferencesManager(this).getToken();
        SensorRepository sensorRepository = new SensorRepository(apiService);
        sensorViewModel = new ViewModelProvider(this).get(SensorViewModel.class);

        // Observar los cambios en los datos del sensor desde el ViewModel
        sensorViewModel.getSensorData("jaula.puerta", token).observe(this, sensorResponse -> {
            if (sensorResponse != null) {
                int estado = Integer.parseInt(sensorResponse.data.value);
                if(estado == 1) {
                    presency_Image.setImageResource(R.drawable.missing);
                    estadoPresencia.setText("Jaula abierta");
                    valor=1;
                    presencia.setText("Cerrar");
                } else {
                    presency_Image.setImageResource(R.drawable.presencyes);
                    estadoPresencia.setText("Jaula cerrada");
                    presencia.setText("Abrir");
                    valor=0;
                }
            }
        });

        presencyScreen = this;


        presencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Definir dato = 1

                if(valor==1) { accion = "0";}
                else { accion = "1";}

                sensorViewModel.sendData("jaula.puerta", token, accion).observe(presencyScreen, sensorResponse -> {
                    if(sensorResponse != null){
                        Toast.makeText(getApplicationContext(), "Dato enviado!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Dato no enviado", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Obtener datos del sensor al iniciar la actividad (puedes hacerlo en respuesta a algún evento)
        String jaulaId = "jaula.ultrasonico"; // Debes proporcionar el ID de la jaula aquí
        sensorViewModel.fetchSensorData(jaulaId);
    }

}
