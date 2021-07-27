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
import com.example.caronas.models.Offer;
import com.example.caronas.models.User;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class HomeActivity extends AppCompatActivity {

    private final Service service = new Service();

    private List<Offer> othersOffers = new ArrayList<>();
    private List<Request> othersRequests = new ArrayList<>();
    private List<Offer> myOffers = new ArrayList<>();
    private List<Request> myRequests = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public List<Offer> getOthersOffers() {
        return othersOffers;
    }

    public List<Request> getOthersRequests() {
        return othersRequests;
    }

    public List<Offer> getMyOffers() {
        return myOffers;
    }

    public List<Request> getMyRequests() {
        return myRequests;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        Long userId = pref.getLong("user_id", -1);

        Thread thread = new Thread(() -> {
            try {
                service.executeGetOthersOffers(userId);
                service.executeGetOthersRequests(userId);
                service.executeGetMyOffers(userId);
                service.executeGetMyRequests(userId);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this.getApplicationContext(), "Erro de conexão, tente novamente", Toast.LENGTH_LONG).show();
            }
        });
        thread.start();

        othersOffers = service.getOthersOffers();
        othersRequests = service.getOthersRequests();
        myOffers = service.getMyOffers();
        myRequests = service.getMyRequests();
        users = service.getUsers();

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