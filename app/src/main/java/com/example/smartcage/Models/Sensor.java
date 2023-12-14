package com.example.smartcage.Models;

public class Sensor {
    private Integer id;
    private String nombre;
    private String tipo;
    private Boolean estado;
    private int iconResId; // Resource id of the icon
    private Float value;

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getIconResId() {
        return iconResId;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    // Constructor
    public Sensor(String nombre, int iconResId) {
        this.nombre = nombre;
        this.iconResId = iconResId;
    }
}
