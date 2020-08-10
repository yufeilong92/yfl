package com.backpacker.yflLibrary.view.recyclerviewutil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyRecyclerView.java
 * @Package com.xuechuan.xcedu.weight
 * @Description: 滑动冲突
 * @author: YFL
 * @date: 2018/5/16 22:50
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018/5/16 星期三
 */
public class MyRecyclerView extends RecyclerView {
    private ScrollView parent;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setParentScrollAble(false);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                setParentScrollAble(false);
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(e);
    }

    private void setParentScrollAble(boolean scrollAble) {
        // 此方法可决定 ScrollView 是否会拦截子 View的手势事件
//        parent.requestDisallowInterceptTouchEvent(!scrollAble);
    }

    public void setParentScrollView(ScrollView scrollView) {
        this.parent = scrollView;
    }
}
