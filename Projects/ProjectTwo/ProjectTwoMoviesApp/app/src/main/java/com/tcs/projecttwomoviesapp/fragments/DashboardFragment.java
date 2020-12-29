package com.tcs.projecttwomoviesapp.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tcs.projecttwomoviesapp.R;
import com.tcs.projecttwomoviesapp.adapters.DashboardViewAdapter;

public class DashboardFragment extends Fragment{
    View rootView;
    private RecyclerView recyclerView;

    public DashboardFragment() {
    }

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = rootView.findViewById(R.id.dashboard_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        }
        recyclerView.setAdapter(new DashboardViewAdapter(SplashFragment.movies.getResults(),getContext()));
        return  rootView;
    }
}