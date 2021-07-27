package com.example.caronas.ui.rides;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.Ride;

import org.jetbrains.annotations.NotNull;

public class MyRidesViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewMyRideFrom;
    private final TextView textViewMyRideTo;
    private final TextView textViewMyRideDate;
    private final TextView textViewMyRideTime1;
    private final TextView textViewMyRideTime2;
    private final Button buttonMyRideAdd;
    private final Button buttonMyRideRemove;

    public MyRidesViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        textViewMyRideFrom = itemView.findViewById(R.id.textViewMyRideFrom);
        textViewMyRideTo = itemView.findViewById(R.id.textViewMyRideTo);
        textViewMyRideDate = itemView.findViewById(R.id.textViewMyRideDate);
        textViewMyRideTime1 = itemView.findViewById(R.id.textViewMyRideTime1);
        textViewMyRideTime2 = itemView.findViewById(R.id.textViewMyRideTime2);
        buttonMyRideAdd = itemView.findViewById(R.id.buttonMyRideAdd);
        buttonMyRideRemove = itemView.findViewById(R.id.buttonMyRideRemove);
    }

    public void setMyRide(Ride ride) {
        //String addressFrom = String.format("%s, %s, %s", ride.getFrom_city(), ride.getFrom_neighborhood(), ride.getFrom_street());
        //String addressTo = String.format("%s, %s, %s", ride.getTo_city(), ride.getTo_neighborhood(), ride.getTo_street());

        //String date = ride.getStart_date().toString();

        textViewMyRideFrom.setText("Charqueadas, Centro, Rua Patricio Ferreira");
        textViewMyRideTo.setText("São Jerônimo, Acacia, Rua das Flores");
        textViewMyRideDate.setText("23/08/2021");
        textViewMyRideTime1.setText("19:20");
        textViewMyRideTime2.setText("19:30");
        buttonMyRideAdd.setOnClickListener(v -> {

        });
        buttonMyRideRemove.setOnClickListener(v -> {

        });
    }
}
