package com.example.smartcage.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcage.R;
import com.example.smartcage.viewModel.UserViewModel;

public class NewAccount extends AppCompatActivity {

    private EditText nameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registrar;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        nameEditText = findViewById(R.id.nameEdit);
        lastNameEditText = findViewById(R.id.lastNameEdit);
        emailEditText = findViewById(R.id.emailText);
        passwordEditText = findViewById(R.id.passwordEdit);
        registrar = findViewById(R.id.registrar);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(NewAccount.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                userViewModel.registerUser(name, lastName, email, password).observe(NewAccount.this, registrationResponse -> {

                    if (registrationResponse != null) {
                        // Registro exitoso, maneja la respuesta según sea necesario
                        String message = registrationResponse.getMessage();
                        // Puedes mostrar un Toast, navegar a otra pantalla, etc.
                        // Muestra el mensaje en un Toast
                        Toast.makeText(NewAccount.this, message, Toast.LENGTH_SHORT).show();
                        Log.d("Registro", "Respuesta del servidor: " + message);
                    }
                });
            }
        });
    }
}
