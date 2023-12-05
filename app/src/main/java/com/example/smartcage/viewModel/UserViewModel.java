package com.example.smartcage.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcage.Models.ApiResponse;
import com.example.smartcage.Models.User;
import com.example.smartcage.repository.UserRepository;

public class UserViewModel extends ViewModel {

    private UserRepository userRepository;
    private LiveData<ApiResponse> registrationResponse;

    public UserViewModel() {
        userRepository = new UserRepository();
        registrationResponse = userRepository.getRegistrationResponse();
    }

    public void registerUser(User user) {
        userRepository.registerUser(user);
    }

    public LiveData<ApiResponse> getRegistrationResponse() {
        return registrationResponse;
    }
}
