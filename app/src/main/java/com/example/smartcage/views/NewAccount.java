package com.example.smartcage.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcage.Models.User;
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
                User user = new User(name, lastName, email, password);
                userViewModel.getRegistrationResponse().removeObservers(NewAccount.this);

                userViewModel.registerUser(user);

                userViewModel.getRegistrationResponse().observe(NewAccount.this, apiResponse -> {
                    if (apiResponse != null) {
                        if (apiResponse.isSuccess()) {
                            Intent i = new Intent(NewAccount.this, RegisteredAccount.class);
                            startActivity(i);
                            finish();

                        } else {
                            Toast.makeText(NewAccount.this, "Error en el registroo: " + apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
