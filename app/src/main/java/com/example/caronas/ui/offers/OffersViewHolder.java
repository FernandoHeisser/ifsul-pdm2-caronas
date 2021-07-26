package com.example.caronas.ui.offers;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;

import org.jetbrains.annotations.NotNull;

public class OffersViewHolder extends RecyclerView.ViewHolder {

    private final TextView textView;

    public OffersViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textValueOffers);
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
