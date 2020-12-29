package com.tcs.assignmentfour.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tcs.assignmentfour.R;
import com.tcs.assignmentfour.adapters.PicturesViewAdapter;
import com.tcs.assignmentfour.interfaces.FragmentToMainActivity;

public class FlowerPicturesFragment extends Fragment {
    static View rootView;
    FragmentToMainActivity fragmentToMainActivity;
    private RecyclerView recyclerView;

    public FlowerPicturesFragment() {
        // Required empty public constructor
    }

    public static FlowerPicturesFragment newInstance() {
        FlowerPicturesFragment fragment = new FlowerPicturesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentToMainActivity = (FragmentToMainActivity) context;
        } catch (ClassCastException castException) {
            Log.d("FlowerPicturesFragment", "Activity doesn't implement FragmentToMainActivity");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_flower_pictures, container, false);
        recyclerView = rootView.findViewById(R.id.flower_pictures_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(new PicturesViewAdapter(R.layout.fragment_flower_pictures,fragmentToMainActivity,350,200));
        fragmentToMainActivity.changeTitle(R.drawable.ic_flower_vintage_24,getString(R.string.flower_pictures));
        return  rootView;
    }

}