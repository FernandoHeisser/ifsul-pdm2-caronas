package com.example.caronas.ui.rides;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.ui.creation.CreationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyRidesFragment extends Fragment {

    private MyRidesAdapter myRidesAdapter;

    @SuppressLint("NotifyDataSetChanged")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rides, container, false);

        FloatingActionButton fab = view.findViewById(R.id.floating_action_button);
        fab.setOnClickListener(v -> {
            Intent myIntent = new Intent(view.getContext(), CreationActivity.class);
            startActivity(myIntent);
        });

        myRidesAdapter = new MyRidesAdapter(getContext());

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_rides);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(myRidesAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        myRidesAdapter.notifyDataSetChanged();
        super.onResume();
    }
}