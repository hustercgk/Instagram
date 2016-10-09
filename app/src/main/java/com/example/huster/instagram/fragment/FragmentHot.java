package com.example.huster.instagram.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huster.instagram.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHot extends Fragment {
    static Fragment fragmentHot = null;
    public FragmentHot() {
        // Required empty public constructor
    }
    static public Fragment newInstance(){
        if(fragmentHot==null) fragmentHot = new FragmentHot();
        return fragmentHot;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot, container, false);
    }

    @Override
    public void onDestroy() {
        fragmentHot = null;
        super.onDestroy();
    }
}
