package com.example.caronas.ui.requests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caronas.R;
import com.example.caronas.models.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        List<Request> requests = new ArrayList<>();
        requests.add(new Request());
        requests.add(new Request());
        requests.add(new Request());

        View view = inflater.inflate(R.layout.fragment_requests, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_requests);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RequestsAdapter(requests));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}