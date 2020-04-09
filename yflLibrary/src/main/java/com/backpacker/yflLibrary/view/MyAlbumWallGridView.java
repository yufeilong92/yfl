package com.backpacker.yflLibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.weight
 * @Description: 显示不全问题
 * @author: L-BackPacker
 * @date: 2019.01.11 下午 4:41
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class MyAlbumWallGridView extends GridView {
    public MyAlbumWallGridView(Context context) {
        super(context);
    }

    public MyAlbumWallGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyAlbumWallGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //自定义GridView的高度
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}