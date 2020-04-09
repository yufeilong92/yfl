package com.backpacker.yflLibrary.java;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * @Author : YFL  is Creating a porject in xiaole
 * @Package com.backpacker.yflLibrary.java
 * @Email : yufeilong92@163.com
 * @Time :2020/3/6 11:33
 * @Purpose :图片展示数据
 */
public class ImagerShowSizeUtil {
    /***
     * @param img  图片
     * @param WithSize 比例
     * @param isWith true 宽  false 高
     * @param with 宽
     * @param height 高
     * @param isMul 是否是乘
     * @return
     */
    public static void setImgerSizeShow(ImageView img, int WithSize, boolean isWith, int with, int height,boolean isMul) {
        //屏幕宽度
        try {
            //倍数
            double size = 0.0;
            //计算后的高宽度
            double div = 0.0;
            //宽高比
            if (isWith) {
                size = ArithUtil.div(with, height, 2);
            } else {
                //高宽比
                size = ArithUtil.div(height, with, 2);

            }
            if (isMul)
                div = ArithUtil.mul(WithSize, size, 2);
            else
                div = ArithUtil.div(WithSize, size, 2);

            ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
            layoutParams.height = (int) div;
            img.setLayoutParams(layoutParams);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /***
     * @param img  图片
     * @param height 高度
     * @param isWith 是否宽度  false 高度
     * @param size 倍数 宽高比
     * @return
     */
    public static void setImgerSizeShow(ImageView img, int height, boolean isWith, double size) {
        //屏幕宽度
        try {
            double div = 0.0;
            if (isWith)
                div = ArithUtil.div(height, size, 2);
            else
                div = ArithUtil.mul(height, size, 2);
            ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
            layoutParams.height = (int) div;
            img.setLayoutParams(layoutParams);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /***
     * @param mContent 上下文
     * @param img  图片
     * @param size 倍数
     * @return
     */
    public static void setImgerSizeShow(Context mContent, ImageView img, double size) {
        WindowManager wm = (WindowManager) mContent.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        //屏幕宽度
        int widthPixels = dm.widthPixels;
        try {
            double div = ArithUtil.div(widthPixels, size, 2);
            ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
            layoutParams.height = (int) div;
            img.setLayoutParams(layoutParams);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /***
     * @param mContent 上下文
     * @param img  图片
     * @param size 倍数
     * @return
     */
    public static void setImgerSizeShow(Context mContent, ImageView img, String size) {
        String[] split = size.split("x");
        try {
            double sizeDoubul = ArithUtil.div(Double.valueOf(split[0]), Double.valueOf(split[1]), 2);
            WindowManager wm = (WindowManager) mContent.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            //屏幕宽度
            int widthPixels = dm.widthPixels;
            try {
                double div = ArithUtil.div(widthPixels, sizeDoubul, 2);
                ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
                layoutParams.height = (int) div;
                img.setLayoutParams(layoutParams);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


}
