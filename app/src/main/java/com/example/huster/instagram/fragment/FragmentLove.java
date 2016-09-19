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
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    public FragmentLove() {
        // Required empty public constructor
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
        InitView();
    }

    void InitView(){
        mViewPager = (ViewPager)getView().findViewById(R.id.viewpager_love);
        tabLayout = (TabLayout)getView().findViewById(R.id.tab);
        fragments.add(FragmentFactory.getInstanceByID(Constant.FragmentHotID));
        fragments.add(FragmentFactory.getInstanceByID(Constant.FragmentSaveID));
        mViewPager.setAdapter(new ViewPagerAdapter(getFragmentManager(), fragments));//如果出问题，改成getChildFragmentManager()
        mViewPager.setCurrentItem(0);
        tabLayout.addTab(tabLayout.newTab().setText("热门"));
        tabLayout.addTab(tabLayout.newTab().setText("收藏"));
        tabLayout.setupWithViewPager(mViewPager);
        //不加下面这行会有问题，因为上面那个函数会把tab删除
        tabLayout.getTabAt(0).setText("热门");
        tabLayout.getTabAt(1).setText("收藏");

    }
}
