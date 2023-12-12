package com.example.smartcage.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcage.Models.ApiResponse;
import com.example.smartcage.Models.User;
import com.example.smartcage.repository.UserRepository;

public class UserViewModel extends ViewModel {
    private final UserRepository repository;

    public UserViewModel() {
        repository = new UserRepository();
    }

    public MutableLiveData<ApiResponse.RegistrationResponse> registerUser(String name, String lastName, String email, String password) {
        User user = new User(name, lastName, email, password);
        return repository.registerUser(user);
    }

    public MutableLiveData<String> loginUser(String email, String password) {
        return repository.loginUser(email, password);
    }
}