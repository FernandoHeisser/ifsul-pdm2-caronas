package com.example.caronas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.caronas.models.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        service = Service.getInstance();
        service.executeGetUsers();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        service.executeGetUsers();
        super.onResume();
    }

    public boolean isAirplaneModeOn() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(this.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(this.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    public boolean isNetworkConnectionAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean mobileConnected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED;
        boolean wifiConnected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        return mobileConnected || wifiConnected;
    }

    public void handleLogin(View view) {
        if (isAirplaneModeOn()) {
            Toast.makeText(view.getContext(), "Modo avi??o ligado, n??o conectado a internet", Toast.LENGTH_LONG).show();
            return;
        }
        if (!isNetworkConnectionAvailable()) {
            Toast.makeText(view.getContext(), "N??o conectado a internet", Toast.LENGTH_LONG).show();
            return;
        }

        boolean userExists = false;

        User currentUser = null;

        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        List<User> users = service.users;

        if (TextUtils.isEmpty(editTextEmail.getText())) {
            editTextEmail.setError("Digite o seu email!");
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(editTextEmail);
            return;
        }
        String email = editTextEmail.getText().toString();

        if (TextUtils.isEmpty(editTextPassword.getText())) {
            editTextPassword.setError("Digite a sua senha!");
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(editTextPassword);
            return;
        }
        String password = editTextPassword.getText().toString();

        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    userExists = true;
                    currentUser = user;
                    break;
                }
            }
        } else {
            Toast.makeText(view.getContext(), "Problema de conex??o, tente mais tarde", Toast.LENGTH_LONG).show();
            return;
        }

        if (userExists) {
            if (currentUser.getEmail().equals(email) && currentUser.getPassword().equals(password)) {

                SharedPreferences.Editor editor = getSharedPreferences("pref", MODE_PRIVATE).edit();
                editor.putLong("user_id", currentUser.getId());
                editor.apply();

                Intent myIntent = new Intent(view.getContext(), HomeActivity.class);
                startActivity(myIntent);
            } else {
                Toast.makeText(view.getContext(), "Senha incorreta!", Toast.LENGTH_LONG).show();
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .repeat(1)
                        .playOn(editTextPassword);
            }
        } else {
            Toast.makeText(view.getContext(), "Email n??o cadastrado, cadastre-se!", Toast.LENGTH_LONG).show();
            YoYo.with(Techniques.Bounce)
                    .duration(700)
                    .repeat(1)
                    .playOn(findViewById(R.id.textViewRegister));
        }
    }

    public void openRegister(View view) {
        if (isAirplaneModeOn()) {
            Toast.makeText(view.getContext(), "Modo avi??o ligado, n??o conectado a internet", Toast.LENGTH_LONG).show();
            return;
        }
        if (!isNetworkConnectionAvailable()) {
            Toast.makeText(view.getContext(), "N??o conectado a internet", Toast.LENGTH_LONG).show();
            return;
        }

        YoYo.with(Techniques.Flash)
                .duration(700)
                .repeat(1)
                .playOn(findViewById(R.id.textViewRegister));

        Intent myIntent = new Intent(view.getContext(), RegisterActivity.class);
        startActivity(myIntent);
    }
}