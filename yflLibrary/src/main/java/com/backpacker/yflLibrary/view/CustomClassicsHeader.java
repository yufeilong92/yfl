package com.backpacker.yflLibrary.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.NonNull;


/**
 * @Author : YFL  is Creating a porject in xiaole
 * @Package cn.ruiye.xiaole.view
 * @Email : yufeilong92@163.com
 * @Time :2019/11/9 10:31
 * @Purpose : 自定义刷新头部
 */
/*
public  class CustomClassicsHeader extends LinearLayout implements RefreshHeader {
 BaseApplication
  init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(R.color.main_bg, R.color.main_text9)
                return@setDefaultRefreshHeaderCreator ClassicsHeader(context).setSpinnerStyle(
                    SpinnerStyle.Translate
                )
            }

            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                return@setDefaultRefreshFooterCreator ClassicsFooter(context).setSpinnerStyle(
                    SpinnerStyle.Translate
                )
            }
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setRefreshHeader(CustomClassicsHeader(context))
                layout.setHeaderHeight(60f)
                val refreshHeader = layout.refreshHeader
                refreshHeader!!
            }
            instance = BaseApplication()
        }

    private TextView mHeaderText;//标题文本
    private ImageView mArrowView;//下拉箭头
    private ImageView mProgressView;//刷新动画视图
//    private ProgressDrawable mProgressDrawable;//刷新动画
    private AnimationDrawable mProgressDrawable;//刷新动画

    public CustomClassicsHeader(Context context) {
        this(context, null);
    }
    public CustomClassicsHeader(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        setGravity(Gravity.CENTER);
        mHeaderText = new TextView(context);
//        mProgressDrawable = new ProgressDrawable();
        mArrowView = new ImageView(context);
        mProgressView = new ImageView(context);
        mProgressView.setImageResource(R.drawable.animation_loading_refresh);
        mProgressDrawable= (AnimationDrawable) mProgressView.getDrawable();
        mArrowView.setImageDrawable(new ArrowDrawable());
        addView(mProgressView, dp2px(40), dp2px(40));
        addView(mArrowView, dp2px(20), dp2px(20));
        addView(new Space(context), dp2px(20), dp2px(20));
        addView(mHeaderText, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        setMinimumHeight(dp2px(60));
    }

    private float density = Resources.getSystem().getDisplayMetrics().density;

    private int dp2px(int dpValue) {
        float v = 0.5f + dpValue * density;
        return (int) v;
    }
    @NonNull
    public View getView() {
        return this;//真实的视图就是自己，不能返回null
    }
    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout layout, int height, int maxDragHeight) {
        mProgressDrawable.start();//开始动画
    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        mProgressDrawable.stop();//停止动画
        mProgressView.setVisibility(GONE);//隐藏动画
        if (success){
            mHeaderText.setText("刷新完成");
        } else {
            mHeaderText.setText("刷新失败");
        }
        return 500;//延迟500毫秒之后再弹回
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                mHeaderText.setText("下拉开始刷新");
                mArrowView.setVisibility(VISIBLE);//显示下拉箭头
                mProgressView.setVisibility(GONE);//隐藏动画
                mArrowView.animate().rotation(0);//还原箭头方向
                break;
            case Refreshing:
                mHeaderText.setText("正在刷新");
                mProgressView.setVisibility(VISIBLE);//显示加载动画
                mArrowView.setVisibility(GONE);//隐藏箭头
                break;
            case ReleaseToRefresh:
                mHeaderText.setText("释放立即刷新");
                mArrowView.animate().rotation(180);//显示箭头改为朝上
                break;
        }
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

//        @Override
//        public void onPulling(float percent, int offset, int height, int maxDragHeight) {
//
//        }
//        @Override
//        public void onReleasing(float percent, int offset, int height, int maxDragHeight) {
//
//        }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }
}*/
