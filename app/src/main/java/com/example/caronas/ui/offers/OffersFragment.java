package com.example.caronas.ui.offers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.Offer;

import java.util.ArrayList;
import java.util.List;

public class OffersFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        List<Offer> offers = new ArrayList<>();
        offers.add(new Offer());
        offers.add(new Offer());
        offers.add(new Offer());

        View view = inflater.inflate(R.layout.fragment_offers, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_offers);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new OffersAdapter(offers));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}