package com.example.smartcage.views;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcage.ApiService;
import com.example.smartcage.Models.Cage;
import com.example.smartcage.R;
import com.example.smartcage.SharedPreferencesManager;
import com.example.smartcage.repository.CageRepository;
import com.example.smartcage.repository.CreateCageRepository;
import com.example.smartcage.repository.SensorRepository;
import com.example.smartcage.request.ApiClient;

public class NewCage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cage);

        // Referencias
        EditText cageName = findViewById(R.id.CageName);
        EditText cageDescription = findViewById(R.id.CageDescription);
        Button agregarJaula = findViewById(R.id.Agregar);

        // Instancias
        ApiService apiService = ApiClient.getApiService();
        String token = "bearer" + new SharedPreferencesManager(this).getToken();
        CreateCageRepository cageRepository = new CreateCageRepository(apiService);

        // Evento clic del botón
        agregarJaula.setOnClickListener(view -> {
            // Obtener los valores de name y description
            String name = cageName.getText().toString().trim();
            String description = cageDescription.getText().toString().trim();

            // Verificar que los campos no estén vacíos
            if (!name.isEmpty() && !description.isEmpty()) {
                // Crear una instancia de CreateCageRequest con los valores de name y description
                ApiService.CreateCageRequest createCageRequest = new ApiService.CreateCageRequest(name, description);

                // Llamar al método createCage del CageRepository para asociar la jaula al usuario
                LiveData<Cage> cageLiveData = cageRepository.createCage(createCageRequest, token);

                // Observar el LiveData para recibir la respuesta de la solicitud
                cageLiveData.observe(this, cage -> {
                    // Manejar la respuesta de la solicitud aquí
                    if (cage != null) {
                        // La jaula se creó con éxito, puedes manejarla aquí si es necesario
                        Toast.makeText(NewCage.this, "Jaula creada con éxito", Toast.LENGTH_SHORT).show();
                    } else {
                        // Manejar cualquier error si la jaula no se creó
                        Toast.makeText(NewCage.this, "Algo Salio mal", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(NewCage.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
                // Mostrar un mensaje al usuario indicando que los campos son obligatorios
                // o manejar el caso cuando los campos estén vacíos
            }
        });
    }
}