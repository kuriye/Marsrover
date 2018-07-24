package com.mustart.marspictures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Bou's Laptop on 30/05/2018.
 */

public class DetailActivity extends AppCompatActivity {


    public static final String PHOTO_ARG = "PHOTO_ARG";

    private Picture picture;

    private ImageView image;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        image = findViewById(R.id.background);
        title = findViewById(R.id.camera);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        picture = (Picture) getIntent().getSerializableExtra(PHOTO_ARG);

        Log.d("DETAIL_ACTIVITY", "opening a picture ") ;

//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setTitle("Photo ID: " + picture.getId());
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        setTitle(picture.getId());

        title.setText(picture.getName());
        Picasso.get().load(picture.getImageUrl()).into(image);
    }
}
