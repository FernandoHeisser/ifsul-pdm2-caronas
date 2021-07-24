package com.example.caronas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openRegister(View view) {
        YoYo.with(Techniques.Flash)
                .duration(700)
                .repeat(1)
                .playOn(findViewById(R.id.textViewRegister));

        Intent myIntent = new Intent(view.getContext(), RegisterActivity.class);
        startActivity(myIntent);
    }
}