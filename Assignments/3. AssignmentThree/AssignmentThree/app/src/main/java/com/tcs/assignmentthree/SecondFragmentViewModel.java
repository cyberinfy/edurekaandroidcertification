package com.tcs.assignmentthree;

import androidx.lifecycle.ViewModel;

public class SecondFragmentViewModel extends ViewModel {
    String name = "Click a color";
    int hexcode = R.color.white;

    public SecondFragmentViewModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHexcode() {
        return hexcode;
    }

    public void setHexcode(int hexcode) {
        this.hexcode = hexcode;
    }
}
