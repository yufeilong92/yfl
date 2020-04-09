package com.backpacker.yflLibrary.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;

import static java.lang.Math.PI;

/**
 * @Author : YFL  is Creating a porject in basehttps
 * @Package com.backpacker.yflLibrary.view
 * @Email : yufeilong92@163.com
 * @Time :2019/11/28 15:54
 * @Purpose :
 */
public class LoadingView extends View {
    private final static String TAG = "LoadingView";

    private final static int LEFT_BALL_DOWN = 1;
    private final static int LEFT_BALL_UP = 2;
    private final static int RIGHT_BALL_DOWN = 3;
    private final static int RIGHT_BALL_UP = 4;

    private Paint paint1, paint2, paint3, paint4, paint5;
    private int mCurrentAnimatorValue;
    private int circleRadius = 10; //小球的半径
    private int distance = 60; //小球开始下落到最低点的距离
    private int mCurrentState = LEFT_BALL_DOWN;


    public LoadingView(Context context) {
        super(context);
        init(context);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {

        paint1 = getPaint(Color.RED);
        paint2 = getPaint(Color.YELLOW);
        paint3 = getPaint(Color.GREEN);
        paint4 = getPaint(Color.BLUE);
        paint5 = getPaint(Color.CYAN);

        ValueAnimator animator = ValueAnimator.ofInt(0, 90);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentAnimatorValue = (int) animation.getAnimatedValue();
                Log.e(TAG, "onAnimationUpdate : mCurrentAnimatorValue = " + mCurrentAnimatorValue);

                invalidate();
            }
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e(TAG, "onAnimationRepeat : mCurrentAnimatorValue = " + mCurrentAnimatorValue);
                switch (mCurrentState) {
                    case LEFT_BALL_DOWN:
                        mCurrentState = RIGHT_BALL_UP;
                        break;
                    case RIGHT_BALL_UP:
                        mCurrentState = RIGHT_BALL_DOWN;
                        break;
                    case RIGHT_BALL_DOWN:
                        mCurrentState = LEFT_BALL_UP;
                        break;
                    case LEFT_BALL_UP:
                        mCurrentState = LEFT_BALL_DOWN;
                        break;
                }
            }
        });
        animator.setStartDelay(500);
        animator.setDuration(600);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();

    }

    private Paint getPaint(int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x, y;
        double cosValue = Math.cos(PI * mCurrentAnimatorValue / 180);
        double sinValue = Math.sin(PI * mCurrentAnimatorValue / 180);
        drawFourBall(canvas);
        switch (mCurrentState) {
            case LEFT_BALL_DOWN://最左边小球往下撞击
                x = circleRadius + (int) ((distance - circleRadius) * (1 - cosValue));
                y = getHeight() - distance + (int) ((distance - circleRadius) * sinValue);
                canvas.drawCircle(x, y, circleRadius, paint1);
                break;
            case RIGHT_BALL_UP://最右边小球往上撞击
                x = distance + 8 * circleRadius + (int) ((distance - circleRadius) * sinValue);
                y = getHeight() - distance + (int) (cosValue * (distance - circleRadius));
                canvas.drawCircle(x, y, circleRadius, paint5);
                break;
            case RIGHT_BALL_DOWN://最右边小球往下撞击
                x = distance + 8 * circleRadius + (int) ((distance - circleRadius) * (cosValue));
                y = (getHeight() - distance) + (int) ((distance - circleRadius) * (sinValue));
                canvas.drawCircle(x, y, circleRadius, paint5);
                break;
            case LEFT_BALL_UP://最左边小球往上撞击
                x = distance - (int) ((distance - circleRadius) * sinValue);
                y = getHeight() - distance + (int) ((distance - circleRadius) * cosValue);
                canvas.drawCircle(x, y, circleRadius, paint1);
                break;
        }

    }


    private void drawFourBall(Canvas canvas) {

        int y = getHeight() - circleRadius;
        canvas.drawCircle(distance + 2 * circleRadius, y, circleRadius, paint2);
        canvas.drawCircle(distance + 4 * circleRadius, y, circleRadius, paint3);
        canvas.drawCircle(distance + 6 * circleRadius, y, circleRadius, paint4);

        if (mCurrentState == LEFT_BALL_DOWN || mCurrentState == LEFT_BALL_UP) {//最左边球运动的时候，要绘制最右边的球
            canvas.drawCircle(distance + 8 * circleRadius, y, circleRadius, paint5);
        } else if (mCurrentState == RIGHT_BALL_UP || mCurrentState == RIGHT_BALL_DOWN) {//最右边球运动的时候，要绘制最左边的球
            canvas.drawCircle(distance, y, circleRadius, paint1);
        }


    }
}
