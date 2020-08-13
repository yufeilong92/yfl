package com.backpacker.yflLibrary.view.viewpageutil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @Author : YFL  is Creating a porject in basehttps
 * @Package com.backpacker.yflLibrary.view
 * @Email : yufeilong92@163.com
 * @Time :2019/11/15 11:01
 * @Purpose :解决photeView
 */
public class PhotoViewPager extends ViewPager {
    public PhotoViewPager(@NonNull Context context) {
        super(context);
    }

    public PhotoViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //是否可以进行滑动
    private boolean isSlide = false;

    public void setSlide(boolean slide) {
        this.isSlide = slide;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return this.isSlide && super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isSlide && super.onTouchEvent(event);
    }
}

