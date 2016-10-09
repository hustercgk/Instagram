package com.example.huster.instagram.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huster.instagram.R;

public class FragmentMyComment extends Fragment {
    static Fragment fragmentMyComment = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    static public Fragment newInstance(){
        if(fragmentMyComment==null) fragmentMyComment = new FragmentMyComment();
        return fragmentMyComment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_comment, container, false);
    }

    @Override
    public void onDestroy() {
        fragmentMyComment = null;
        super.onDestroy();
    }
}
