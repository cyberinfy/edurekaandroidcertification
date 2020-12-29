package com.tcs.assignmentfour.viewmodel;

import androidx.lifecycle.ViewModel;

import com.tcs.assignmentfour.datamodel.PictureDataModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    List<PictureDataModel> pictures;

    public List<PictureDataModel> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureDataModel> pictures) {
        this.pictures = pictures;
    }
}
