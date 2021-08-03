package com.example.caronas.ui.requests;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.RideRequest;
import com.example.caronas.models.User;

import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    public void setRequest(RideRequest rideRequest, User user) {

        if (Objects.nonNull(rideRequest.getFrom_city()) && Objects.nonNull(rideRequest.getFrom_neighborhood()) && Objects.nonNull(rideRequest.getFrom_street())) {
            String addressFrom = String.format("%s, %s, %s", rideRequest.getFrom_city(), rideRequest.getFrom_neighborhood(), rideRequest.getFrom_street());
            textViewRequestFrom.setText(addressFrom);
        }
        if (Objects.nonNull(rideRequest.getTo_city()) && Objects.nonNull(rideRequest.getTo_neighborhood()) && Objects.nonNull(rideRequest.getTo_street())) {
            String addressTo = String.format("%s, %s, %s", rideRequest.getTo_city(), rideRequest.getTo_neighborhood(), rideRequest.getTo_street());
            textViewRequestTo.setText(addressTo);
        }

        if (Objects.nonNull(rideRequest.getStart_date())) {
            OffsetDateTime startDate = OffsetDateTime.parse(rideRequest.getStart_date(), DateTimeFormatter.ISO_DATE_TIME);
            String date = startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String time1 = startDate.format(DateTimeFormatter.ofPattern("HH:mm"));
            textViewRequestDate.setText(date);
            textViewRequestTime1.setText(time1);
        }
        if (Objects.nonNull(rideRequest.getEnd_date())) {
            OffsetDateTime endDate = OffsetDateTime.parse(rideRequest.getEnd_date(), DateTimeFormatter.ISO_DATE_TIME);
            String time2 = endDate.format(DateTimeFormatter.ofPattern("HH:mm"));
            textViewRequestTime2.setText(time2);
        }

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
