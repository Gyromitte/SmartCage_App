package com.example.smartcage.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.smartcage.ApiService;
import com.example.smartcage.Models.Cage;
import com.example.smartcage.R;
import com.example.smartcage.SharedPreferencesManager;
import com.example.smartcage.repository.CreateCageRepository;
import com.example.smartcage.request.ApiClient;
import com.example.smartcage.views.fragments.CageFragment;

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


        agregarJaula.setOnClickListener(view -> {
            String name = cageName.getText().toString().trim();
            String description = cageDescription.getText().toString().trim();

            if (!name.isEmpty() && !description.isEmpty()) {
                ApiService.CreateCageRequest createCageRequest = new ApiService.CreateCageRequest(name, description);

                LiveData<Cage> cageLiveData = cageRepository.createCage(createCageRequest, token);

                // Remover observadores previos
                cageLiveData.removeObservers(this);

                cageLiveData.observe(this, cage -> {
                    if (cage != null) {
                        Toast.makeText(NewCage.this, "Jaula creada con éxito", Toast.LENGTH_SHORT).show();
                        agregarJaula.setEnabled(false); // Desactivar el botón después de crear la jaula

                        // Esperar 2 segundos antes de regresar al fragmento inicial
                        Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            // Aquí regresas al fragmento inicial
                            // Puedes utilizar una transacción de fragmentos para regresar al fragmento deseado
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_containter, new CageFragment()) // Reemplaza con tu fragmento inicial
                                    .commit();
                        }, 2000); // Espera 2 segundos

                    } else {
                        // Manejar cualquier error si la jaula no se creó
                        Toast.makeText(NewCage.this, "Algo Salio mal", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(NewCage.this, "Llena todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}