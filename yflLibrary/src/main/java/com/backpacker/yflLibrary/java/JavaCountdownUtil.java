package com.backpacker.yflLibrary.java;

import android.content.Context;
import android.os.Handler;
import android.widget.Button;

import com.example.UtilsLibrary.R;

import org.jetbrains.annotations.Nullable;

public class JavaCountdownUtil {
    int recLen = 60;
    private Context mContext;
    Handler handler = new Handler();
    private static JavaCountdownUtil util;
    private Button button;
    /**
     * 运行时字体颜色
     */
    private int mColor = -1;
    /**
     * 开始颜色背景
     */
    private int textColot = -1;
    /**
     * 回复原来背景
     */
    private int textColot_n = -1;
    /**
     * 回复原来字体颜色
     */
    private int text_d = -1;
    /**
     * 是否停止
     */
    Boolean isStop = false;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isStop) {
                return;
            }
            recLen--;
            if (recLen == 0) {
                button.setText("重新发送");
                button.setEnabled(true);
                if (mColor == -1) {//重新发送时
                    button.setTextColor(mContext.getResources().getColor(R.color.red));
                    button.setBackgroundResource(textColot_n);
                } else {////运行时
                    button.setTextColor(mContext.getResources().getColor(mColor));
                    button.setBackgroundResource(textColot_n);
                }
                recLen = 60;
                return;
            }
            button.setBackgroundResource(textColot);
            button.setTextColor(mContext.getResources().getColor(R.color.gray));
            button.setText("重发" + "(" + recLen + ")");
            handler.postDelayed(this, 1000);
        }
    };

    /**
     * @param context 上下文
     * @param button  显示按钮
     */
    public void startTime(Context context, final Button button) {
        this.button = button;
        this.mContext = context;
        isStop = false;
        button.setEnabled(false);
        handler.postDelayed(runnable, 1000);
    }

    /**
     * @param context 上下文
     * @param color   失败的颜色
     * @param button  按钮
     */
    public void startTime(Context context, int color, final Button button) {
        this.button = button;
        this.mContext = context;
        this.mColor = color;
        button.setEnabled(false);
        isStop = false;
        handler.postDelayed(runnable, 1000);
    }

    /**
     * @param context   上下文
     * @param color     失败的颜色
     * @param txtColorS 正常字体颜色
     * @param button    按钮
     */
    public void startTime(Context context, int n_color, int color, int txtColorS, final Button button) {
        this.button = button;
        this.mContext = context;
        this.mColor = color;
        this.textColot = txtColorS;
        button.setEnabled(false);
        isStop = false;
        handler.postDelayed(runnable, 1000);
    }

    public static JavaCountdownUtil getInstance() {
        if (util == null)
            util = new JavaCountdownUtil();
        return util;
    }

    public void stop() {
//        isStop = true;
//        handler.postDelayed(null, 1000);
    }

    public void destroy() {
        button = null;
        isStop = false;
        handler.removeCallbacks(runnable);
//        handler.postDelayed(null, 1000);
    }
    /**
     * 设置监听
     */
    private OnNullRestartClickListener onNullRestartClickListener;

    public interface OnNullRestartClickListener {
        public void onNullRestartClickItem();
    }

    public void setOnNullRestartClickListener(OnNullRestartClickListener onNullRestartClickListener) {
        this.onNullRestartClickListener = onNullRestartClickListener;
    }

    public void restart() {
        if (button == null) {
            if (onNullRestartClickListener != null) {
                onNullRestartClickListener.onNullRestartClickItem();
            }
            return;
        }
//        isStop = false;
//        handler.postDelayed(runnable, 1000);
    }

    /***
     *
     * @param mContext
     * @param white  结束回复颜色
     * @param textFuBg
     * @param shapeBtGreen
     * @param shapeBtGray
     * @param btnLoginSendCode
     */
    public void startTime(Context mContext, int white, int textFuBg, int shapeBtGreen, int shapeBtGray, @Nullable Button btnLoginSendCode) {
        this.button = btnLoginSendCode;
        this.mContext = mContext;
        this.mColor = textFuBg;
        this.textColot = shapeBtGray;
        this.text_d = white;
        this.textColot_n = shapeBtGreen;
        button.setEnabled(false);
        recLen = 60;
        isStop = false;
        handler.postDelayed(runnable, 1000);
    }
}
