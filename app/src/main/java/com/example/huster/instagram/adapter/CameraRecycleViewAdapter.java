package com.example.huster.instagram.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.huster.instagram.R;
import com.example.huster.instagram.activity.CameraActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by huster on 2016/9/20.
 */
public class CameraRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    ArrayList<String> mList;
    static Context mContext;
    int w; int h;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public CameraRecycleViewAdapter(Context context, ArrayList<String> list, int w, int h){
        this.mContext = context;
        this.mList = list;
        this.w = w; this.h = h;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view, parent, false);
        Log.d("kangkang", "onCreateViewHolder");
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        Log.d("kangkang", "getItemCount");
        return mList==null?0:mList.size();

    }

    @Override
    public int getItemViewType(int position) {
        Log.d("kangkang", "getItemViewType");
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.string = mList.get(position);
        viewHolder.itemView.setTag(mList.get(position));
        Log.d("kangkang", "onBindViewHolder");
        Picasso.with(mContext)
                .load(new File(viewHolder.string))
                .placeholder(R.drawable.android)//未加载完成时放置的缺省图片
                .resize(w / 4, h / 5)
                .centerCrop()
                .into(viewHolder.imageView);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(String)v.getTag());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public String string;
        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.image_grid);
            /*设置高度，不然高度不会1/5，在placeholder时*/
            ViewGroup.LayoutParams layoutParams =  imageView.getLayoutParams();
            layoutParams.height = CameraActivity.screenHeight/5;
            imageView.setLayoutParams(layoutParams);
         /**/
        }
    }
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String src);
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
