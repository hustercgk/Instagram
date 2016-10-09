package com.example.huster.instagram.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.huster.instagram.R;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageFilterActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_filter);
        imageView = (ImageView)findViewById(R.id.image_imagefilter_show);
        String fileName = getIntent().getStringExtra("file");
        Picasso.with(getBaseContext()).load(new File(fileName)).into(imageView);
    }
}
