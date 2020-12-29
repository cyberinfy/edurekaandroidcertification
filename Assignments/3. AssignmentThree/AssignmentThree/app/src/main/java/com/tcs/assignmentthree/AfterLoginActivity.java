package com.tcs.assignmentthree;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class AfterLoginActivity extends AppCompatActivity implements FirstFragmentToAfterLoginActivity{

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FirstFragment firstFragment;
    SecondFragment secondFragment;
    SecondFragmentViewModel secondFragmentViewModel;
    String param1, param2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        param1 = param2 = "";
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        firstFragment = FirstFragment.newInstance(param1,param2);
        secondFragment = SecondFragment.newInstance(param1,param2);
        fragmentTransaction.add(R.id.frameLayoutFirst,firstFragment);
        fragmentTransaction.add(R.id.frameLayoutSecond,secondFragment);
        fragmentTransaction.commit();
        secondFragmentViewModel = new ViewModelProvider(this).get(SecondFragmentViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        paint(secondFragmentViewModel.getHexcode(),secondFragmentViewModel.getName());
    }

    @Override
    public void paint(int hexcode, String name) {
        Log.d("MainActivity","Paint Invoked");
        secondFragmentViewModel.setName(name);
        secondFragmentViewModel.setHexcode(hexcode);
        secondFragment.paint(hexcode, name);
    }
}
