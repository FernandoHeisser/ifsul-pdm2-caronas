package com.example.caronas.ui.rides;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.Ride;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyRidesAdapter extends RecyclerView.Adapter<MyRidesViewHolder> {

    private final List<Ride> myRides;

    public MyRidesAdapter(List<Ride> myRides) {
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
        return new MyRidesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyRidesViewHolder holder, int position) {
        holder.setMyRide(this.myRides.get(position));
    }

    @Override
    public int getItemCount() {
        return myRides.size();
    }
}
