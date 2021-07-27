package com.example.caronas.ui.offers;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.Offer;

import org.jetbrains.annotations.NotNull;

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

    public void setOffer(Offer offer) {

        //String addressFrom = String.format("%s, %s, %s", offer.getFrom_city(), offer.getFrom_neighborhood(), offer.getFrom_street());
        //String addressTo = String.format("%s, %s, %s", offer.getTo_city(), offer.getTo_neighborhood(), offer.getTo_street());

        //String date = offer.getStart_date().toString();

        textViewOfferFrom.setText("Charqueadas, Centro, Rua Patricio Ferreira");
        textViewOfferTo.setText("São Jerônimo, Acacia, Rua das Flores");
        textViewOfferDate.setText("23/08/2021");
        textViewOfferTime1.setText("19:20");
        textViewOfferTime2.setText("19:30");
        textViewOfferUser.setText("Fernando Heisser");
        textViewOfferVacancies.setText("3");
        buttonOfferFav.setOnClickListener(v -> {

        });
        buttonOfferCall.setOnClickListener(v -> {

        });
        buttonOfferWhatsapp.setOnClickListener(v -> {

        });
    }
}
