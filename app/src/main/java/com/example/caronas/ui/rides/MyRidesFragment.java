package com.example.caronas.ui.rides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.HomeActivity;
import com.example.caronas.R;
import com.example.caronas.models.Offer;
import com.example.caronas.models.Ride;
import com.example.caronas.models.RideRequest;

import java.util.ArrayList;
import java.util.List;

public class MyRidesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeActivity homeActivity = (HomeActivity) getActivity();
        assert homeActivity != null;
        List<Offer> myOffers = homeActivity.getService().getMyOffers();
        List<RideRequest> myRideRequests = homeActivity.getService().getMyRequests();

        List<Ride> myRides = new ArrayList<>();

        myRides.addAll(myOffers);
        myRides.addAll(myRideRequests);

        View view = inflater.inflate(R.layout.fragment_rides, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_rides);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new MyRidesAdapter(myRides));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}