package com.example.caronas.ui.requests;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.HomeActivity;
import com.example.caronas.R;
import com.example.caronas.Service;
import com.example.caronas.models.User;

import org.jetbrains.annotations.NotNull;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsViewHolder> {

    private final Service service;

    public RequestsAdapter(Context context) {
        HomeActivity homeActivity = (HomeActivity) context;
        assert homeActivity != null;

        service = homeActivity.service;
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
        User currentUser = null;
        for (User user : service.users) {
            if (user.getId().equals(service.othersRideRequests.get(position).getUser_id())) {
                currentUser = user;
            }
        }
        holder.setRequest(service.othersRideRequests.get(position), currentUser);
    }

    @Override
    public int getItemCount() {
        return service.othersRideRequests.size();
    }
}
