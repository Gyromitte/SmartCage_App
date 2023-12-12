package com.example.smartcage.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcage.R;
import com.example.smartcage.viewModel.UserViewModel;

public class RegisteredAccount extends AppCompatActivity {

    private Button login;
    private EditText passwordText;
    private EditText emailText;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_account);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        login=findViewById(R.id.login);
        emailText=findViewById(R.id.emailText);
        passwordText=findViewById(R.id.passwordText);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisteredAccount.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                userViewModel.loginUser(email, password).observe(RegisteredAccount.this, token -> {
                    if (token != null) {
                        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("token", token);
                        editor.apply();

                        Intent i = new Intent(RegisteredAccount.this, MainActivity.class);
                        startActivity(i);



                    } else {
                        // Error en el inicio de sesión, muestra un mensaje adecuado
                        Toast.makeText(RegisteredAccount.this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}