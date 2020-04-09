package com.backpacker.yflLibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.weight
 * @Description: 展示不全
 * @author: L-BackPacker
 * @date: 2019.02.16 下午 4:25
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class CaseGridView extends GridView {
    public CaseGridView(Context context) {
        super(context);
    }

    public CaseGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CaseGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
