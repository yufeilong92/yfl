package com.backpacker.yflLibrary.kotlin

import android.webkit.WebSettings
import android.webkit.WebView

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.base
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:46
 * @Purpose :网址加载工具类
 */
abstract class KotlinWebViewUtils {
    /**
     * @param webView 设置web
     * @param isStorageEnabled  是否开启本地缓存
     */
    protected fun setWebVIewSetting(webView: WebView,isStorageEnabled:Boolean) {
        val settings = webView.settings
        settings.javaScriptEnabled = true
        // 设置可以支持缩放
        settings.setSupportZoom(true)
        //加载网络数据，不读取本地
        settings.cacheMode = WebSettings.LOAD_NO_CACHE;
        // 设置出现缩放工具
        settings.builtInZoomControls = true
               //开启缓存
        settings.setDomStorageEnabled(isStorageEnabled);
        //自适应屏幕
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        //设置自适应屏幕，两者合用
        settings.useWideViewPort = true //将图片调整到适合webview的大小
        settings.loadWithOverviewMode = true // 缩放至屏幕的大小
        settings.displayZoomControls = false //隐藏原生的缩放控件
        settings.loadsImagesAutomatically = true //支持自动加载图片
        settings.defaultTextEncodingName = "utf-8"//设置编码格式
        settings.useWideViewPort = true
        settings.allowFileAccess = true
        settings.domStorageEnabled=true
        settings.pluginState = WebSettings.PluginState.ON
        settings.javaScriptCanOpenWindowsAutomatically = true
    }
}