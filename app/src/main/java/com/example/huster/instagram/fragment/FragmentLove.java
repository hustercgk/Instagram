package com.example.huster.instagram.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.huster.instagram.R;
import com.example.huster.instagram.factory.FragmentFactory;
import com.example.huster.instagram.util.Constant;
import java.util.ArrayList;
import com.example.huster.instagram.adapter.ViewPagerAdapter;

public class FragmentLove extends Fragment {
    private ViewPager mViewPager;
    private TabLayout mTablayout;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    static Fragment fragmentLove = null;
    public FragmentLove() {
        // Required empty public constructor
    }
    static public Fragment newInstance(){
        if(fragmentLove==null) fragmentLove = new FragmentLove();
        return fragmentLove;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_love, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitView();
    }

    void InitView(){
        mViewPager = (ViewPager)getView().findViewById(R.id.viewpager_love);
        mTablayout = (TabLayout)getView().findViewById(R.id.tab_love);
        fragments.add(FragmentMyState.newInstance());
        fragments.add(FragmentMyComment.newInstance());
        fragments.add(FragmentCollect.newInstance());
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), fragments));//如果出问题，改成getChildFragmentManager()
        mViewPager.setCurrentItem(0);
        mTablayout.addTab(mTablayout.newTab().setText("我的"));
        mTablayout.addTab(mTablayout.newTab().setText("评论"));
        mTablayout.addTab(mTablayout.newTab().setText("收藏"));
        mTablayout.setupWithViewPager(mViewPager);
        //不加下面这行会有问题，因为上面那个函数会把tab删除，然后再new tab，所以会没有文字
        mTablayout.getTabAt(0).setText("我的");
        mTablayout.getTabAt(1).setText("评论");
        mTablayout.getTabAt(2).setText("收藏");
    }

    @Override
    public void onDestroy() {
        fragmentLove = null;
        super.onDestroy();
    }
}
