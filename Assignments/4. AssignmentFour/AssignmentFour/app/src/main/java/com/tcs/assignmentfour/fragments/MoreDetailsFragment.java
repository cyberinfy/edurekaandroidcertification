package com.tcs.assignmentfour.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tcs.assignmentfour.R;
import com.tcs.assignmentfour.interfaces.FragmentToMainActivity;

public class MoreDetailsFragment extends Fragment {
    private String pictureURL;
    View rootView;
    ImageView imageViewPicture;
    TextView textViewPictureName, textViewPictureUrl;
    FragmentToMainActivity fragmentToMainActivity;

    public static MoreDetailsFragment newInstance(String pictureURL) {
        MoreDetailsFragment fragment = new MoreDetailsFragment(pictureURL);
        return fragment;
    }

    public MoreDetailsFragment(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_more_details, container, false);
        imageViewPicture = rootView.findViewById(R.id.image_view_picture);
        textViewPictureUrl = rootView.findViewById(R.id.text_view_picture_url);
        textViewPictureName = rootView.findViewById(R.id.text_view_picture_name);
        textViewPictureName.setText(pictureURL.substring(pictureURL.indexOf("data//")+"data//".length()));
        textViewPictureUrl.setText(pictureURL);
        Picasso.get().load(pictureURL).into(imageViewPicture);
        Log.d("details",pictureURL);
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        return rootView;
    }
}