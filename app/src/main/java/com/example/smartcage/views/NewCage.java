package com.example.smartcage.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smartcage.R;

public class NewCage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cage);

        // Referencias
        EditText cageName = findViewById(R.id.CageName);
        EditText cageDescription = findViewById(R.id.CageDescription);
        Button agregarJaula = findViewById(R.id.Agregar);


    }
}