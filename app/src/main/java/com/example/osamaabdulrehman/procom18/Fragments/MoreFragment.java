package com.example.osamaabdulrehman.procom18.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.osamaabdulrehman.procom18.Adapters.MoreRVAdapter;
import com.example.osamaabdulrehman.procom18.Models.Option;
import com.example.osamaabdulrehman.procom18.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {


    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_more, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.moreRecyclerView);

        Option option1 = new Option("Contact Us", R.drawable.contact);
        Option option2 = new Option("Our Team", R.drawable.team);

        List<Option> options = new ArrayList<>();
        options.add(option1);
        options.add(option2);

        MoreRVAdapter moreAdapter = new MoreRVAdapter(getContext(), options);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(moreAdapter);

        return rootView;
    }

}
