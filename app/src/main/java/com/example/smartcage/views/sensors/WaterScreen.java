package com.example.smartcage.views.sensors;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.smartcage.R;

public class WaterScreen extends AppCompatActivity {

    private static final int MAX_PROGRESO = 100;
    private ImageView imagenVasoLleno;
    private boolean ajusteRealizado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_screen);

        imagenVasoLleno = findViewById(R.id.water_bowl);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!ajusteRealizado) {
            int progreso = 50;

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
