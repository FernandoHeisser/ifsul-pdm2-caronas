package com.example.caronas.ui.requests;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.Request;

import org.jetbrains.annotations.NotNull;

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

    public void setRequest(Request request) {
        //String addressFrom = String.format("%s, %s, %s", request.getFrom_city(), request.getFrom_neighborhood(), request.getFrom_street());
        //String addressTo = String.format("%s, %s, %s", request.getTo_city(), request.getTo_neighborhood(), request.getTo_street());

        //String date = request.getStart_date().toString();

        textViewRequestFrom.setText("Charqueadas, Centro, Rua Patricio Ferreira");
        textViewRequestTo.setText("São Jerônimo, Acacia, Rua das Flores");
        textViewRequestDate.setText("23/08/2021");
        textViewRequestTime1.setText("19:20");
        textViewRequestTime2.setText("19:30");
        textViewRequestUser.setText("Fernando Heisser");
        buttonRequestFav.setOnClickListener(v -> {

        });
        buttonRequestCall.setOnClickListener(v -> {

        });
        buttonRequestWhatsapp.setOnClickListener(v -> {

        });
    }
}
