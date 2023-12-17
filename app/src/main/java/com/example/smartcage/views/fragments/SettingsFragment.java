package com.example.smartcage.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.smartcage.R;
import com.example.smartcage.SharedPreferencesManager;

public class SettingsFragment extends Fragment {
    private EditText name;
    private EditText lastname;
    private EditText email;
    private SharedPreferencesManager sharedPreferencesManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Obtener referencias a los EditText
        name = view.findViewById(R.id.nameEdit);
        lastname = view.findViewById(R.id.lastnameEdit);
        email = view.findViewById(R.id.emailEdit);

        // Deshabilitar la edición del nombre
        name.setEnabled(false);

        // Inicializar el SharedPreferencesManager
        sharedPreferencesManager = new SharedPreferencesManager(requireContext());

        // Obtener y establecer el valor del correo electrónico desde SharedPreferences
        String emailValue = sharedPreferencesManager.getEmail();
        email.setText(emailValue);

        return view;
    }
}