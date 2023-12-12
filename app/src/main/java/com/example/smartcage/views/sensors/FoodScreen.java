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

        Button addFood = findViewById(R.id.add_water);

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create dialog
                Dialog dialog = new Dialog(FoodScreen.this);
                // Assign rounded corners
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_corners);
                // Assign design
                dialog.setContentView(R.layout.food_popup);

                // Find the Spinner inside the Dialog
                Spinner spinnerDropdown = dialog.findViewById(R.id.spinner_dropdown);

                // Define an array of elements for the dropdown
                //TODO: Hacer que el array sea dinamico y muestre solo porcentajes mayores al actual
                String[] opciones = {"20%", "30%", "40%", "Llenar todo"};

                // Create an ArrayAdapter using the array and a predefined layout for the items
                ArrayAdapter<String> adapter = new ArrayAdapter<>(FoodScreen.this, android.R.layout.simple_spinner_dropdown_item, opciones);

                // Specify the layout to use when displaying the dropdown list of choices
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Assign the adapter to the Spinner
                spinnerDropdown.setAdapter(adapter);

                // Show pop-up
                dialog.show();

            }
        });
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