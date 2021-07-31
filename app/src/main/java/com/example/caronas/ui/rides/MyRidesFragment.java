package com.example.caronas.ui.rides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyRidesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rides, container, false);

        FloatingActionButton fab = view.findViewById(R.id.floating_action_button);
        fab.setOnClickListener(v -> {
            Toast.makeText(view.getContext(), "Funcionou", Toast.LENGTH_LONG).show();
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_rides);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new MyRidesAdapter(getContext()));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}