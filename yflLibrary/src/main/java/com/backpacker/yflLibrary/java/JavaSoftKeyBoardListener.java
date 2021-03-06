package com.backpacker.yflLibrary.java;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;


import java.lang.reflect.Field;

/**
 * @Author : YFL  is Creating a porject in xiaole
 * @Package cn.ruiye.xiaole.utils
 * @Email : yufeilong92@163.com
 * @Time :2019/11/25 16:37
 * @Purpose : 获取键盘弹出试图
 */
public class JavaSoftKeyBoardListener {
    private View rootView;//activity的根视图
    int rootViewVisibleHeight;//纪录根视图的显示高度
    private OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener;

    public JavaSoftKeyBoardListener(Context activity) {
        //获取activity的根视图a
        Activity as= (Activity) activity;
        rootView =   as.getWindow().getDecorView();
        //监听视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获取当前根视图在屏幕上显示的大小
                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);
                int visibleHeight = r.height();
                if (rootViewVisibleHeight == 0) {
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }
                //根视图显示高度没有变化，可以看作软键盘显示／隐藏状态没有改变
                if (rootViewVisibleHeight == visibleHeight) {
                    return;
                }
                //根视图显示高度变小超过200，可以看作软键盘显示了
                if (rootViewVisibleHeight - visibleHeight > 200) {
                    if (onSoftKeyBoardChangeListener != null) {
                        onSoftKeyBoardChangeListener.keyBoardShow(rootViewVisibleHeight - visibleHeight);
                    }
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }
                //根视图显示高度变大超过200，可以看作软键盘隐藏了
                if (visibleHeight - rootViewVisibleHeight > 200) {
                    if (onSoftKeyBoardChangeListener != null) {
                        onSoftKeyBoardChangeListener.keyBoardHide(visibleHeight - rootViewVisibleHeight);
                    }
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }
            }
        });
    }

    private void setOnSoftKeyBoardChangeListener(OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        this.onSoftKeyBoardChangeListener = onSoftKeyBoardChangeListener;
    }

    public interface OnSoftKeyBoardChangeListener {
        void keyBoardShow(int height);

        void keyBoardHide(int height);
    }

    public static void setListener(Activity activity, OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        JavaSoftKeyBoardListener javaSoftKeyBoardListener = new JavaSoftKeyBoardListener(activity);
        javaSoftKeyBoardListener.setOnSoftKeyBoardChangeListener(onSoftKeyBoardChangeListener);
    }


    private  ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    private InputMethodManager imm;
    int vHeight = 0;


    /**
     * 监听键盘高度和键盘时候处于打开状态，在调用的Activity中的onDestroy()方法中调用
     * 该类中的removeGlobalOnLayoutListener()方法来移除监听
     *
     * @param activity 当前页面
     * @param listener 监听 返回高度及是否打开
     */
    public void observeSoftKeyboard(Activity activity, final OnSoftKeyboardChangeListener listener) {
        final View decorView = activity.getWindow().getDecorView();
        final int statusBarHeight = getStatusBarHeight(activity);// 状态栏的高度
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private int keyboardHeight;// 软键盘的高度
            private boolean isShowKeyboard;// 软键盘的显示状态
            private boolean isVKeyMap;//虚拟按键，华为手机

            @Override
            public void onGlobalLayout() {
                onGlobalLayoutListener = this;

                // 应用可以显示的区域。此处包括应用占用的区域，
                // 以及ActionBar和状态栏，但不含设备底部的虚拟按键。
                Rect r = new Rect();
                decorView.getWindowVisibleDisplayFrame(r);
                // 屏幕高度。这个高度不含虚拟按键的高度
                int screenHeight = decorView.getRootView().getHeight();

                // 在不显示软键盘时，heightDiff等于状态栏的高度
                // 在显示软键盘时，heightDiff会变大，等于软键盘加状态栏的高度。
                // 所以heightDiff大于状态栏高度时表示软键盘出现了，
                // 这时可算出软键盘的高度，即heightDiff减去状态栏的高度

                if ((screenHeight - r.bottom) < screenHeight / 4) {
                    isVKeyMap = true;
                    vHeight = screenHeight - r.bottom;
                } else if ((screenHeight - r.bottom) == 0) {
                    vHeight = 0;
                    isVKeyMap = false;
                }

                int heightDiff = screenHeight - (r.bottom - r.top);
                if (keyboardHeight == 0 && heightDiff > (screenHeight / 4)) {
                    if (isVKeyMap) {
                        keyboardHeight = heightDiff - vHeight;
                    } else {
                        keyboardHeight = heightDiff;
                    }
                }

                if (isVKeyMap) {

                    if (isShowKeyboard) {
                        // 如果软键盘是弹出的状态，并且heightDiff小于等于状态栏高度，
                        // 说明这时软键盘已经收起
                        if (heightDiff <= (statusBarHeight + vHeight)) {
                            isShowKeyboard = false;
                            listener.onSoftKeyBoardChange(keyboardHeight, isShowKeyboard);
                        }
                    } else {
                        // 如果软键盘是收起的状态，并且heightDiff大于状态栏高度，
                        if (heightDiff > (statusBarHeight) && heightDiff > (screenHeight / 4)) {
                            isShowKeyboard = true;
                            listener.onSoftKeyBoardChange(keyboardHeight, isShowKeyboard);
                        }
                    }
                } else {
                    if (isShowKeyboard) {
                        // 如果软键盘是弹出的状态，并且heightDiff小于等于状态栏高度，
                        // 说明这时软键盘已经收起
                        if (heightDiff <= statusBarHeight) {
                            isShowKeyboard = false;
                            listener.onSoftKeyBoardChange(keyboardHeight, isShowKeyboard);
                        }
                    } else {
                        if (heightDiff > (statusBarHeight)) {
                            isShowKeyboard = true;
                            listener.onSoftKeyBoardChange(keyboardHeight, isShowKeyboard);
                        }
                    }
                }
            }
        });
    }

    /**
     * 接口监听
     */
    public  interface OnSoftKeyboardChangeListener {
        //返回键盘高度及是否显示
        void onSoftKeyBoardChange(int softKeybardHeight, boolean isShow);
    }

    /**
     * 移除键盘监听的方法
     * @param activity 当前activity
     */
    public void removeGlobalOnLayoutListener(Activity activity) {
        final View decorView = activity.getWindow().getDecorView();
        if (onGlobalLayoutListener != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                decorView.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
            } else {
                decorView.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
            }
        }
    }

    /**
     * 获取状态栏高度
     * @param context 上下文对象
     * @return int 高度
     */
    public  int getStatusBarHeight(Context context) {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 隐藏键盘
     * @param context 上下文对象
     * @param view     指定view
     */
    public void hideKeyboard(Context context, View view) {
        view.requestFocus();
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏键盘
     * @param activity 当前activity
     */
    public void hideKeyboard(Activity activity) {
        imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示键盘
     * @param context 上下文对象
     * @param view   指定view
     */
    public void showKeyboard(Context context, View view) {
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

    /**
     * 解除键盘高度遮挡UI的方法
     *  1、获取main在窗体的可视区域
     *  2、获取main在窗体的不可视区域高度
     *  3、判断不可视区域高度
     *      1、大于100：键盘显示  获取Scroll的窗体坐标
     *                           算出main需要滚动的高度，使scroll显示。
     *      2、小于100：键盘隐藏
     *   4. 不需要在AndroidManifest.xml中配置键盘属性
     *
     * @param main 根布局
     * @param scroll 需要显示的最下方View
     */
    public static void addLayoutListener(final View main, final View scroll) {
        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                main.getWindowVisibleDisplayFrame(rect);
                int mainInvisibleHeight = main.getRootView().getHeight() - rect.bottom;
                if (mainInvisibleHeight > 100) {
                    int[] location = new int[2];
                    scroll.getLocationInWindow(location);
                    int srollHeight = (location[1] + scroll.getHeight()) - rect.bottom;
                    main.scrollTo(0, srollHeight);
                } else {
                    main.scrollTo(0, 0);
                }
            }
        });
    }
}
