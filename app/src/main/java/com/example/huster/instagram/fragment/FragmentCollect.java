package com.example.huster.instagram.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huster.instagram.R;

public class FragmentCollect extends Fragment {
    static Fragment fragmentCollect = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    static public Fragment newInstance(){
        if(fragmentCollect==null) fragmentCollect = new FragmentCollect();
        return fragmentCollect;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collect, container, false);
    }

    @Override
    public void onDestroy() {
        fragmentCollect = null;
        super.onDestroy();
    }
}
