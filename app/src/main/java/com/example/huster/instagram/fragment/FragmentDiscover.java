package com.example.huster.instagram.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huster.instagram.R;
import com.example.huster.instagram.adapter.ViewPagerAdapter;
import com.example.huster.instagram.factory.FragmentFactory;
import com.example.huster.instagram.util.Constant;

import java.util.ArrayList;


public class FragmentDiscover extends Fragment {
    private ViewPager mViewPager;
    private TabLayout mTablayout;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false);
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
        mViewPager = (ViewPager)getView().findViewById(R.id.viewpager_discover);
        mTablayout = (TabLayout)getView().findViewById(R.id.tab_discover);
        fragments.add(FragmentFactory.getInstanceByID(Constant.FragmentHotID));
        fragments.add(FragmentFactory.getInstanceByID(Constant.FragmentNearbyID));
        fragments.add(FragmentFactory.getInstanceByID(Constant.FragmentRecommendID));
        mViewPager.setAdapter(new ViewPagerAdapter(getFragmentManager(), fragments));//如果出问题，改成getChildFragmentManager()
        mViewPager.setCurrentItem(0);
        mTablayout.addTab(mTablayout.newTab().setText("热门"));
        mTablayout.addTab(mTablayout.newTab().setText("附近"));
        mTablayout.addTab(mTablayout.newTab().setText("推荐"));
        mTablayout.setupWithViewPager(mViewPager);
        //不加下面这行会有问题，因为上面那个函数会把tab删除
        mTablayout.getTabAt(0).setText("热门");
        mTablayout.getTabAt(1).setText("附近");
        mTablayout.getTabAt(2).setText("推荐");
    }
}
