package com.example.caronas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.caronas.models.User;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private Service service;
    private List<User> users;
    private ImageButton imageButton;
    private static final int CAMERA_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        service = Service.getInstance();
        users = service.users;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(v -> askCameraPermission());
    }

    public void askCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
        } else {
            openCamera();
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            imageButton.setImageBitmap(image);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST) {
            if (grantResults.length < 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Permissão da câmera não concedida", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void handleRegister(View view) {
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextEmailRegister = findViewById(R.id.editTextEmailRegister);
        EditText editTextPasswordRegister = findViewById(R.id.editTextPasswordRegister);

        if (TextUtils.isEmpty(editTextName.getText())) {
            editTextName.setError("Digite o seu nome!");
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(editTextName);
            return;
        }
        String name = editTextName.getText().toString();

        if (TextUtils.isEmpty(editTextEmailRegister.getText())) {
            editTextEmailRegister.setError("Digite o seu email!");
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(editTextEmailRegister);
            return;
        }
        String email = editTextEmailRegister.getText().toString();

        if (TextUtils.isEmpty(editTextPasswordRegister.getText())) {
            editTextPasswordRegister.setError("Digite a sua senha!");
            YoYo.with(Techniques.Shake)
                    .duration(700)
                    .repeat(1)
                    .playOn(editTextPasswordRegister);
            return;
        }
        String password = editTextPasswordRegister.getText().toString();

        User newUser = new User(name, email, password);

        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                if (user.getEmail().equals(newUser.getEmail())) {
                    Toast.makeText(view.getContext(), "Email já cadastrado, faça o login!", Toast.LENGTH_LONG).show();
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(findViewById(R.id.button2));
                    return;
                }
            }
        }
        service.createUser(newUser);

        Toast.makeText(view.getContext(), "Cadastro realizado!", Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
