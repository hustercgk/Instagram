package com.example.huster.instagram.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.huster.instagram.R;
import com.example.huster.instagram.adapter.HomeAdapter;
import com.example.huster.instagram.adapter.ViewPagerAdapter;
import com.example.huster.instagram.factory.FragmentFactory;
import com.example.huster.instagram.util.Constant;
import java.util.ArrayList;


public class FragmentHome extends Fragment {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private HomeAdapter homeAdapter;
    private ViewPager viewPager;
    protected Context mContext;
    private ArrayList<String> list= new ArrayList<>();
    private boolean isloading = false, isresfresh = false, isaddprogressbar = false;
    public  FragmentHome(){}
    @SuppressLint("ValidFragment")
    public FragmentHome(Context context){
        this.mContext = context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    //不能在onCreate中初始化组件？因为你onCreateView是在onCreate后执行的
    @Override
    public void onStart() {
        super.onStart();
        //下拉刷新
        mSwipeRefreshLayout = (SwipeRefreshLayout)getView().findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isresfresh) {
                    new RefreshItem().execute();
                    isresfresh = true;
                }
            }
        });
        //recycleview初始化
        for(int i = 0; i<HomeAdapter.LIST_NUMS-1; i++) list.add("test");
        mRecyclerView = (RecyclerView)getView().findViewById(R.id.home_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        homeAdapter = new HomeAdapter(mContext, list);
        mRecyclerView.setAdapter(homeAdapter);
        //判断是否拉到最后
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //如果到达顶部或者底部，这个函数不会再调用,并且次函数比下面的先调用
            }
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(isloading) return;
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if(layoutManager.getItemCount()-1 == layoutManager.findLastVisibleItemPosition()){
                    isloading = true;
                    list.add("loadmore");
                    homeAdapter.notifyDataSetChanged();
                    new LoadMoreItem().execute();
                }
            }
        });
    }

    class LoadMoreItem extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Thread.sleep(3000);
                list.remove(list.size()-1);
                for(int i = 0; i<=5; i++) list.add("test");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            homeAdapter.notifyDataSetChanged();
            isloading = false;
        }
    }
    class RefreshItem extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Thread.sleep(1500);
                for(int i = 0; i<=5; i++) list.add(0, "test");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            mSwipeRefreshLayout.setRefreshing(false);
            homeAdapter.notifyDataSetChanged();
            isresfresh = false;
        }
    }
}
