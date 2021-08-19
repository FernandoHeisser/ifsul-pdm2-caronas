package com.example.caronas.ui.requests;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.caronas.models.User;

import org.jetbrains.annotations.NotNull;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsViewHolder> {

    private final Service service;
    private final HomeActivity homeActivity;

    public RequestsAdapter(Context context) {
        HomeActivity homeActivity = (HomeActivity) context;
        assert homeActivity != null;
        this.homeActivity = homeActivity;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull @NotNull RequestsViewHolder holder, int position) {
        User currentUser = null;
        for (User user : service.users) {
            if (user.getId().equals(service.othersRideRequests.get(position).getUser_id())) {
                currentUser = user;
            }
        }
        holder.setRequest(service.othersRideRequests.get(position), currentUser);

        holder.buttonRequestCall.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("tel:+55"+service.othersRideRequests.get(position).getPhone()));
            homeActivity.startActivity(i);
        });
        holder.buttonRequestWhatsapp.setOnClickListener(v -> {
            String url = String.format("https://wa.me/55%s?text=Olá, você está precisando de carona?", service.othersRideRequests.get(position).getPhone());
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            homeActivity.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return service.othersRideRequests.size();
    }
}
