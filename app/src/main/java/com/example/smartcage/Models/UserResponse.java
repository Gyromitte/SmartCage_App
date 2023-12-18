package com.example.smartcage.Models;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("user")
    private User user;

    @SerializedName("message")
    private String message;

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}
