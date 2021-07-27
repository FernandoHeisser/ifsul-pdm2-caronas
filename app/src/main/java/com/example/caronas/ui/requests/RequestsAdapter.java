package com.example.caronas.ui.requests;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.Request;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsViewHolder> {

    private final List<Request> requests;

    public RequestsAdapter(List<Request> requests) {
        this.requests = requests;
    }

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
        holder.setRequest(this.requests.get(position));
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }
}
