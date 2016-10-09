package com.example.huster.instagram.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import com.example.huster.instagram.R;
import com.example.huster.instagram.factory.FragmentFactory;
import com.example.huster.instagram.fragment.FragmentDiscover;
import com.example.huster.instagram.fragment.FragmentHome;
import com.example.huster.instagram.fragment.FragmentLove;
import com.example.huster.instagram.fragment.FragmentPersonal;
import com.example.huster.instagram.util.Constant;

public class MainActivity extends FragmentActivity{
    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
        }
    };
    ImageView []imageView = new ImageView[5];
//    private HashMap<String, ImageView> imageViewMap = new HashMap<>();
    int checkId = 0, tag = 0;
    private FragmentManager fragmentManager;
    private Fragment[] fragments = new Fragment[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            tag = savedInstanceState.getInt("tag");
            checkId = tag;
            fragments[0] = getSupportFragmentManager().findFragmentByTag(FragmentHome.class.getName());
            fragments[1] = getSupportFragmentManager().findFragmentByTag(FragmentDiscover.class.getName());
            fragments[3] = getSupportFragmentManager().findFragmentByTag(FragmentLove.class.getName());
            fragments[4] = getSupportFragmentManager().findFragmentByTag(FragmentPersonal.class.getName());
            getSupportFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[3]).hide(fragments[4]).show(fragments[tag]).commit();
        }
        else{
            fragments[0] = FragmentHome.newInstance();
            fragments[1] = FragmentDiscover.newInstance();
            fragments[3] = FragmentLove.newInstance();
            fragments[4] = FragmentPersonal.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragments[0], FragmentHome.class.getName())
                    .add(R.id.content, fragments[1], FragmentDiscover.class.getName())
                    .add(R.id.content, fragments[3], FragmentLove.class.getName())
                    .add(R.id.content, fragments[4], FragmentPersonal.class.getName())
                    .hide(fragments[1])
                    .hide(fragments[3])
                    .hide(fragments[4])
                    .show(fragments[0])
                    .commit();
            checkId = 0;
        }
        InitTabListener();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tag", checkId);
    }

    void InitTabListener(){
        imageView[0] = (ImageView)findViewById(R.id.imageView1);
        imageView[1] = (ImageView)findViewById(R.id.imageView2);
        imageView[2] = (ImageView)findViewById(R.id.imageView3);
        imageView[3] = (ImageView)findViewById(R.id.imageView4);
        imageView[4] = (ImageView)findViewById(R.id.imageView5);
        fragmentManager = getSupportFragmentManager();
        imageView[checkId].setImageResource(R.drawable.refresh_pressed);
        imageView[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkId==0) return;
                imageView[checkId].setImageResource(R.drawable.refresh);
                imageView[0].setImageResource(R.drawable.refresh_pressed);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if(fragments[checkId]!=null) transaction.hide(fragments[checkId]);
                transaction.show(fragments[0]);
                transaction.commit();
                checkId = 0;
            }
        });
        imageView[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkId==1) return;
                imageView[checkId].setImageResource(R.drawable.refresh);
                imageView[1].setImageResource(R.drawable.refresh_pressed);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if(fragments[checkId]!=null) transaction.hide(fragments[checkId]);
                transaction.show(fragments[1]);
                transaction.commit();
                checkId = 1;
            }
        });
        imageView[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                Intent intent = new Intent(getBaseContext(), CameraActivity.class);
                startActivity(intent);
            }
        });
        imageView[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkId == 3) return;
                imageView[checkId].setImageResource(R.drawable.refresh);
                imageView[3].setImageResource(R.drawable.refresh_pressed);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if (fragments[checkId] != null) transaction.hide(fragments[checkId]);
                transaction.show(fragments[3]);
                transaction.commit();
                checkId = 3;
            }
        });
        imageView[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkId == 4) return;
                imageView[checkId].setImageResource(R.drawable.refresh);
                imageView[4].setImageResource(R.drawable.refresh_pressed);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if (fragments[checkId] != null) transaction.hide(fragments[checkId]);
                transaction.show(fragments[4]);
                transaction.commit();
                checkId = 4;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
