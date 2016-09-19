package com.example.huster.instagram.factory;

//import android.support.v4.app.Fragment;

import android.app.Fragment;
import android.content.Context;

import com.example.huster.instagram.fragment.FragmentCamera;
import com.example.huster.instagram.fragment.FragmentHome;
import com.example.huster.instagram.fragment.FragmentLove;
import com.example.huster.instagram.fragment.FragmentPersonal;
import com.example.huster.instagram.fragment.FragmentSearch;

/**
 * Created by huster on 2016/9/15.
 */
public class FragmentFactory {
    final static int FragmentHomeID = 0;
    final static int FragmentSearchID = 1;
    final static int FragmentCameraID = 2;
    final static int FragmentLoveID = 3;
    final static int FragmentPersonalID = 4;
    public static Fragment getInstanceByID(int ID, Context context){
        Fragment fragment = null;
        switch (ID){
            case FragmentHomeID:
                fragment = new FragmentHome(context);
                break;
            case FragmentSearchID:
                fragment = new FragmentSearch();
                break;
            case FragmentCameraID:
                fragment = new FragmentCamera();
                break;
            case FragmentLoveID:
                fragment = new FragmentLove();
                break;
            case FragmentPersonalID:
                fragment = new FragmentPersonal();
        }
        return fragment;
    }
}
