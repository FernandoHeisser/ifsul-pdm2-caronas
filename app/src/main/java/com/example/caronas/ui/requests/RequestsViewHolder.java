package com.example.caronas.ui.requests;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;

import org.jetbrains.annotations.NotNull;

public class RequestsViewHolder extends RecyclerView.ViewHolder {

    private final TextView textView;

    public RequestsViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textValueRequest);
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
