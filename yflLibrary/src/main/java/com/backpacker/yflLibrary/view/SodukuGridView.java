package com.backpacker.yflLibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @Author : YFL  is Creating a porject in basehttps
 * @Package com.backpacker.yflLibrary.view
 * @Email : yufeilong92@163.com
 * @Time :2019/11/15 11:01
 * @Purpose :
 */
public class SodukuGridView extends GridView {

    public SodukuGridView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public SodukuGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public SodukuGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}