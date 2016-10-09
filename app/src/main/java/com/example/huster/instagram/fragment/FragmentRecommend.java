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
public class FragmentRecommend extends Fragment {

    static Fragment fragmentRecommend = null;
    public FragmentRecommend() {
        // Required empty public constructor
    }
    static public Fragment newInstance(){
        if(fragmentRecommend==null) fragmentRecommend = new FragmentRecommend();
        return fragmentRecommend;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recomment, container, false);
    }

    @Override
    public void onDestroy() {
        fragmentRecommend = null;
        super.onDestroy();
    }
}
