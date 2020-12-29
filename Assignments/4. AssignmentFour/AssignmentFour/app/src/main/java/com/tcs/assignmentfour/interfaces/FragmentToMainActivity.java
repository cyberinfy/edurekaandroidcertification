package com.tcs.assignmentfour.interfaces;

import com.tcs.assignmentfour.datamodel.PictureDataModel;

import java.util.List;

public interface FragmentToMainActivity {
    public void changeTitle(int imgId, String title);
    public void showMoreDetails(int id,String pictureUrl);
    public List<PictureDataModel> getPictures();
    public void showFlowerPictures();
    public void showNaturePictures();
}
