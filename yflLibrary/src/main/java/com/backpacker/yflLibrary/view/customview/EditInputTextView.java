package com.backpacker.yflLibrary.view.customview;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.UtilsLibrary.R;

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.example.myapplication
 * @Email : yufeilong92@163.com
 * @Time :2019/11/28 10:43
 * @Purpose :输入框
 */
public class EditInputTextView extends LinearLayout implements TextWatcher, View.OnClickListener {
    private EditText mEtInputTextview;
    private CheckBox mCbGmInputSelect;

    /**
     * 设置监听
     */
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void onClickItem(Object o);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public EditInputTextView(Context context) {
        super(context);
        init(context);
    }

    public EditInputTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EditInputTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context mContext) {
        LayoutInflater.from(mContext).inflate(R.layout.gm_input_textview, this, true);
        mEtInputTextview = (EditText) findViewById(R.id.et_input_textview);
        mCbGmInputSelect = (CheckBox) findViewById(R.id.cb_gm_input_select);
        mCbGmInputSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showPassWord(isChecked, mEtInputTextview);
            }
        });
        mEtInputTextview.addTextChangedListener(this);
        mEtInputTextview.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                if ((keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_MEDIA_NEXT)) {
                    switch (keyEvent.getAction()) {
                        case KeyEvent.ACTION_UP:
                            if (onItemClickListener != null) {
                                String com = mEtInputTextview.getText().toString();
                                onItemClickListener.onClickItem(com);
                            }
                            return true;
                        default:
                            return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {

    }


    /***
     * @param show 是否显示密码
     * @param et 输入框
     * @return
     */
    private void showPassWord(Boolean show, EditText et) {
        String s = et.getText().toString();
        if (show) {
            et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            et.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        if (!TextUtils.isEmpty(s)) {
            et.setSelection(s.length());
        }
    }
}
