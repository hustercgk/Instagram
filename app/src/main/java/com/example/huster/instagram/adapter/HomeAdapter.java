package com.example.huster.instagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.huster.instagram.R;

import java.util.ArrayList;

/**
 * Created by huster on 2016/9/16.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context mContext;
    ArrayList<String> mList;
    public static final int LIST_NUMS = 10;
    public static final  int LOAD_MORE = 1;
    public static final int NORMAL_VIEW = 2;
    public HomeAdapter(Context context, ArrayList<String> list){
        this.mContext = context;
        this.mList = list;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder){
            ViewHolder viewHolder = (ViewHolder)holder;
            viewHolder.imageView.setImageResource(R.drawable.test);
            return;
        }
        if(holder instanceof FooterHolder){
            return;
        }
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if(viewType==LOAD_MORE){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_view, parent, false);
            return new FooterHolder(v);
        }
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        String s = mList.get(position);
        if(s.equals("loadmore")) return LOAD_MORE;
        return NORMAL_VIEW;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView)v.findViewById(R.id.card);
        }
    }
    public static class FooterHolder extends RecyclerView.ViewHolder{
        public FooterHolder(View v){
            super(v);
        }
    }
}
