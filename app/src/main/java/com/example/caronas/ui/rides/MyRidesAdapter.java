package com.example.caronas.ui.rides;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.HomeActivity;
import com.example.caronas.R;
import com.example.caronas.Service;
import com.example.caronas.models.Offer;
import com.example.caronas.models.Ride;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyRidesAdapter extends RecyclerView.Adapter<MyRidesViewHolder> {

    private final Service service;
    private final List<Ride> myRides;

    public MyRidesAdapter(List<Ride> myRides, Context context) {
        HomeActivity homeActivity = (HomeActivity) context;
        assert homeActivity != null;

        service = homeActivity.service;
        this.myRides = myRides;
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
        return new MyRidesViewHolder(view, service);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyRidesViewHolder holder, int position) {
        holder.setMyRide(myRides.get(position));
        holder.buttonMyRideCancel.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Deletar carona");
            builder.setMessage("Você tem certeza?");
            builder.setPositiveButton("SIM", (dialog, which) -> {

                if (myRides.get(position).getClass().equals(Offer.class)) {
                    service.cancelOffer(myRides.get(position).getId());
                } else {
                    service.cancelRequest(myRides.get(position).getId());
                }
                myRides.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
                dialog.dismiss();

            });
            builder.setNegativeButton("NÃO", (dialog, which) -> dialog.dismiss());
            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    @Override
    public int getItemCount() {
        return myRides.size();
    }
}
