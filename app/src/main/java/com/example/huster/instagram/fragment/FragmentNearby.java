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
public class FragmentNearby extends Fragment {

    static Fragment fragmentNearby = null;
    public FragmentNearby() {
        // Required empty public constructor
    }
    static public Fragment newInstance(){
        if(fragmentNearby==null) fragmentNearby = new FragmentNearby();
        return fragmentNearby;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nearby, container, false);
    }

    @Override
    public void onDestroy() {
        fragmentNearby = null;
        super.onDestroy();
    }
}
