package com.example.caronas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.caronas.models.User;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private final Service service = new Service();
    private List<User> users = new ArrayList<>();
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        service.executeGetUsers();
        users = service.getUsers();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        imageButton = findViewById(R.id.imageButton);
    }

    public void takeProfileImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Selecione a imagem"), REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            imageButton.setImageURI(selectedImageUri);
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

        Thread thread = new Thread(() -> {
            try {
                service.createUser(newUser);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(view.getContext(), "Erro no cadastro, tente novamente", Toast.LENGTH_LONG).show();
            }
        });
        thread.start();

        Toast.makeText(view.getContext(), "Cadastro realizado!", Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
