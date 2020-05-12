package com.backpacker.yflLibrary.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * @Author : YFL  is Creating a porject in xiaole
 * @Package com.backpacker.yflLibrary.view
 * @Email : yufeilong92@163.com
 * @Time :2019/11/29 14:21
 * @Purpose :
 */
public class RoundTransformTop implements Transformation {
    private int radius;//圆角值
    private HalfType half;

    public RoundTransformTop(int radius, HalfType type) {
        this.radius = radius;
        this.half = type;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int width = source.getWidth();
        int height = source.getHeight();
        //画板
        Bitmap bitmap = Bitmap.createBitmap(width, height, source.getConfig());
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmap);//创建同尺寸的画布
        paint.setAntiAlias(true);//画笔抗锯齿
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        //画圆角背景
        RectF rectF = new RectF(new Rect(0, 0, width, height));//赋值
        canvas.drawRoundRect(rectF, radius, radius, paint);//画圆角矩形
        //
        paint.setFilterBitmap(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        source.recycle();//释放

        switch (half) {
            case LEFT:
                return Bitmap.createBitmap(bitmap, 0, 0, width - radius, height);
            case RIGHT:
                return Bitmap.createBitmap(bitmap, width - radius, 0, width - radius, height);
            case TOP:// 上半部分圆角化 “- roundPixels”实际上为了保证底部没有圆角，采用截掉一部分的方式，就是截掉和弧度一样大小的长度
                return Bitmap.createBitmap(bitmap, 0, 0, width, height - radius);
            case BOTTOM:
                return Bitmap.createBitmap(bitmap, 0, height - radius, width, height - radius);
            case ALL:
                return bitmap;
            default:
                return bitmap;
        }

    }

    @Override
    public String key() {
        return "round : radius = " + radius;
    }

    /**
     * 图片圆角规则 eg. TOP：上半部分
     */
    public  enum HalfType {
        LEFT, // 左上角 + 左下角
        RIGHT, // 右上角 + 右下角
        TOP, // 左上角 + 右上角
        BOTTOM, // 左下角 + 右下角
        ALL // 四角
    }

}
