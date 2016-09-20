package com.example.huster.instagram.factory;

//import android.support.v4.app.Fragment;
import com.example.huster.instagram.fragment.FragmentDiscover;
import com.example.huster.instagram.fragment.FragmentHot;
import com.example.huster.instagram.fragment.FragmentMyComment;
import com.example.huster.instagram.fragment.FragmentNearby;
import com.example.huster.instagram.util.Constant;
import android.support.v4.app.Fragment;

import com.example.huster.instagram.fragment.FragmentHome;
import com.example.huster.instagram.fragment.FragmentMyState;
import com.example.huster.instagram.fragment.FragmentLove;
import com.example.huster.instagram.fragment.FragmentPersonal;
import com.example.huster.instagram.fragment.FragmentCollect;
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
            case Constant.FragmentDiscoverID:
                fragment = new FragmentDiscover();
                break;
            case Constant.FragmentLoveID:
                fragment = new FragmentLove();
                break;
            case Constant.FragmentPersonalID:
                fragment = new FragmentPersonal();
                break;
            case Constant.FragmentMyStateID:
                fragment = new FragmentMyState();
                break;
            case Constant.FragmentCollectID:
                fragment = new FragmentCollect();
                break;
            case Constant.FragmentMyCommentID:
                fragment = new FragmentMyComment();
                break;
            case Constant.FragmentHotID:
                fragment = new FragmentHot();
                break;
            case Constant.FragmentNearbyID:
                fragment = new FragmentNearby();
                break;
            case Constant.FragmentRecommendID:
                fragment = new FragmentNearby();
                break;
        }
        return fragment;
    }
}
