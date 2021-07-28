package com.example.caronas.ui.requests;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.RideRequest;
import com.example.caronas.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsViewHolder> {

    private final List<RideRequest> rideRequests;
    private final List<User> users;

    public RequestsAdapter(List<RideRequest> rideRequests, List<User> users) {
        this.rideRequests = rideRequests;
        this.users = users;
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
        for (User user : users) {
            if (user.getId().equals(rideRequests.get(position).getUser_id())) {
                currentUser = user;
            }
        }
        holder.setRequest(this.rideRequests.get(position), currentUser);
    }

    @Override
    public int getItemCount() {
        return rideRequests.size();
    }
}
