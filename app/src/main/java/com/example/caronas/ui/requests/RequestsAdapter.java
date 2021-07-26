package com.example.caronas.ui.requests;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;

import org.jetbrains.annotations.NotNull;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsViewHolder> {

    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_requests;
    }

    @NonNull
    @NotNull
    @Override
    public RequestsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RequestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RequestsViewHolder holder, int position) {
        holder.setText("Test requests");
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
