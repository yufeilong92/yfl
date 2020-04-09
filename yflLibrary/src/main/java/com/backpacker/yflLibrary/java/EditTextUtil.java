package com.backpacker.yflLibrary.java;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class EditTextUtil {
    public interface SearchInterface {
        void searchOnClick(String com);
    }

    public static void etActionSearch(final EditText et, final SearchInterface anInterface) {
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (anInterface != null) {
                        String com = JavaStringUtil.getObjectToStr(et);
                        anInterface.searchOnClick(com);
                    }
                    return true;
                }
                return false;
            }
        });
    }
    public static void etActionDONE(final EditText et, final SearchInterface anInterface) {
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (anInterface != null) {
                        String com = JavaStringUtil.getObjectToStr(et);
                        anInterface.searchOnClick(com);
                    }
                    return true;
                }
                return false;
            }
        });
    }
    public static void etActionSend(final EditText et, final SearchInterface anInterface) {
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (anInterface != null) {
                        String com = JavaStringUtil.getObjectToStr(et);
                        anInterface.searchOnClick(com);
                    }
                    return true;
                }
                return false;
            }
        });
    }
    public static void etActionGo(final EditText et, final SearchInterface anInterface) {
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    if (anInterface != null) {
                        String com = JavaStringUtil.getObjectToStr(et);
                        anInterface.searchOnClick(com);
                    }
                    return true;
                }
                return false;
            }
        });
    }
    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(final Activity activity, final EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) activity
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText, 0);
            }
        }, 300);
    }
    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(final Activity activity, final View editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) activity
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText, 0);
            }
        }, 300);
    }

}
