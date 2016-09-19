package com.example.huster.instagram.factory;

//import android.support.v4.app.Fragment;
import com.example.huster.instagram.fragment.FragmentHomeState;
import com.example.huster.instagram.fragment.FragmentPushState;
import com.example.huster.instagram.util.Constant;
import android.support.v4.app.Fragment;
import android.content.Context;
import com.example.huster.instagram.fragment.FragmentCamera;
import com.example.huster.instagram.fragment.FragmentHome;
import com.example.huster.instagram.fragment.FragmentHot;
import com.example.huster.instagram.fragment.FragmentLove;
import com.example.huster.instagram.fragment.FragmentPersonal;
import com.example.huster.instagram.fragment.FragmentSave;
import com.example.huster.instagram.fragment.FragmentSearch;

/**
 * Created by huster on 2016/9/15.
 */
public class FragmentFactory {

    public static Fragment getInstanceByID(int ID){
        Fragment fragment = null;
        switch (ID){
            case Constant.FragmentHomeID:
                fragment = new FragmentHome();
                break;
            case Constant.FragmentSearchID:
                fragment = new FragmentSearch();
                break;
            case Constant.FragmentCameraID:
                fragment = new FragmentCamera();
                break;
            case Constant.FragmentLoveID:
                fragment = new FragmentLove();
                break;
            case Constant.FragmentPersonalID:
                fragment = new FragmentPersonal();
                break;
            case Constant.FragmentHotID:
                fragment = new FragmentHot();
                break;
            case Constant.FragmentSaveID:
                fragment = new FragmentSave();
                break;
            case Constant.FragmentHomeStateID:
                fragment = new FragmentHomeState();
                break;
            case Constant.FragmentPushStateID:
                fragment = new FragmentPushState();
                break;
        }
        return fragment;
    }
}
