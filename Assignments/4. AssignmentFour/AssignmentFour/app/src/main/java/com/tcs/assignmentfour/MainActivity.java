package com.tcs.assignmentfour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.tcs.assignmentfour.datamodel.PictureDataModel;
import com.tcs.assignmentfour.fragments.FlowerPicturesFragment;
import com.tcs.assignmentfour.fragments.MoreDetailsFragment;
import com.tcs.assignmentfour.fragments.NaturePicturesFragment;
import com.tcs.assignmentfour.interfaces.FragmentToMainActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentToMainActivity{
    List<PictureDataModel> pictures;
    DrawerLayout drawerLayoutRoot;
    ImageView imageViewMenu, imageViewIcon;
    TextView textViewTitle;
    LinearLayout linearLayoutHostFragmentContainer;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FlowerPicturesFragment flowerPicturesFragment;
    NaturePicturesFragment naturePicturesFragment;
    MoreDetailsFragment moreDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayoutRoot = (DrawerLayout) findViewById(R.id.drawer_layout_root);
        imageViewMenu = (ImageView)findViewById(R.id.image_view_menu);
        imageViewIcon = (ImageView) findViewById(R.id.image_view_icon);
        textViewTitle = (TextView) findViewById(R.id.text_view_title);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        linearLayoutHostFragmentContainer = (LinearLayout) findViewById(R.id.host_fragment_container);
        fragmentManager = getSupportFragmentManager();
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayoutRoot.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_flower_pictures:
                        showFlowerPictures();
                        return true;
                    case R.id.menu_nature_pictures:
                        showNaturePictures();
                        return true;
                }
                return true;
            }
        });
        pictures = SplashScreenActivity.pictures;
        showFlowerPictures();
        Log.d("MainActivity", String.valueOf(pictures.size()));
    }

    @Override
    public void showNaturePictures() {
        Toast.makeText(getApplicationContext(),"Loading Nature Pictures",Toast.LENGTH_LONG).show();
        linearLayoutHostFragmentContainer.removeAllViewsInLayout();
        fragmentTransaction = fragmentManager.beginTransaction();
        naturePicturesFragment = NaturePicturesFragment.newInstance();
        fragmentTransaction.replace(R.id.host_fragment_container, naturePicturesFragment)
                .addToBackStack(null)
                .commit();
        drawerLayoutRoot.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showFlowerPictures() {
        Toast.makeText(getApplicationContext(),"Loading Flower Pictures",Toast.LENGTH_LONG).show();
        linearLayoutHostFragmentContainer.removeAllViewsInLayout();
        fragmentTransaction = fragmentManager.beginTransaction();
        flowerPicturesFragment = FlowerPicturesFragment.newInstance();
        fragmentTransaction.replace(R.id.host_fragment_container, flowerPicturesFragment)
                .commit();
        drawerLayoutRoot.closeDrawer(GravityCompat.START);
    }

    @Override
    public void changeTitle(int imgId, String title) {
        imageViewIcon.setImageResource(imgId);
        textViewTitle.setText(title);
    }

    @Override
    public void showMoreDetails(int id,String pictureURL) {
        linearLayoutHostFragmentContainer.removeAllViewsInLayout();
        fragmentTransaction = fragmentManager.beginTransaction();
        moreDetailsFragment = MoreDetailsFragment.newInstance(pictureURL);
        fragmentTransaction.replace(R.id.host_fragment_container, moreDetailsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public List<PictureDataModel> getPictures() {
        return pictures;
    }

}