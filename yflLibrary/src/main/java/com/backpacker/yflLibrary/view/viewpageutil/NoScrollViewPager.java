package com.backpacker.yflLibrary.view.viewpageutil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.view
 * @Description: 防止viewpage滑动
 * @author: L-BackPacker
 * @date: 2019/4/1 0:38
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class NoScrollViewPager extends ViewPager {
    private boolean isPagingEnabled = true;
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }

}