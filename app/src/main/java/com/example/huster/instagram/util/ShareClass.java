package com.example.huster.instagram.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by huster on 2016/10/11.
 */
public class ShareClass {
    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int left, right, top, bottom;
        public SpaceItemDecoration(int left, int right, int top, int bottom) {
            this.left = left;
            this.right = right;
            this.top = top;
            this.bottom = bottom;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.top = top;
            outRect.left = left;
            outRect.bottom = bottom;
            outRect.right = right;
        }
    }
}
