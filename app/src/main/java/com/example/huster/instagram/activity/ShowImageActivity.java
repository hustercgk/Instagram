package com.example.huster.instagram.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.huster.instagram.R;
import com.example.huster.instagram.adapter.CameraRecycleViewAdapter;
import com.example.huster.instagram.util.CameraUtil;
import com.example.huster.instagram.util.Constant;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.util.ArrayList;

public class ShowImageActivity extends Activity {
    ArrayList<String> listImageSrc = new ArrayList<>();
    RecyclerView mRecyclerView;
    CameraRecycleViewAdapter cameraRecycleViewAdapter;
    CardView cardView;
    ImageView imageView;
    public static int screenWidth, screenHeight, actionbarHeight;
    boolean isShowSeekBar = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showimage);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
//        System.out.println("husterkang "+screenHeight+" "+screenWidth);
        InitView();
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.head);
        actionbarHeight = relativeLayout.getLayoutParams().height;
//        System.out.println("husterkang "+relativeLayout.getLayoutParams().height);
        new LoadImgSrc().execute();
//        new LoadMoreImgAndShow().execute();
    }

    void InitView() {
//        cardView = (CardView) findViewById(R.id.card_view);
        imageView = (ImageView)findViewById(R.id.imageview_activity_showimage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                // 启动activity并获取返回数据
//                setResult(RESULT_OK, intent);
//                String img_path = getCacheDir().getAbsolutePath() + System.currentTimeMillis() + ".jpeg";
//                Uri imageUri =Uri.fromFile(ne File(img_path));
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                intent.setData(imageUri);
//                startActivityForResult(intent, Constant.REQUEST_CODE.CAMERA);
//                CameraUtil.getInstance().camera(ShowImageActivity.this);
                Intent intent = new Intent(getBaseContext(), CameraActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE.CAMERA);
//                startActivity(intent);
//                CameraUtil.getInstance().camera(ShowImageActivity.this);
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.camera_list);
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

    class LoadImgSrc extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            // 扫描外部设备中的照片
            System.out.println("husterkang AsyncTask");
            String str[] = {MediaStore.Images.Media._ID,
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
            if(listImageSrc.size()==0)
                listImageSrc.add(0, "");
//            imgIdLast = listImageSrc.size()-1;
//       //     初始化listShowImg
//            for(int i = listImageSrc.size()-1; i>=0; i--){
//                listShowImg.add("");//                listShowImg.set(i, listImageSrc.get(i));

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
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getBaseContext(), 3);
            mRecyclerView.setLayoutManager(gridLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//            mRecyclerView.addItemDecoration(new ShareClass.SpaceItemDecoration());
            cameraRecycleViewAdapter = new CameraRecycleViewAdapter(getBaseContext(), listImageSrc, screenWidth, screenHeight);
            System.out.println("husterkang "+cameraRecycleViewAdapter);
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
//                    intent.putExtra("aspectX", 1);  //裁剪方框宽的比例
//                    intent.putExtra("aspectY", 1);
//                    /**/
//                    intent.putExtra("scale", true);  //是否保持比例
//                    startActivityForResult(intent, 1);
                    startUCrop(ShowImageActivity.this, src, UCrop.REQUEST_CROP, 12, 10);
                }
            });
        }
    }

    public void startUCrop(Activity activity, String sourceFilePath,
                           int requestCode, float aspectRatioX, float aspectRatioY) {
        Uri sourceUri = Uri.fromFile(new File(sourceFilePath));
        String fileName = SystemClock.uptimeMillis() + ".jpg";
        UCrop uCrop = UCrop.of(sourceUri, Uri.fromFile(new File(getCacheDir(), fileName)));
        UCrop.Options options = new UCrop.Options();
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.SCALE, UCropActivity.SCALE);
        options.setHideBottomControls(true);
        options.setToolbarColor(Color.WHITE);
        options.setFreeStyleCropEnabled(true);
        options.setToolbarTitle("test");
//        options.setLogoColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
        options.setShowCropGrid(false);
        options.setToolbarWidgetColor(Color.BLACK);
        options.setCompressionQuality(70);
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        uCrop.withOptions(options);
        uCrop.withAspectRatio(aspectRatioX, aspectRatioY);
        uCrop.start(activity, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            Uri resultUri = UCrop.getOutput(data);
            if (resultUri == null) {
                Toast.makeText(getBaseContext(), "resultUri is null", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(getBaseContext(), ImageFilterActivity.class);
            intent.setData(resultUri);
            startActivity(intent);
            Toast.makeText(getBaseContext(), "OK", Toast.LENGTH_SHORT).show();
        }
        else if(resultCode == RESULT_OK && requestCode == Constant.REQUEST_CODE.CAMERA){
//            System.out.println("pkusz "+"onActivityResult");
            Toast.makeText(getBaseContext(), "onActivityResult "+data.getData().toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getBaseContext(), ImageFilterActivity.class);
            intent.setData(data.getData());
            startActivity(intent);
        }
        else {
            Toast.makeText(getBaseContext(), "Not OK", Toast.LENGTH_SHORT).show();
        }
    }


}