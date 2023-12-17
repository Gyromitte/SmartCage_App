package com.example.smartcage.views.sensors;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcage.ApiService;
import com.example.smartcage.R;
import com.example.smartcage.SharedPreferencesManager;
import com.example.smartcage.repository.SensorRepository;
import com.example.smartcage.request.ApiClient;
import com.example.smartcage.viewModel.SensorViewModel;

public class SoundScreen extends AppCompatActivity {
    //1 es fuerte
    private SensorViewModel sensorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_screen);

        // Referencias
        TextView estado = findViewById(R.id.estado);
        TextView value = findViewById(R.id.value);
        ImageView image = findViewById(R.id.image);

        // Instancias
        ApiService apiService = ApiClient.getApiService();
        String token = "bearer" + new SharedPreferencesManager(this).getToken();
        SensorRepository sensorRepository = new SensorRepository(apiService);
        sensorViewModel = new ViewModelProvider(this).get(SensorViewModel.class);

        // Observar los cambios en el sensor
        sensorViewModel.getSensorData("jaula.sonido", token).observe(this, sensorResponse -> {
            if(sensorResponse != null){
                int sonido = Integer.parseInt(sensorResponse.data.value);
                if(sonido == 1){
                    image.setImageResource(R.drawable.sound);
                    estado.setText("Hay sonido dentro de la jaula \n actualmente");
                    value.setText("Tu mascota podria molestarse");
                }else if(sonido == 0){
                    image.setImageResource(R.drawable.nosound);
                    estado.setText("No hay sonido actualmente \n en la jaula ");
                    value.setText("Tu mascota deberia estar bien");
                }
            }
        });



    }

}
