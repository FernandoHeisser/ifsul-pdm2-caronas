package com.example.caronas.ui.offers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;

import org.jetbrains.annotations.NotNull;

public class OffersAdapter extends RecyclerView.Adapter<OffersViewHolder> {

    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_offers;
    }

    @NonNull
    @NotNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new OffersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OffersViewHolder holder, int position) {
        holder.setText("Test offers");
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
