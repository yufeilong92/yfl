package com.backpacker.yflLibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.UtilsLibrary.R;

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.example.myapplication
 * @Email : yufeilong92@163.com
 * @Time :2019/11/28 11:19
 * @Purpose :数据
 */
public class GmTitleBarView extends LinearLayout {
    private TextView mGmTitleContent;
    private TextView mGmTitleTvRight;
    private ImageView mGmTitleIvRight;
    private RelativeLayout mRlBg;
    private ImageView mGmTitleIvLift;
    private TextView mGmTitleTvLift;
    private MyBarView mMyBarView;


    public GmTitleBarView(Context context) {
        super(context);
    }

    public GmTitleBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GmTitleBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.gm_title_bar, this);
        mGmTitleContent = (TextView) findViewById(R.id.gm_title_content);
        mGmTitleIvLift = (ImageView) findViewById(R.id.gm_title_iv_lift);
        mGmTitleTvLift = (TextView) findViewById(R.id.gm_title_tv_lift);
        mGmTitleIvRight = (ImageView) findViewById(R.id.gm_title_iv_right);
        mGmTitleTvRight = (TextView) findViewById(R.id.gm_title_tv_right);
        mMyBarView = (MyBarView) findViewById(R.id.my_bar_view);
        mRlBg = (RelativeLayout) findViewById(R.id.rl_bg);

        TypedArray titleBar = context.obtainStyledAttributes(attrs, R.styleable.GmTitleBarView);
//          左侧
        int l_color = titleBar.getColor(R.styleable.GmTitleBarView_lift_color, Color.BLACK);
        int l_colorBg = titleBar.getColor(R.styleable.GmTitleBarView_lift_bg, Color.TRANSPARENT);
        String l_string = titleBar.getString(R.styleable.GmTitleBarView_lift_content);
        float l_dimension = titleBar.getDimension(R.styleable.GmTitleBarView_lift_size, 13.0f);
        int l_ResourceId = titleBar.getResourceId(R.styleable.GmTitleBarView_lift_resource, -1);
        mGmTitleTvLift.setTextColor(l_color);
        if (TextUtils.isEmpty(l_string))
            mGmTitleTvLift.setVisibility(GONE);
        else {
            mGmTitleTvLift.setVisibility(VISIBLE);
            mGmTitleTvLift.setText(l_string);
        }
        mGmTitleTvLift.setTextSize(px2sp(context, l_dimension));
        mGmTitleTvLift.setBackgroundColor(l_colorBg);
        if (l_ResourceId != -1)
            mGmTitleTvLift.setBackgroundResource(l_ResourceId);

//        中间
        int color = titleBar.getColor(R.styleable.GmTitleBarView_content_color, Color.BLACK);
        int colorBg = titleBar.getColor(R.styleable.GmTitleBarView_content_bg, Color.TRANSPARENT);
        String string = titleBar.getString(R.styleable.GmTitleBarView_content_content);
        float dimension = titleBar.getDimension(R.styleable.GmTitleBarView_content_size, 13.0f);
        int resourceId = titleBar.getResourceId(R.styleable.GmTitleBarView_content_resource, -1);
        mGmTitleContent.setTextColor(color);
        if (TextUtils.isEmpty(string)) {
            mGmTitleContent.setVisibility(GONE);
        } else {
            mGmTitleContent.setVisibility(VISIBLE);
            mGmTitleContent.setText(string);
        }
        mGmTitleContent.setTextSize(px2sp(context, dimension));
        mGmTitleContent.setBackgroundColor(colorBg);
        if (resourceId != -1)
            mGmTitleContent.setBackgroundResource(resourceId);

//        右侧
        int r_Color = titleBar.getColor(R.styleable.GmTitleBarView_right_color, Color.BLACK);
        int r_Color_BG = titleBar.getColor(R.styleable.GmTitleBarView_right_bg, Color.TRANSPARENT);
        String r_String = titleBar.getString(R.styleable.GmTitleBarView_right_content);
        float r_Dimension = titleBar.getDimension(R.styleable.GmTitleBarView_right_size, 13.0f);
        int r_resourceId1 = titleBar.getResourceId(R.styleable.GmTitleBarView_right_resource, -1);
        mGmTitleTvRight.setTextColor(r_Color);
        if (TextUtils.isEmpty(r_String)) {
            mGmTitleTvRight.setVisibility(GONE);
        } else {
            mGmTitleTvRight.setVisibility(VISIBLE);
            mGmTitleTvRight.setText(r_String);
        }
        mGmTitleTvRight.setTextSize(px2sp(context, r_Dimension));
        mGmTitleTvRight.setBackgroundColor(r_Color_BG);
        if (r_resourceId1 != -1) {
            mGmTitleTvRight.setBackgroundResource(r_resourceId1);
        }

        //   右侧背景
        int ic_Rbg = titleBar.getResourceId(R.styleable.GmTitleBarView_ic_rigth_resource, -1);
        if (ic_Rbg != -1)
            mGmTitleIvRight.setImageResource(ic_Rbg);
        else
            mGmTitleIvRight.setVisibility(GONE);

        //左侧背景
        int ic_bg = titleBar.getResourceId(R.styleable.GmTitleBarView_ic_life_resource, -1);
        if (ic_bg != -1)
            mGmTitleIvLift.setImageResource(ic_bg);
        else
            mGmTitleIvLift.setVisibility(GONE);

        boolean life = titleBar.getBoolean(R.styleable.GmTitleBarView_tv_life_boolean, false);
        mGmTitleTvLift.setVisibility(life ? VISIBLE : GONE);
        mGmTitleIvLift.setVisibility(!life ? VISIBLE : GONE);

        boolean right = titleBar.getBoolean(R.styleable.GmTitleBarView_tv_right_boolean, false);
        mGmTitleTvRight.setVisibility(right ? VISIBLE : GONE);
        mGmTitleIvRight.setVisibility(!right ? VISIBLE : GONE);

        int appBg = titleBar.getColor(R.styleable.GmTitleBarView_app_bar_bg, Color.TRANSPARENT);
        int appResource = titleBar.getResourceId(R.styleable.GmTitleBarView_app_bar_resource, -1);
        mMyBarView.setBackgroundColor(appBg);
        if (appResource != -1)
            mMyBarView.setBackgroundResource(appResource);

        int appTitleBg = titleBar.getColor(R.styleable.GmTitleBarView_app_title_bar_bg, Color.TRANSPARENT);
        int appTitleResource = titleBar.getResourceId(R.styleable.GmTitleBarView_app_title_bar_resource, -1);
        mRlBg.setBackgroundColor(appTitleBg);
        if (appTitleResource != -1)
            mRlBg.setBackgroundResource(appTitleResource);

        mGmTitleIvLift.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onLiftClickItem();
                }
            }
        });
        mGmTitleTvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    String right = mGmTitleTvRight.getText().toString();
                    onItemClickListener.onRightClickItem(right);
                }
            }
        });
        mGmTitleContent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    String right = mGmTitleContent.getText().toString();
                    onItemClickListener.onContentClickItem(right);
                }
            }
        });

    }

    private int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 设置监听
     */
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void onContentClickItem(String o);

        public void onRightClickItem(String o);

        public void onLiftClickItem();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
