package com.example.caronas.ui.offers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.Offer;
import com.example.caronas.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersViewHolder> {

    private final List<Offer> offers;
    private final List<User> users;

    public OffersAdapter(List<Offer> offers, List<User> users) {
        this.offers = offers;
        this.users = users;
    }

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
        User currentUser = null;
        for (User user : users) {
            if (user.getId().equals(offers.get(position).getUser_id())) {
                currentUser = user;
            }
        }
        holder.setOffer(this.offers.get(position), currentUser);
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }
}
