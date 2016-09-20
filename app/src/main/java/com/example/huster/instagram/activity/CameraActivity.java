package com.example.huster.instagram.activity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.huster.instagram.R;
import com.example.huster.instagram.adapter.CameraRecycleViewAdapter;
import com.example.huster.instagram.adapter.HomeAdapter;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.ArrayList;

public class CameraActivity extends Activity {
    ArrayList<String> listImageSrc = new ArrayList<>();
    RecyclerView mRecyclerView;
    CameraRecycleViewAdapter cameraRecycleViewAdapter;
    private Handler mHandler;
    int screenWidth, screenHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        InitView();
        new LoadImgSrc().execute();
    }
    void InitView(){
        mRecyclerView = (RecyclerView)findViewById(R.id.camera_list);
    }
    class LoadImgSrc extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params) {
            // 扫描外部设备中的照片
            String str[] = { MediaStore.Images.Media._ID,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str,
                    null, null, null);
            while (cursor.moveToNext()) {
//                System.out.println(cursor.getString(0)); // 图片ID
//                System.out.println(cursor.getString(1)); // 图片文件名
//                System.out.println(cursor.getString(2)); // 图片绝对路径
//                System.out.println(new Date(new File(cursor.getString(2)).lastModified())+"");
                listImageSrc.add(cursor.getString(2));
            }
            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 3));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            cameraRecycleViewAdapter = new CameraRecycleViewAdapter(getBaseContext(), listImageSrc, screenWidth, screenHeight);
            mRecyclerView.setAdapter(cameraRecycleViewAdapter);
            cameraRecycleViewAdapter.setOnItemClickListener(new CameraRecycleViewAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, String src) {
                    Uri sourceUri = Uri.parse("http://star.xiziwang.net/uploads/allimg/140512/19_140512150412_1.jpg");
                    //裁剪后保存到文件中
                    Uri destinationUri = Uri.fromFile(new File(getCacheDir(), "SampleCropImage.jpeg"));
                    UCrop.of(sourceUri, destinationUri).withAspectRatio(16, 9).withMaxResultSize(300, 300).start(CameraActivity.this);
                    Toast.makeText(CameraActivity.this, "test", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
