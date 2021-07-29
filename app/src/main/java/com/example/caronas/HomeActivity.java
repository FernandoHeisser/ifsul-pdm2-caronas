package com.example.caronas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.caronas.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private Service service;

    public Service getService() {
        return service;
    }

    public void execute() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        Long userId = pref.getLong("user_id", -1);

        Thread thread = new Thread(() -> {
            try {
                service.executeGetOthersOffers(userId);
                service.executeGetOthersRequests(userId);
                service.executeGetMyOffers(userId);
                service.executeGetMyRequests(userId);
                service.executeGetUsers();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this.getApplicationContext(), "Erro de conexão, tente novamente", Toast.LENGTH_LONG).show();
            }
        });
        thread.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        service = new Service();
        execute();
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