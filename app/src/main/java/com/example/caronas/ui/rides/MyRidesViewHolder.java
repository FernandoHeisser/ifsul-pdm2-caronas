package com.example.caronas.ui.rides;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;

import org.jetbrains.annotations.NotNull;

public class MyRidesViewHolder extends RecyclerView.ViewHolder {

    private final TextView textView;

    public MyRidesViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textValueMyRides);
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
