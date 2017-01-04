package com.example.huster.instagram.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huster.instagram.R;
import com.example.huster.instagram.util.GPUImageFilterTools;

/**
 * Created by huster on 2016/10/11.
 */
public class ImageFilterRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    public  GPUImageFilterTools.FilterList mList;
    Context mContext;
    Uri uri;
    public int id = 0;
    static int height, width;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public ImageFilterRecycleViewAdapter(Context context, GPUImageFilterTools.FilterList list, int h, int w, Uri uri){
        mContext = context;
        mList = list;
        height = h;
        width = w;
        this.uri = uri;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.camerafilter_cardview, parent, false);
        v.setOnClickListener(this);
        v.setPressed(false);
        ViewHolder viewHolder = new ViewHolder(v);
//        viewHolder.set
        return viewHolder;
    }
    @Override
    public int getItemCount() {
        return mList==null?0:mList.names.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        System.out.println("pkusz position "+position);
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.textView.setText(mList.names.get(position));
//        Picasso.with(mContext)
//                .load(uri)
//                .resize((int)(height*0.8), (int)(height*0.8))
//                .centerCrop()
//                .into(viewHolder.imageView);
        viewHolder.imageView.setImageBitmap(mList.bitmaps.get(position));
        if(mList.textColorFlags.get(position)){
            viewHolder.textView.setTextColor(Color.BLACK);
            id = position;
        }
        else {
            viewHolder.textView.setTextColor(Color.GRAY);
        }
        viewHolder.itemView.setTag(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.image_image_filter_recycleview_adapter_view);
            textView = (TextView) v.findViewById(R.id.textview_image_filter_recycleview_adapter_name);
            textView.setTextColor(Color.GRAY);
            ViewGroup.LayoutParams layoutParams =imageView.getLayoutParams();
            layoutParams.width = (int)(height*0.8); layoutParams.height = (int)(height*0.8);
            imageView.setLayoutParams(layoutParams);
            layoutParams = textView.getLayoutParams();
            layoutParams.height = height/5;
            textView.setLayoutParams(layoutParams);
        }
    }
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , int data);
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public void onClick(View v) {
//        TextView textView =  (TextView)v.findViewById(R.id.textview_image_filter_recycleview_adapter_name);
//        textView.setTextColor(Color.BLACK);
//        if(viewHolderS!=null){
//            viewHolderS.textView.setTextColor(Color.GRAY);
//        }
        if(mOnItemClickListener!=null){
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    public class Data{
        public int id = 0;
        public int position = 0;
        public String name = "Normal";
    }
}
