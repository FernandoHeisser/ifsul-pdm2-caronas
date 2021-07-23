package com.example.caronas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmailRegister, editTextPasswordRegister, editTextPhone;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(view -> {
            YoYo.with(Techniques.Flash)
                    .duration(700)
                    .repeat(1)
                    .playOn(findViewById(R.id.button2));

            //TODO add register code

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
