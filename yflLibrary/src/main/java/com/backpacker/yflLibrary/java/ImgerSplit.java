package com.backpacker.yflLibrary.java;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.java
 * @Description: t图片拼接
 * @author: L-BackPacker
 * @date: 2019/4/1 0:21
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class ImgerSplit {
    /**
     *
     * @param bit1 上面布局
     * @param bit2 下图片
     * @return
     */
    public static Bitmap add2Bitmap(Bitmap bit1, Bitmap bit2) {
        Bitmap newBit = null;
        int width = bit1.getWidth();
        if (bit2.getWidth() != width) {
            int h2 = bit2.getHeight() * width / bit2.getWidth();
            newBit = Bitmap.createBitmap(width, bit1.getHeight() + h2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(newBit);
            Bitmap newSizeBitmap2 = getNewSizeBitmap(bit2, width, h2);
            canvas.drawBitmap(bit1, 0, 0, null);
            canvas.drawBitmap(newSizeBitmap2, 0, bit1.getHeight(), null);
        } else {
            newBit = Bitmap.createBitmap(width, bit1.getHeight() + bit2.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(newBit);
            canvas.drawBitmap(bit1, 0, 0, null);
            canvas.drawBitmap(bit2, 0, bit1.getHeight(), null);
        }
        return newBit;
    }
    private static Bitmap getNewSizeBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        float scaleWidth = ((float) newWidth) / bitmap.getWidth();
        float scaleHeight = ((float) newHeight) / bitmap.getHeight();
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap bit1Scale = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,
                true);
        return bit1Scale;
    }
}