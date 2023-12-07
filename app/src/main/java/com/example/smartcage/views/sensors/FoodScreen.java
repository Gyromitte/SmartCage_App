package com.example.smartcage.views.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.smartcage.R;

public class FoodScreen extends AppCompatActivity {

    private static final int MAX_PROGRESO = 100;
    private ImageView imageFoodBowl;
    private boolean ajusteRealizado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_screen);

        imageFoodBowl = findViewById(R.id.food_bowl);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!ajusteRealizado) {
            int progreso = 10;

            if (progreso >= 0 && progreso <= MAX_PROGRESO) {
                int alturaVisible = (imageFoodBowl.getHeight() * progreso) / MAX_PROGRESO;
                imageFoodBowl.setClipBounds(new Rect(0, imageFoodBowl.getHeight() - alturaVisible,
                        imageFoodBowl.getWidth(), imageFoodBowl.getHeight()));
            } else {
                imageFoodBowl.setVisibility(View.INVISIBLE);
            }

            ajusteRealizado = true;
        }
    }
}