package com.example.caronas;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.caronas.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    public Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        Long userId = pref.getLong("user_id", -1);

        service = new Service();
        service.executeGetOthersOffers(userId);
        service.executeGetOthersRequests(userId);
        service.executeGetMyOffers(userId);
        service.executeGetMyRequests(userId);
        service.executeGetUsers();

        super.onCreate(savedInstanceState);

        com.example.caronas.databinding.ActivityHomeBinding binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_rides, R.id.navigation_offers, R.id.navigation_requests)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}