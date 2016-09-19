package com.example.huster.instagram.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
/**
 * Created by huster on 2016/9/19.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;
    public ViewPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> list){
        super(fragmentManager);
        this.list = list;
    }
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return list.get(position);
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }
}