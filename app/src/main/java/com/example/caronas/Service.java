package com.example.caronas;

import com.example.caronas.models.Offer;
import com.example.caronas.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Service {

    private final OkHttpClient client = new OkHttpClient();

    private List<User> users = new ArrayList<>();
    private List<Offer> othersOffers = new ArrayList<>();
    private List<Request> othersRequests = new ArrayList<>();
    private List<Offer> myOffers = new ArrayList<>();
    private List<Request> myRequests = new ArrayList<>();

    private static final String localhost = "192.168.1.7";

    public List<User> getUsers() {
        return users;
    }

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

    public void createUser(User user) throws Exception {
        String postBody = new Gson().toJson(user);

        Request request = new Request.Builder()
                .url(String.format("http://%s/api/user", localhost))
                .post(RequestBody.create(postBody, MediaType.parse("application/json; charset=utf-8")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(Objects.requireNonNull(response.body()).string());
        }
    }

    public void executeGetUsers() {
        Request request = new Request.Builder()
                .url(String.format("http://%s:3333/api/users", localhost))
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

    public void executeGetOthersOffers(Long userId) {
        Request request = new Request.Builder()
                .url(String.format(Locale.getDefault(), "http://%s:3333/api/carpool/offers/others/%d", localhost, userId))
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

                    Type listType = new TypeToken<List<Offer>>() {
                    }.getType();
                    othersOffers = new Gson().fromJson(Objects.requireNonNull(responseBody).string(), listType);
                }
            }
        });
    }

    public void executeGetOthersRequests(Long userId) {
        Request request = new Request.Builder()
                .url(String.format(Locale.getDefault(), "http://%s:3333/api/carpool/requests/others/%d", localhost, userId))
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

                    Type listType = new TypeToken<List<Request>>() {
                    }.getType();
                    othersRequests = new Gson().fromJson(Objects.requireNonNull(responseBody).string(), listType);
                }
            }
        });
    }

    public void executeGetMyOffers(Long userId) {
        Request request = new Request.Builder()
                .url(String.format(Locale.getDefault(), "http://%s:3333/api/carpool/offers/user/%d", localhost, userId))
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

                    Type listType = new TypeToken<List<Offer>>() {
                    }.getType();
                    myOffers = new Gson().fromJson(Objects.requireNonNull(responseBody).string(), listType);
                }
            }
        });
    }

    public void executeGetMyRequests(Long userId) {
        Request request = new Request.Builder()
                .url(String.format(Locale.getDefault(), "http://%s:3333/api/carpool/requests/user/%d", localhost, userId))
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

                    Type listType = new TypeToken<List<Request>>() {
                    }.getType();
                    myRequests = new Gson().fromJson(Objects.requireNonNull(responseBody).string(), listType);
                }
            }
        });
    }

}
