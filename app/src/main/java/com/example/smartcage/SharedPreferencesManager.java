package com.example.smartcage;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String PREFERENCES_NAME = "user";
    private static final String TOKEN_KEY = "token";
    private static final String KEY_EMAIL = "email";

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public SharedPreferencesManager(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public String getEmail() {
        return preferences.getString(KEY_EMAIL, "");
    }

    public void saveEmail(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public void saveToken(String token) {
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public String getToken() {
        return preferences.getString(TOKEN_KEY, null);
    }

    public void clearPreferences() {
        editor.clear();
        editor.apply();
    }
}
