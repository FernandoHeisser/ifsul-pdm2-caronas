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
    public List<Offer> myOffers = new ArrayList<>();
    public List<RideRequest> myRideRequests = new ArrayList<>();

    private static final String localhost = "https://ifsul-pdm2-caronas.herokuapp.com/api";

    public void createUser(User user) {
        Thread thread = new Thread(() -> {
            try {
                String postBody = new Gson().toJson(user);

                Request request = new Request.Builder()
                        .url(String.format("%s/user", localhost))
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

    public String createOffer(Offer offer) throws IOException {
        String postBody = new Gson().toJson(offer);

        Request request = new Request.Builder()
                .url(String.format("%s/carpool/offer", localhost))
                .post(RequestBody.create(postBody, MediaType.parse("application/json; charset=utf-8")))
                .build();

        Response response = client.newCall(request).execute();

        return Objects.requireNonNull(response.body()).string();
    }

    public String createRideRequest(RideRequest rideRequest) throws IOException {
        String postBody = new Gson().toJson(rideRequest);

        Request request = new Request.Builder()
                .url(String.format("%s/carpool/request", localhost))
                .post(RequestBody.create(postBody, MediaType.parse("application/json; charset=utf-8")))
                .build();

        Response response = client.newCall(request).execute();

        return Objects.requireNonNull(response.body()).string();
    }

    public void addVacancy(Long offerId) {
        Thread thread = new Thread(() -> {
            try {
                String postBody = "";

                Request request = new Request.Builder()
                        .url(String.format(Locale.getDefault(), "%s/vacancy/add/%d", localhost, offerId))
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
                        .url(String.format(Locale.getDefault(), "%s/vacancy/remove/%d", localhost, offerId))
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
                        .url(String.format(Locale.getDefault(), "%s/cancel/offer/%d", localhost, offerId))
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
                        .url(String.format(Locale.getDefault(), "%s/cancel/request/%d", localhost, requestId))
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
                .url(String.format("%s/users", localhost))
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
                .url(String.format(Locale.getDefault(), "%s/carpool/offers/others/%d", localhost, userId))
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
                .url(String.format(Locale.getDefault(), "%s/carpool/requests/others/%d", localhost, userId))
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
                .url(String.format(Locale.getDefault(), "%s/carpool/offers/user/%d", localhost, userId))
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
                .url(String.format(Locale.getDefault(), "%s/carpool/requests/user/%d", localhost, userId))
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
