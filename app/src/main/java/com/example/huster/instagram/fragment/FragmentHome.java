package com.example.huster.instagram.fragment;

import android.os.Bundle;
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


public class FragmentHome extends Fragment {
    private ViewPager mViewPager;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        InitView();
    }

    void InitView(){
        mViewPager = (ViewPager)getView().findViewById(R.id.viewpager_home);
        fragments.add(FragmentFactory.getInstanceByID(Constant.FragmentPushStateID));
        fragments.add(FragmentFactory.getInstanceByID(Constant.FragmentHomeStateID));
        fragments.add(FragmentFactory.getInstanceByID(Constant.FragmentPushStateID));
        mViewPager.setAdapter(new ViewPagerAdapter(getFragmentManager(), fragments));//如果出问题，改成getChildFragmentManager()
        mViewPager.setCurrentItem(1);
    }
}
