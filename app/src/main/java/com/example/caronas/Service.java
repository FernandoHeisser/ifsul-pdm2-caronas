package com.example.caronas;

import android.app.Application;

import com.example.caronas.models.Offer;
import com.example.caronas.models.Ride;
import com.example.caronas.models.RideRequest;
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

public class Service extends Application {

    private static Service instance;

    public static Service getInstance() {
        if (instance == null) {
            synchronized (Service.class) {
                if (instance == null)
                    instance = new Service();
            }
        }
        return instance;
    }

    private Service() {

    }

    private final OkHttpClient client = new OkHttpClient();

    public List<User> users = new ArrayList<>();
    public List<Offer> othersOffers = new ArrayList<>();
    public List<RideRequest> othersRideRequests = new ArrayList<>();
    public List<Ride> myRides = new ArrayList<>();

    private List<Offer> myOffers = new ArrayList<>();
    private List<RideRequest> myRideRequests = new ArrayList<>();

    private static final String localhost = "192.168.1.7:3333";

    public void createUser(User user) {
        Thread thread = new Thread(() -> {
            try {
                String postBody = new Gson().toJson(user);

                Request request = new Request.Builder()
                        .url(String.format("http://%s/api/user", localhost))
                        .post(RequestBody.create(postBody, MediaType.parse("application/json; charset=utf-8")))
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    System.out.println(Objects.requireNonNull(response.body()).string());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void createOffer(Offer offer) {
        Thread thread = new Thread(() -> {
            try {
                String postBody = new Gson().toJson(offer);

                Request request = new Request.Builder()
                        .url(String.format("http://%s/api/carpool/offer", localhost))
                        .post(RequestBody.create(postBody, MediaType.parse("application/json; charset=utf-8")))
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    System.out.println(Objects.requireNonNull(response.body()).string());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void createRideRequest(RideRequest rideRequest) {
        Thread thread = new Thread(() -> {
            try {
                String postBody = new Gson().toJson(rideRequest);

                Request request = new Request.Builder()
                        .url(String.format("http://%s/api/carpool/request", localhost))
                        .post(RequestBody.create(postBody, MediaType.parse("application/json; charset=utf-8")))
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    System.out.println(Objects.requireNonNull(response.body()).string());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void addVacancy(Long offerId) {
        Thread thread = new Thread(() -> {
            try {
                String postBody = "";

                Request request = new Request.Builder()
                        .url(String.format(Locale.getDefault(), "http://%s/api/vacancy/add/%d", localhost, offerId))
                        .put(RequestBody.create(postBody, MediaType.parse("application/json; charset=utf-8")))
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    System.out.println(Objects.requireNonNull(response.body()).string());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void removeVacancy(Long offerId) {
        Thread thread = new Thread(() -> {
            try {
                String postBody = "";

                Request request = new Request.Builder()
                        .url(String.format(Locale.getDefault(), "http://%s/api/vacancy/remove/%d", localhost, offerId))
                        .put(RequestBody.create(postBody, MediaType.parse("application/json; charset=utf-8")))
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    System.out.println(Objects.requireNonNull(response.body()).string());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void cancelOffer(Long offerId) {
        Thread thread = new Thread(() -> {
            try {
                String postBody = "";

                Request request = new Request.Builder()
                        .url(String.format(Locale.getDefault(), "http://%s/api/cancel/offer/%d", localhost, offerId))
                        .put(RequestBody.create(postBody, MediaType.parse("application/json; charset=utf-8")))
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    System.out.println(Objects.requireNonNull(response.body()).string());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void cancelRequest(Long requestId) {
        Thread thread = new Thread(() -> {
            try {
                String postBody = "";

                Request request = new Request.Builder()
                        .url(String.format(Locale.getDefault(), "http://%s/api/cancel/request/%d", localhost, requestId))
                        .put(RequestBody.create(postBody, MediaType.parse("application/json; charset=utf-8")))
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    System.out.println(Objects.requireNonNull(response.body()).string());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void executeGetUsers() {
        Request request = new Request.Builder()
                .url(String.format("http://%s/api/users", localhost))
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
                .url(String.format(Locale.getDefault(), "http://%s/api/carpool/offers/others/%d", localhost, userId))
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
                .url(String.format(Locale.getDefault(), "http://%s/api/carpool/requests/others/%d", localhost, userId))
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

                    Type listType = new TypeToken<List<RideRequest>>() {
                    }.getType();
                    othersRideRequests = new Gson().fromJson(Objects.requireNonNull(responseBody).string(), listType);
                }
            }
        });
    }

    public void executeGetMyOffers(Long userId) {
        Request request = new Request.Builder()
                .url(String.format(Locale.getDefault(), "http://%s/api/carpool/offers/user/%d", localhost, userId))
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
                    myRides.addAll(myOffers);
                }
            }
        });
    }

    public void executeGetMyRequests(Long userId) {
        Request request = new Request.Builder()
                .url(String.format(Locale.getDefault(), "http://%s/api/carpool/requests/user/%d", localhost, userId))
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

                    Type listType = new TypeToken<List<RideRequest>>() {
                    }.getType();
                    myRideRequests = new Gson().fromJson(Objects.requireNonNull(responseBody).string(), listType);
                    myRides.addAll(myRideRequests);
                }
            }
        });
    }

}
