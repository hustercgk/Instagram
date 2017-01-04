package com.example.huster.instagram.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.huster.instagram.R;
import com.example.huster.instagram.adapter.ImageFilterRecycleViewAdapter;
import com.example.huster.instagram.util.GPUImageFilterTools;
import com.example.huster.instagram.util.ShareClass;
import java.lang.reflect.Method;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

public class ImageFilterActivity extends Activity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private GPUImageFilter mFilter;
    private GPUImageFilterTools.FilterAdjuster mFilterAdjuster;
    private GPUImageView mGPUImageView;
    LinearLayout linearLayout1, linearLayout2, linearLayout3;
    TabLayout tabLayout;
    RecyclerView mRecyclerView;
    SeekBar seekBar;
    ImageFilterRecycleViewAdapter imageFilterRecycleViewAdapter;
    GPUImageFilterTools.FilterList list = null;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_filter);
//        seekBar = ((SeekBar) findViewById(R.id.seekbar_activity_image_filter));
        seekBar = new SeekBar(this);
        seekBar.setOnSeekBarChangeListener(this);
//        findViewById(R.id.button_choose_filter).setOnClickListener(this);
        linearLayout1 = (LinearLayout)findViewById(R.id.linearlayout1_activity_image_filter);
        linearLayout2 = (LinearLayout)findViewById(R.id.linearlayout2_activity_image_filter);
        linearLayout3 = (LinearLayout)findViewById(R.id.linearlayout3_activity_image_filter);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview_image_filter_activity_card);

        int height = (int)((Math.min(getResources().getDisplayMetrics().heightPixels, getDpi())- ShowImageActivity.actionbarHeight)*0.618);
        int height1 =(int)((Math.min(getResources().getDisplayMetrics().heightPixels, getDpi())- ShowImageActivity.actionbarHeight)*0.382*0.618);
        int height2 =(int)((Math.min(getResources().getDisplayMetrics().heightPixels, getDpi())- ShowImageActivity.actionbarHeight)*0.382*0.382);
        ViewGroup.LayoutParams layoutParams = linearLayout1.getLayoutParams();
        layoutParams.height = height;
        linearLayout1.setLayoutParams(layoutParams);
        layoutParams = linearLayout2.getLayoutParams();
        layoutParams.height = height1;
        linearLayout2.setLayoutParams(layoutParams);

        layoutParams = linearLayout3.getLayoutParams();
        layoutParams.height = height2;
        linearLayout3.setLayoutParams(layoutParams);

        layoutParams = new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, FrameLayout.LayoutParams.WRAP_CONTENT);
        seekBar.setLayoutParams(layoutParams);

        tabLayout = (TabLayout)findViewById(R.id.tablayout_activity_image_filter);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.filter).setTag("filter"));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.other).setTag("other"));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getTag().equals("filter")){
                    if(id==0) return;
                    linearLayout2.removeAllViews();
                    linearLayout2.addView(mRecyclerView);
                    id = 0;
                }
                else{
                    if(id==1) return;
                    linearLayout2.removeAllViews();
                    linearLayout2.addView(seekBar);
                    id = 1;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mGPUImageView = (GPUImageView)findViewById(R.id.gpuimage);
//        mGPUImageView.setImage(getIntent().getData());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(getIntent().getData().getPath(), options);
        layoutParams = mGPUImageView.getLayoutParams();
        //缩略图
        final int len = Math.max(options.outHeight, options.outWidth);
        if(len>(height1*0.8)){
            options.inSampleSize =(int) (len/(height1*0.8));
            System.out.println("husterkang "+options.inSampleSize);
        }
        //
        if(options.outWidth>=options.outHeight){
            layoutParams.height = options.outHeight*getResources().getDisplayMetrics().widthPixels/options.outWidth;
            layoutParams.width = getResources().getDisplayMetrics().widthPixels;
            if(layoutParams.height>height){
                layoutParams.height = height;
                layoutParams.width = options.outWidth*height/options.outHeight;
            }
        }
        else{
            layoutParams.width = options.outWidth*height/options.outHeight;
            layoutParams.height = height;
        }
        mGPUImageView.setLayoutParams(layoutParams);
        mGPUImageView.setImage(getIntent().getData());
//        ImageView imageView = new ImageView(this);
//        Picasso.with(getBaseContext())
//                .load(getIntent().getData())
//                .resize((int)(height1*0.8), (int)(height1*0.8))
//                .centerCrop()
//                .into(imageView);
        options.inJustDecodeBounds = false;
        Bitmap bitmap=BitmapFactory.decodeFile(getIntent().getData().getPath(), options);
        list = GPUImageFilterTools.getFilterList(getBaseContext());
        list.setImage(bitmap);
        bitmap.recycle();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        ((DefaultItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);//取消闪烁
        imageFilterRecycleViewAdapter = new ImageFilterRecycleViewAdapter(getBaseContext(), list, height1, getResources().getDisplayMetrics().widthPixels, getIntent().getData());
        mRecyclerView.setAdapter(imageFilterRecycleViewAdapter);
        mRecyclerView.addItemDecoration(new ShareClass.SpaceItemDecoration(5, 5, 5, 5));
        imageFilterRecycleViewAdapter.setOnItemClickListener(new ImageFilterRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position==imageFilterRecycleViewAdapter.id) return;
                list.textColorFlags.set(position, true);
                list.textColorFlags.set(imageFilterRecycleViewAdapter.id, false);
                imageFilterRecycleViewAdapter.notifyItemChanged(position);
                imageFilterRecycleViewAdapter.notifyItemChanged(imageFilterRecycleViewAdapter.id);
                imageFilterRecycleViewAdapter.id = position;
                if(position==0){
                    switchFilterTo(GPUImageFilterTools.createFilterForType(getBaseContext(), list.filters.get(position)));
                    mFilterAdjuster = new GPUImageFilterTools.FilterAdjuster(mFilter);
                    mFilterAdjuster.adjust(50);
                    mGPUImageView.requestRender();
                    return;
                }
                switchFilterTo(GPUImageFilterTools.createFilterForType(getBaseContext(), list.filters.get(position)));
                mGPUImageView.requestRender();
//                mGPUImageView.setFilter(GPUImageFilterTools.createFilterForType(getBaseContext(), list.filters.get(position)));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void switchFilterTo(final GPUImageFilter filter) {
        if (mFilter == null
                || (filter != null && !mFilter.getClass().equals(filter.getClass()))) {
            mFilter = filter;
            mGPUImageView.setFilter(mFilter);
            seekBar.setProgress(70);
            mFilterAdjuster = new GPUImageFilterTools.FilterAdjuster(mFilter);
        }
    }
    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.button_choose_filter:
//                GPUImageFilterTools.showDialog(this, new GPUImageFilterTools.OnGpuImageFilterChosenListener() {
//                    @Override
//                    public void onGpuImageFilterChosenListener(final GPUImageFilter filter) {
//                        switchFilterTo(filter);
//                        mGPUImageView.requestRender();
//                    }
//                });
//                break;
//            case R.id.button_save:
//                break;
//            default:
//                break;
//        }
    }
    @Override
    public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
        if (mFilterAdjuster != null) {
            mFilterAdjuster.adjust(progress);
        }
        mGPUImageView.requestRender();
    }

    @Override
    public void onStartTrackingTouch(final SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(final SeekBar seekBar) {
    }
    private int getDpi()
    {   int dpi = 0;
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics",DisplayMetrics.class);
            method.invoke(display, dm);
            dpi=dm.heightPixels;
        }catch(Exception e){
            e.printStackTrace();
        }
        return dpi;
    }
}
