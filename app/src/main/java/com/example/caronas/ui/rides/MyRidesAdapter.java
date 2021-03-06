package com.example.caronas.ui.rides;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.HomeActivity;
import com.example.caronas.R;
import com.example.caronas.Service;
import com.example.caronas.models.Offer;
import com.example.caronas.models.RideRequest;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MyRidesAdapter extends RecyclerView.Adapter<MyRidesViewHolder> {

    private final Service service;

    public MyRidesAdapter(Context context) {
        HomeActivity homeActivity = (HomeActivity) context;
        assert homeActivity != null;

        service = homeActivity.service;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_rides;
    }

    @NonNull
    @NotNull
    @Override
    public MyRidesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new MyRidesViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(@NonNull @NotNull MyRidesViewHolder holder, int position) {
        if (Objects.nonNull(service.myRides.get(position)) && Objects.nonNull(service.myRides.get(position).getId())) {
            holder.setMyRide(service.myRides.get(position));
            holder.buttonMyRideCancel.setOnClickListener(v -> {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Deletar carona");
                builder.setMessage("Você tem certeza?");
                builder.setPositiveButton("SIM", (dialog, which) -> {

                    if (service.myRides.get(position).getClass().equals(Offer.class)) {
                        service.cancelOffer(service.myRides.get(position).getId());
                        service.myOffers.removeIf(offer -> offer.getId().equals(service.myRides.get(position).getId()));
                    } else {
                        service.cancelRequest(service.myRides.get(position).getId());
                        service.myRideRequests.removeIf(rideRequest -> rideRequest.getId().equals(service.myRides.get(position).getId()));
                    }
                    service.myRides.remove(service.myRides.get(position));
                    notifyDataSetChanged();
                    dialog.dismiss();

                });
                builder.setNegativeButton("NÃO", (dialog, which) -> dialog.dismiss());
                AlertDialog alert = builder.create();
                alert.show();
            });

            if (service.myRides.get(position).getClass().equals(Offer.class)) {
                holder.vacancies = ((Offer) service.myRides.get(position)).getAvailable_vacancies();
                holder.textViewCardTitle.setText("Oferta");
                holder.textViewMyRideVacancies.setText(holder.vacancies.toString());
                holder.buttonMyRideAdd.setOnClickListener(v -> {
                    try {
                        service.addVacancy(service.myRides.get(position).getId());
                        holder.vacancies++;
                        holder.textViewMyRideVacancies.setText(holder.vacancies.toString());
                        ((Offer) service.myRides.get(position)).setAvailable_vacancies(holder.vacancies);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                holder.buttonMyRideRemove.setOnClickListener(v -> {
                    try {
                        service.removeVacancy(service.myRides.get(position).getId());
                        holder.vacancies--;
                        holder.textViewMyRideVacancies.setText(holder.vacancies.toString());
                        ((Offer) service.myRides.get(position)).setAvailable_vacancies(holder.vacancies);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } else if (service.myRides.get(position).getClass().equals(RideRequest.class)) {
                holder.textViewCardTitle.setText("Pedido");
                holder.textViewMyRideVacanciesLabel.setText("");
                holder.buttonMyRideAdd.setVisibility(View.GONE);
                holder.buttonMyRideRemove.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return service.myRides.size();
    }
}
