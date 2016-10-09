package com.example.huster.instagram.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huster.instagram.R;

public class FragmentMyState extends Fragment {
    static Fragment fragmentMyState = null;
    public FragmentMyState(){

    }
    static public Fragment newInstance(){
        if(fragmentMyState==null) fragmentMyState = new FragmentMyState();
        return fragmentMyState;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_state, container, false);
    }

    @Override
    public void onDestroy() {
        fragmentMyState = null;
        super.onDestroy();
    }
}
