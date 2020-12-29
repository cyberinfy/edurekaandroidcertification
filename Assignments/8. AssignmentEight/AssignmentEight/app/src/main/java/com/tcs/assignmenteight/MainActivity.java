package com.tcs.assignmenteight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.tcs.assignmenteight.adapter.PicturesViewAdapter;
import com.tcs.assignmenteight.interfaces.AdapterToActivity;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements AdapterToActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.gridRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new PicturesViewAdapter(this));
    }

    @Override
    public void showMoreDetails(String path) {
        Intent intent = new Intent(MainActivity.this, MoreDetailActivity.class);
        intent.putExtra(EXTRA_MESSAGE, path);
        startActivity(intent);
    }

}