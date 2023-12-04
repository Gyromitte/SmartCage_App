package com.example.smartcage.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.smartcage.R;
import com.example.smartcage.fragments.AboutFragment;
import com.example.smartcage.fragments.HomeFragment;
import com.example.smartcage.fragments.SensorsFragment;
import com.example.smartcage.fragments.SettingsFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int NAV_HOME = R.id.nav_home;
    private static final int NAV_SETTINGS = R.id.nav_settings;
    private static final int NAV_SENSORS = R.id.nav_sensors;
    private static final int NAV_ABOUT = R.id.nav_about;
    private static final int NAV_LOGOUT = R.id.nav_logout;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == NAV_HOME) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, new HomeFragment()).commit();
        } else if (itemId == NAV_SETTINGS) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, new SettingsFragment()).commit();
        } else if (itemId == NAV_SENSORS) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, new SensorsFragment()).commit();
        } else if (itemId == NAV_ABOUT) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containter, new AboutFragment()).commit();
        } else if (itemId == NAV_LOGOUT) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }
}