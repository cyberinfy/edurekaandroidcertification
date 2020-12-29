package com.tcs.assignmentfour.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tcs.assignmentfour.R;
import com.tcs.assignmentfour.adapters.PicturesViewAdapter;
import com.tcs.assignmentfour.interfaces.FragmentToMainActivity;

public class NaturePicturesFragment extends Fragment {

    static View rootView;
    FragmentToMainActivity fragmentToMainActivity;
    private RecyclerView recyclerView;

    public NaturePicturesFragment() {
    }

    public static NaturePicturesFragment newInstance() {
        NaturePicturesFragment fragment = new NaturePicturesFragment();
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
            Log.d("NaturePicturesFragment", "Activity doesn't implement FragmentToMainActivity");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_nature_pictures, container, false);
        rootView = inflater.inflate(R.layout.fragment_nature_pictures, container, false);
        recyclerView = rootView.findViewById(R.id.nature_pictures_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new PicturesViewAdapter(R.layout.fragment_nature_pictures,fragmentToMainActivity,450,300));
        fragmentToMainActivity.changeTitle(R.drawable.ic_nature_24,getString(R.string.nature_pictures));
        return rootView;
    }
}