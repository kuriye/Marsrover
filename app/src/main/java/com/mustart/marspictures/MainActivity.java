package com.mustart.marspictures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NasaApiListener, MarsPicturesAdapter.OnItemClickListener{
    private ArrayList<Picture> pictures;
    private RecyclerView pictureRecyclerView;
    private MarsPicturesAdapter nasaAdapter;
    private GestureDetector gestureDetector;

    public static final String PHOTO_ARG = "PHOTO_ARG";

    private static String PICTURE = "picture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pictures = new ArrayList<>();

        NasaApiManager api = new NasaApiManager(this, this);


        pictureRecyclerView = findViewById(R.id.my_recycler_view);
        pictureRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        nasaAdapter = new MarsPicturesAdapter(pictures);
        pictureRecyclerView.setAdapter(nasaAdapter);

        Spinner spinner = findViewById(R.id.spinner);



        api.getPictures();
        nasaAdapter.setItemClickListener(this);
    }

    @Override
    public void onPictureAvailable(Picture newPicture) {
        Log.d("MAIN_ACTIVITY", "onPictureAvailable: " + pictures.size());
        pictures.add(newPicture);
        nasaAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPictureError(String error) {

    }

    @Override
    public void onItemClick(View v, Picture picture) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra(PHOTO_ARG, picture);

        startActivity(detailIntent);
    }
}
