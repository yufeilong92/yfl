package com.backpacker.yflLibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.weight
 * @Description: webview 滑动
 * @author: L-BackPacker
 * @date: 2018.11.30 上午 9:34
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class ScrollWebView extends WebView {

    public OnScrollChangeListener listener;

    public ScrollWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ScrollWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollWebView(Context context) {
        super(context);
    }

    /**
     * 是否滑动
     */
    private int isCrool = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isCrool = 0;
                break;
            case MotionEvent.ACTION_UP:
                int scrollY = getScrollY();
                if (scrollY == 0 && isCrool > 8) {
                    if (listener != null)
                        listener.onPageTop(-1000, 0, 0, 0);
                }
                isCrool = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                isCrool += 1;
                break;
        }

        return super.onTouchEvent(event);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.e("==========", "onScrollChanged: " + l + t + oldl + oldt);

        float webcontent = getContentHeight() * getScale();// webview的高度
        float webnow = getHeight() + getScrollY();// 当前webview的高度
        if (Math.abs(webcontent - webnow) < 1) {
            // 已经处于底端
            // Log.i("TAG1", "已经处于底端");
            listener.onPageEnd(l, t, oldl, oldt);
        } else if (getScrollY() == 0) {
            // Log.i("TAG1", "已经处于顶端");
            listener.onPageTop(l, t, oldl, oldt);
        } else {
            listener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public void setOnScrollChangeListener(OnScrollChangeListener listener) {
        this.listener = listener;
    }

    public interface OnScrollChangeListener {
        public void onPageEnd(int l, int t, int oldl, int oldt);

        public void onPageTop(int l, int t, int oldl, int oldt);

        public void onScrollChanged(int l, int t, int oldl, int oldt);

    }
}