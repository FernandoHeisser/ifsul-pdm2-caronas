package com.example.caronas.ui.offers;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.Offer;
import com.example.caronas.models.User;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

public class OffersViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewOfferFrom;
    private final TextView textViewOfferTo;
    private final TextView textViewOfferDate;
    private final TextView textViewOfferTime1;
    private final TextView textViewOfferTime2;
    private final TextView textViewOfferUser;
    private final TextView textViewOfferVacancies;
    private final Button buttonOfferFav;
    private final Button buttonOfferCall;
    private final Button buttonOfferWhatsapp;

    public OffersViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        textViewOfferFrom = itemView.findViewById(R.id.textViewOfferFrom);
        textViewOfferTo = itemView.findViewById(R.id.textViewOfferTo);
        textViewOfferDate = itemView.findViewById(R.id.textViewOfferDate);
        textViewOfferTime1 = itemView.findViewById(R.id.textViewOfferTime1);
        textViewOfferTime2 = itemView.findViewById(R.id.textViewOfferTime2);
        textViewOfferUser = itemView.findViewById(R.id.textViewOfferUser);
        textViewOfferVacancies = itemView.findViewById(R.id.textViewOfferVacancies);
        buttonOfferFav = itemView.findViewById(R.id.buttonOfferFav);
        buttonOfferCall = itemView.findViewById(R.id.buttonOfferCall);
        buttonOfferWhatsapp = itemView.findViewById(R.id.buttonOfferWhatsapp);
    }

    @SuppressLint("SetTextI18n")
    public void setOffer(Offer offer, User user) {

        String addressFrom = String.format("%s, %s, %s", offer.getFrom_city(), offer.getFrom_neighborhood(), offer.getFrom_street());
        String addressTo = String.format("%s, %s, %s", offer.getTo_city(), offer.getTo_neighborhood(), offer.getTo_street());

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(offer.getStart_date());

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String time1 = timeFormat.format(offer.getStart_date());
        String time2 = timeFormat.format(offer.getEnd_date());

        textViewOfferFrom.setText(addressFrom);
        textViewOfferTo.setText(addressTo);
        textViewOfferDate.setText(date);
        textViewOfferTime1.setText(time1);
        textViewOfferTime2.setText(time2);
        textViewOfferVacancies.setText(offer.getAvailable_vacancies().toString());

        if (user != null) {
            textViewOfferUser.setText(user.getName());
        }

        buttonOfferFav.setOnClickListener(v -> {

        });
        buttonOfferCall.setOnClickListener(v -> {

        });
        buttonOfferWhatsapp.setOnClickListener(v -> {

        });
    }
}
