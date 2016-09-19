package com.example.huster.instagram.activity;

import android.app.Activity;
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
import com.example.huster.instagram.util.Constant;

public class MainActivity extends FragmentActivity{
    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
        }
    };
    ImageView []imageView = new ImageView[5];
//    private HashMap<String, ImageView> imageViewMap = new HashMap<>();
    int checkId = 0;
    private FragmentManager fragmentManager;
    private Fragment[] fragments = new Fragment[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitTabListener();
    }
    void InitTabListener(){
        imageView[0] = (ImageView)findViewById(R.id.imageView1);
        imageView[1] = (ImageView)findViewById(R.id.imageView2);
        imageView[2] = (ImageView)findViewById(R.id.imageView3);
        imageView[3] = (ImageView)findViewById(R.id.imageView4);
        imageView[4] = (ImageView)findViewById(R.id.imageView5);
        for(int i = 0; i<5; i++) fragments[i] = null;
        fragmentManager = getSupportFragmentManager();
        imageView[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkId==0) return;
                imageView[checkId].setImageResource(R.drawable.refresh);
                imageView[0].setImageResource(R.drawable.refresh_pressed);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if(fragments[0]==null){
                    fragments[0] = FragmentFactory.getInstanceByID(Constant.FragmentHomeID);
                    transaction.add(R.id.content, fragments[0]);
                }
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
                if(fragments[1]==null){
                    fragments[1] = FragmentFactory.getInstanceByID(Constant.FragmentSearchID);
                    transaction.add(R.id.content, fragments[1]);
                }
                if(fragments[checkId]!=null) transaction.hide(fragments[checkId]);
                transaction.show(fragments[1]);
                transaction.commit();
                checkId = 1;
            }
        });
        imageView[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkId==2) return;
                imageView[checkId].setImageResource(R.drawable.refresh);
                imageView[2].setImageResource(R.drawable.refresh_pressed);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if(fragments[2]==null){
                    fragments[2] = FragmentFactory.getInstanceByID(Constant.FragmentCameraID);
                    transaction.add(R.id.content, fragments[2]);
                }
                if(fragments[checkId]!=null) transaction.hide(fragments[checkId]);
                transaction.show(fragments[2]);
                transaction.commit();
                checkId = 2;
            }
        });
        imageView[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkId==3) return;
                imageView[checkId].setImageResource(R.drawable.refresh);
                imageView[3].setImageResource(R.drawable.refresh_pressed);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if(fragments[3]==null){
                    fragments[3] = FragmentFactory.getInstanceByID(Constant.FragmentLoveID);
                    transaction.add(R.id.content, fragments[3]);
                }
                if(fragments[checkId]!=null) transaction.hide(fragments[checkId]);
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
                if (fragments[4] == null) {
                    fragments[4] = FragmentFactory.getInstanceByID(Constant.FragmentPersonalID);
                    transaction.add(R.id.content, fragments[4]);
                }
                if (fragments[checkId] != null) transaction.hide(fragments[checkId]);
                transaction.show(fragments[4]);
                transaction.commit();
                checkId = 4;
            }
        });
        imageView[0].setImageResource(R.drawable.refresh_pressed);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(fragments[0]==null){
            fragments[0] = FragmentFactory.getInstanceByID(Constant.FragmentHomeID);
            transaction.add(R.id.content, fragments[0]);
        }
        transaction.show(fragments[0]);
        transaction.commit();
        checkId = 0;
    }
}
