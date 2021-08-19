package com.example.caronas.ui.offers;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.Offer;
import com.example.caronas.models.User;

import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class OffersViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewOfferFrom;
    private final TextView textViewOfferTo;
    private final TextView textViewOfferDate;
    private final TextView textViewOfferTime1;
    private final TextView textViewOfferTime2;
    private final TextView textViewOfferUser;
    private final TextView textViewOfferVacancies;
    protected final Button buttonOfferCall;
    protected final Button buttonOfferWhatsapp;

    public OffersViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        textViewOfferFrom = itemView.findViewById(R.id.textViewOfferFrom);
        textViewOfferTo = itemView.findViewById(R.id.textViewOfferTo);
        textViewOfferDate = itemView.findViewById(R.id.textViewOfferDate);
        textViewOfferTime1 = itemView.findViewById(R.id.textViewOfferTime1);
        textViewOfferTime2 = itemView.findViewById(R.id.textViewOfferTime2);
        textViewOfferUser = itemView.findViewById(R.id.textViewOfferUser);
        textViewOfferVacancies = itemView.findViewById(R.id.textViewOfferVacancies);
        buttonOfferCall = itemView.findViewById(R.id.buttonOfferCall);
        buttonOfferWhatsapp = itemView.findViewById(R.id.buttonOfferWhatsapp);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    public void setOffer(Offer offer, User user) {

        if (Objects.nonNull(offer.getFrom_city()) && Objects.nonNull(offer.getFrom_neighborhood()) && Objects.nonNull(offer.getFrom_street())) {
            String addressFrom = String.format("%s, %s, %s", offer.getFrom_city(), offer.getFrom_neighborhood(), offer.getFrom_street());
            textViewOfferFrom.setText(addressFrom);
        }
        if (Objects.nonNull(offer.getTo_city()) && Objects.nonNull(offer.getTo_neighborhood()) && Objects.nonNull(offer.getTo_street())) {
            String addressTo = String.format("%s, %s, %s", offer.getTo_city(), offer.getTo_neighborhood(), offer.getTo_street());
            textViewOfferTo.setText(addressTo);
        }

        if (Objects.nonNull(offer.getStart_date())) {
            OffsetDateTime startDate = OffsetDateTime.parse(offer.getStart_date(), DateTimeFormatter.ISO_DATE_TIME);
            String date = startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String time1 = startDate.format(DateTimeFormatter.ofPattern("HH:mm"));
            textViewOfferDate.setText(date);
            textViewOfferTime1.setText(time1);
        }
        if (Objects.nonNull(offer.getEnd_date())) {
            OffsetDateTime endDate = OffsetDateTime.parse(offer.getEnd_date(), DateTimeFormatter.ISO_DATE_TIME);
            String time2 = endDate.format(DateTimeFormatter.ofPattern("HH:mm"));
            textViewOfferTime2.setText(time2);
        }
        if (Objects.nonNull(offer.getAvailable_vacancies())) {
            textViewOfferVacancies.setText(offer.getAvailable_vacancies().toString());
        }

        if (user != null) {
            textViewOfferUser.setText(user.getName());
        }
    }
}
