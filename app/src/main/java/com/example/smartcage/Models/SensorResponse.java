package com.example.smartcage.Models;

import com.example.smartcage.viewModel.AdaFruitModel;
import com.google.gson.annotations.SerializedName;

public class SensorResponse {
    @SerializedName("msg")
    public String msg;

    @SerializedName("data")
    public AdaFruitModel data;
}
