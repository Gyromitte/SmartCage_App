package com.example.smartcage.views.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.smartcage.R;

public class WaterScreen extends AppCompatActivity {

    private static final int MAX_PROGRESO = 100;
    private ImageView imagenVasoLleno;

    private TextView estadoAgua;

    private boolean ajusteRealizado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_screen);

        imagenVasoLleno = findViewById(R.id.water_bowl);

        estadoAgua = findViewById(R.id.estado_agua);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // Image animation
        if (!ajusteRealizado) {
            int progreso = 0;

            if (progreso >= 0 && progreso <= MAX_PROGRESO) {
                int alturaVisible = (imagenVasoLleno.getHeight() * progreso) / MAX_PROGRESO;
                imagenVasoLleno.setClipBounds(new Rect(0, imagenVasoLleno.getHeight() - alturaVisible,
                        imagenVasoLleno.getWidth(), imagenVasoLleno.getHeight()));
            } else {
                imagenVasoLleno.setVisibility(View.INVISIBLE);
            }

            ajusteRealizado = true;
        }
    }
}
