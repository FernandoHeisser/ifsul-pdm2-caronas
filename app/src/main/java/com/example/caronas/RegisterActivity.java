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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RegisterActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();
    private List<User> users = new ArrayList<>();
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getUsers();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        imageButton = findViewById(R.id.imageButton);
    }

    public void createUser(User user) throws Exception {
        String postBody = new Gson().toJson(user);

        Request request = new Request.Builder()
                .url("http://192.168.1.3:3333/api/user")
                .post(RequestBody.create(postBody, MediaType.parse("application/json; charset=utf-8")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(Objects.requireNonNull(response.body()).string());
        }
    }

    public void getUsers() {
        Request request = new Request.Builder()
                .url("http://192.168.1.3:3333/api/users")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Type listType = new TypeToken<List<User>>() {
                    }.getType();
                    users = new Gson().fromJson(Objects.requireNonNull(responseBody).string(), listType);
                }
            }
        });
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

    public void handleRegister(View view) throws Exception {
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
                createUser(newUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
