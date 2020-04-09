package com.backpacker.yflLibrary.view;

import android.content.Context;
    import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.weight
 * @Description: 滑动布局
 * @author: L-BackPacker
 * @date: 2018.12.20 上午 9:09
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class WeiNestedScrollView extends NestedScrollView {

    private ISmartScrollChangedListener mSmartScrollChangedListener;
    private boolean isScrolledToTop = true;// 初始化的时候设置一下值
    private boolean isScrolledToBottom = false;
    /**
     * 定义监听接口
     */
    public interface ISmartScrollChangedListener {
        void onScrolledToBottom();

        void onScrolledToTop();
    }

    public WeiNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public WeiNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeiNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setmSmartScrollChangedListener(ISmartScrollChangedListener mSmartScrollChangedListener) {
        this.mSmartScrollChangedListener = mSmartScrollChangedListener;
    }

    private void notifyScrollChangedListeners() {
        if (isScrolledToTop) {
            if (mSmartScrollChangedListener != null) {
                mSmartScrollChangedListener.onScrolledToTop();
            }
        } else if (isScrolledToBottom) {
            if (mSmartScrollChangedListener != null) {
                mSmartScrollChangedListener.onScrolledToBottom();
            }
        }
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (android.os.Build.VERSION.SDK_INT < 9) {  // API 9及之后走onOverScrolled方法监听
            if (getScrollY() == 0) {    // 小心踩坑1: 这里不能是getScrollY() <= 0
                isScrolledToTop = true;
                isScrolledToBottom = false;
            } else if (getScrollY() + getHeight() - getPaddingTop() - getPaddingBottom() == getChildAt(0).getHeight()) {
                // 小心踩坑2: 这里不能是 >=
                // 小心踩坑3（可能忽视的细节2）：这里最容易忽视的就是ScrollView上下的padding　
                isScrolledToBottom = true;
                isScrolledToTop = false;
            } else {
                isScrolledToTop = false;
                isScrolledToBottom = false;
            }
            notifyScrollChangedListeners();
        }
        // 有时候写代码习惯了，为了兼容一些边界奇葩情况，上面的代码就会写成<=,>=的情况，结果就出bug了
        // 我写的时候写成这样：getScrollY() + getHeight() >= getChildAt(0).getHeight()
        // 结果发现快滑动到底部但是还没到时，会发现上面的条件成立了，导致判断错误
        // 原因：getScrollY()值不是绝对靠谱的，它会超过边界值，但是它自己会恢复正确，导致上面的计算条件不成立
        // 仔细想想也感觉想得通，系统的ScrollView在处理滚动的时候动态计算那个scrollY的时候也会出现超过边界再修正的情况
    }
    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (scrollY == 0) {
            isScrolledToTop = clampedY;
            isScrolledToBottom = false;
        } else {
            isScrolledToTop = false;
            isScrolledToBottom = clampedY;
        }
        notifyScrollChangedListeners();
    }
}
