package com.example.smartcage.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.smartcage.ApiService;
import com.example.smartcage.Models.User;
import com.example.smartcage.Models.UserResponse;
import com.example.smartcage.R;
import com.example.smartcage.SharedPreferencesManager;
import com.example.smartcage.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment {
    private TextView name;
    private TextView lastname;
    private TextView email;
    private SharedPreferencesManager sharedPreferencesManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        name = view.findViewById(R.id.nameEdit);
        lastname = view.findViewById(R.id.lastnameEdit);
        email = view.findViewById(R.id.emailEdit);

        Context context = view.getContext();
        sharedPreferencesManager = new SharedPreferencesManager(context);

        String token = sharedPreferencesManager.getToken();
        ApiService apiService = ApiClient.getApiService();
        Call<UserResponse> call = apiService.getUserInfo("Bearer " + token);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.isSuccessful()) {
                    UserResponse userInfoResponse = response.body();
                    if (userInfoResponse != null && userInfoResponse.getUser() != null) {
                        User user = userInfoResponse.getUser();
                        name.setText(user.getName());
                        lastname.setText(user.getLastname());
                        email.setText(user.getEmail());
                    }
                } else {
                    Log.i("menso Geibriel", "a");
                    int statusCode = response.code();
                    Log.i("Código de estado HTTP", String.valueOf(statusCode));

                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // Manejar el fallo en la llamada, si es necesario
            }
        });



        return view;
    }
}