package cn.yfl.myapplication;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;


/**
 * @author by DELL
 * @date on 2018/3/15
 * @describe 获取验证码 倒计时View 语言需自己处理
 */

public class TimerTextView extends AppCompatTextView {
    private static final int COUNT_DOWN_TIME = 60;
    private CountDownTimer downTimer;

    public TimerTextView(Context context) {
        this(context, null);
    }

    public TimerTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setText("获取验证码");
        setGravity(Gravity.CENTER);
    }

    private void createTimer() {
        downTimer = new CountDownTimer(COUNT_DOWN_TIME * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //四舍五入 优化系统误差
                setText(String.format("获取验证码(%d)", Math.round((double) millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                finish();
            }
        };
    }


    public void start() {
        setEnabled(false);
        if (downTimer != null) {
            downTimer.cancel();
        }
        createTimer();
        downTimer.start();
    }

    private void finish() {
        setText("获取验证码");
        setEnabled(true);
    }

    public void cancel() {
        if (downTimer != null) downTimer.cancel();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancel();
    }
}
