package com.example.caronas.ui.rides;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.Ride;

import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class MyRidesViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewMyRideFrom;
    private final TextView textViewMyRideTo;
    private final TextView textViewMyRideDate;
    private final TextView textViewMyRideTime1;
    private final TextView textViewMyRideTime2;
    protected final TextView textViewCardTitle;
    protected final TextView textViewMyRideVacanciesLabel;
    protected final TextView textViewMyRideVacancies;
    protected final Button buttonMyRideAdd;
    protected final Button buttonMyRideRemove;
    protected final Button buttonMyRideCancel;

    protected Long vacancies;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    public void setMyRide(Ride ride) {

        if (Objects.nonNull(ride.getFrom_city()) && Objects.nonNull(ride.getFrom_neighborhood()) && Objects.nonNull(ride.getFrom_street())) {
            String addressFrom = String.format("%s, %s, %s", ride.getFrom_city(), ride.getFrom_neighborhood(), ride.getFrom_street());
            textViewMyRideFrom.setText(addressFrom);
        }
        if (Objects.nonNull(ride.getTo_city()) && Objects.nonNull(ride.getTo_neighborhood()) && Objects.nonNull(ride.getTo_street())) {
            String addressTo = String.format("%s, %s, %s", ride.getTo_city(), ride.getTo_neighborhood(), ride.getTo_street());
            textViewMyRideTo.setText(addressTo);
        }

        if (Objects.nonNull(ride.getStart_date())) {
            OffsetDateTime startDate = OffsetDateTime.parse(ride.getStart_date(), DateTimeFormatter.ISO_DATE_TIME);
            String date = startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String time1 = startDate.format(DateTimeFormatter.ofPattern("HH:mm"));
            textViewMyRideDate.setText(date);
            textViewMyRideTime1.setText(time1);
        }
        if (Objects.nonNull(ride.getEnd_date())) {
            OffsetDateTime endDate = OffsetDateTime.parse(ride.getEnd_date(), DateTimeFormatter.ISO_DATE_TIME);
            String time2 = endDate.format(DateTimeFormatter.ofPattern("HH:mm"));
            textViewMyRideTime2.setText(time2);
        }

    }
}
