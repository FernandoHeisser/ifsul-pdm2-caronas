package com.example.caronas.ui.requests;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.RideRequest;
import com.example.caronas.models.User;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

public class RequestsViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewRequestFrom;
    private final TextView textViewRequestTo;
    private final TextView textViewRequestDate;
    private final TextView textViewRequestTime1;
    private final TextView textViewRequestTime2;
    private final TextView textViewRequestUser;
    private final Button buttonRequestFav;
    private final Button buttonRequestCall;
    private final Button buttonRequestWhatsapp;

    public RequestsViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        textViewRequestFrom = itemView.findViewById(R.id.textViewRequestFrom);
        textViewRequestTo = itemView.findViewById(R.id.textViewRequestTo);
        textViewRequestDate = itemView.findViewById(R.id.textViewRequestDate);
        textViewRequestTime1 = itemView.findViewById(R.id.textViewRequestTime1);
        textViewRequestTime2 = itemView.findViewById(R.id.textViewRequestTime2);
        textViewRequestUser = itemView.findViewById(R.id.textViewRequestUser);
        buttonRequestFav = itemView.findViewById(R.id.buttonRequestFav);
        buttonRequestCall = itemView.findViewById(R.id.buttonRequestCall);
        buttonRequestWhatsapp = itemView.findViewById(R.id.buttonRequestWhatsapp);
    }

    @SuppressLint("SetTextI18n")
    public void setRequest(RideRequest rideRequest, User user) {
        String addressFrom = String.format("%s, %s, %s", rideRequest.getFrom_city(), rideRequest.getFrom_neighborhood(), rideRequest.getFrom_street());
        String addressTo = String.format("%s, %s, %s", rideRequest.getTo_city(), rideRequest.getTo_neighborhood(), rideRequest.getTo_street());

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(rideRequest.getStart_date());

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String time1 = timeFormat.format(rideRequest.getStart_date());
        String time2 = timeFormat.format(rideRequest.getEnd_date());

        textViewRequestFrom.setText(addressFrom);
        textViewRequestTo.setText(addressTo);
        textViewRequestDate.setText(date);
        textViewRequestTime1.setText(time1);
        textViewRequestTime2.setText(time2);

        if (user != null) {
            textViewRequestUser.setText(user.getName());
        }

        buttonRequestFav.setOnClickListener(v -> {

        });
        buttonRequestCall.setOnClickListener(v -> {

        });
        buttonRequestWhatsapp.setOnClickListener(v -> {

        });
    }
}
