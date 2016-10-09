package com.example.huster.instagram.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.huster.instagram.R;
import com.example.huster.instagram.adapter.CameraRecycleViewAdapter;
import com.example.huster.instagram.adapter.HomeAdapter;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class CameraActivity extends Activity {
    ArrayList<String> listImageSrc = new ArrayList<>();
    RecyclerView mRecyclerView;
    CameraRecycleViewAdapter cameraRecycleViewAdapter;
    CardView cardView;
    private Handler mHandler;
    public static int screenWidth, screenHeight;
    final int MAX_IMG_NUMS = 20;
    boolean isloading = false;
    int imgIdLast = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        InitView();
        new LoadImgSrc().execute();
//        new LoadMoreImgAndShow().execute();
    }
    void InitView(){
        cardView = (CardView)findViewById(R.id.card_view);

        mRecyclerView = (RecyclerView)findViewById(R.id.camera_list);
    }
//    class LoadMoreImgAndShow extends AsyncTask{
//        @Override
//        protected Object doInBackground(Object[] params) {
//            //一次30张
//            if(imgIdLast>0){
//                int i;
//                for(i = imgIdLast; i>imgIdLast-MAX_IMG_NUMS && i>0; i--){
//                    listShowImg.add(listImageSrc.get(i));
//                }
//                imgIdLast = i;
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Object o) {
//            super.onPostExecute(o);
//            cameraRecycleViewAdapter.notifyDataSetChanged();
//            isloading = false;
//        }
//    }

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
                listImageSrc.add(0, cursor.getString(2));
            }
//            imgIdLast = listImageSrc.size()-1;
//       //     初始化listShowImg
//            for(int i = listImageSrc.size()-1; i>=0; i--){
//                listShowImg.add("");
//            }
////            初步显示
//            for(int i = 0; i<listImageSrc.size() && i<MAX_IMG_NUMS; i++){
//                listShowImg.set(i, listImageSrc.get(i));
//            }
            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 4));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            cameraRecycleViewAdapter = new CameraRecycleViewAdapter(getBaseContext(), listImageSrc, screenWidth, screenHeight);
            mRecyclerView.setAdapter(cameraRecycleViewAdapter);
            cameraRecycleViewAdapter.setOnItemClickListener(new CameraRecycleViewAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, String src) {
//                    Intent intent = new Intent(getBaseContext(), ImageFilterActivity.class);
//                    intent.putExtra("file", src);
//                    startActivity(intent);
                    /*暂且调用系统的剪切图片*/
                    Intent intent = new Intent();
                    intent.setAction("com.android.camera.action.CROP");
                    Uri uri = Uri.fromFile(new File(src));
                    intent.setDataAndType(uri, "image/*");
                    /*圆形，但是在三星上行不通啊！*/
//                    intent.putExtra("outputX", 300);  //裁剪图片的宽
//                    intent.putExtra("outputY", 300);
                    intent.putExtra("aspectX", 1);  //裁剪方框宽的比例
                    intent.putExtra("aspectY", 1);
                    /**/
                    intent.putExtra("scale", true);  //是否保持比例
                    startActivityForResult(intent, 1);
                }
            });
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    //如果到达顶部或者底部，这个函数不会再调用,并且次函数比下面的先调用
                }

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
//                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                        int first = layoutManager.findFirstVisibleItemPosition();
//                        int last = layoutManager.findLastVisibleItemPosition();
//                        for (int i = first; i < listImageSrc.size() && i <= last; i++) {
//                            CameraRecycleViewAdapter.ViewHolder viewHolder = (CameraRecycleViewAdapter.ViewHolder) mRecyclerView.findViewHolderForAdapterPosition(i);
//                            Picasso.with(getBaseContext()).load(new File(viewHolder.string)).resize(screenWidth / 4, screenHeight / 5).centerCrop().into(viewHolder.imageView);
//                        }
//                    }
                }
            });
        }
    }

}
