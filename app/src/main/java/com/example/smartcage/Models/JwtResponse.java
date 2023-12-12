package com.example.smartcage.Models;

import com.google.gson.annotations.SerializedName;

public class JwtResponse {
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }
}
