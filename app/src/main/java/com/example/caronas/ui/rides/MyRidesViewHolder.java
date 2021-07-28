package com.example.caronas.ui.rides;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.Offer;
import com.example.caronas.models.Ride;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

public class MyRidesViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewCardTitle;
    private final TextView textViewMyRideFrom;
    private final TextView textViewMyRideTo;
    private final TextView textViewMyRideDate;
    private final TextView textViewMyRideTime1;
    private final TextView textViewMyRideTime2;
    private final TextView textViewMyRideVacancies;
    private final TextView textViewMyRideVacanciesLabel;
    private final Button buttonMyRideAdd;
    private final Button buttonMyRideRemove;
    private final Button buttonMyRideCancel;

    public MyRidesViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        textViewCardTitle = itemView.findViewById(R.id.textViewCardTitle);
        textViewMyRideFrom = itemView.findViewById(R.id.textViewMyRideFrom);
        textViewMyRideTo = itemView.findViewById(R.id.textViewMyRideTo);
        textViewMyRideDate = itemView.findViewById(R.id.textViewMyRideDate);
        textViewMyRideTime1 = itemView.findViewById(R.id.textViewMyRideTime1);
        textViewMyRideTime2 = itemView.findViewById(R.id.textViewMyRideTime2);
        textViewMyRideVacancies = itemView.findViewById(R.id.textViewMyRideVacancies);
        textViewMyRideVacanciesLabel = itemView.findViewById(R.id.textViewMyRideVacanciesLabel);
        buttonMyRideAdd = itemView.findViewById(R.id.buttonMyRideAdd);
        buttonMyRideRemove = itemView.findViewById(R.id.buttonMyRideRemove);
        buttonMyRideCancel = itemView.findViewById(R.id.buttonMyRideCancel);
    }

    @SuppressLint("SetTextI18n")
    public void setMyRide(Ride ride) {
        String addressFrom = String.format("%s, %s, %s", ride.getFrom_city(), ride.getFrom_neighborhood(), ride.getFrom_street());
        String addressTo = String.format("%s, %s, %s", ride.getTo_city(), ride.getTo_neighborhood(), ride.getTo_street());

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(ride.getStart_date());

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String time1 = timeFormat.format(ride.getStart_date());
        String time2 = timeFormat.format(ride.getEnd_date());

        textViewMyRideFrom.setText(addressFrom);
        textViewMyRideTo.setText(addressTo);
        textViewMyRideDate.setText(date);
        textViewMyRideTime1.setText(time1);
        textViewMyRideTime2.setText(time2);

        buttonMyRideCancel.setOnClickListener(v -> {

        });

        if (ride.getClass().equals(Offer.class)) {
            textViewCardTitle.setText("Oferta");
            textViewMyRideVacancies.setText(((Offer) ride).getAvailable_vacancies().toString());
            buttonMyRideAdd.setOnClickListener(v -> {

            });
            buttonMyRideRemove.setOnClickListener(v -> {

            });
        } else {
            textViewCardTitle.setText("Pedido");
            textViewMyRideVacanciesLabel.setText("");
            buttonMyRideAdd.setVisibility(View.GONE);
            buttonMyRideRemove.setVisibility(View.GONE);
        }

    }
}
