package com.tcs.assignmentthree;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    View rootView;
    LinearLayout colorsListView;
    RecyclerView colorsList;
    FirstFragmentToAfterLoginActivity listener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        rootView = inflater.inflate(R.layout.fragment_first, container, false);
        // Inflate the layout for this fragment
        colorsListView = (LinearLayout) rootView.findViewById(R.id.fragmentFirst);
        colorsList = new RecyclerView(getContext());
        colorsList.setLayoutManager(new LinearLayoutManager(getContext()));
        ColorDataModel[] colors = new ColorDataModel[5];
        colors[0] = new ColorDataModel();
        colors[0].setName(getString(R.string.black));
        colors[0].setHexcode(R.color.black);
        colors[1] = new ColorDataModel();
        colors[1].setName(getString(R.string.red));
        colors[1].setHexcode(R.color.red);
        colors[2] = new ColorDataModel();
        colors[2].setName(getString(R.string.green));
        colors[2].setHexcode(R.color.green);
        colors[3] = new ColorDataModel();
        colors[3].setName(getString(R.string.blue));
        colors[3].setHexcode(R.color.blue);
        colors[4] = new ColorDataModel();
        colors[4].setName(getString(R.string.grey));
        colors[4].setHexcode(R.color.grey);
        colorsList.setAdapter(new FirstFragmentAdapter(colors, listener));
        colorsListView.addView(colorsList);
        return rootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (FirstFragmentToAfterLoginActivity) context;
        } catch (ClassCastException castException) {
            Log.d("FirstFragment", "Activity doesn't implement FirstFragmentToMainActivity");
        }
    }
}