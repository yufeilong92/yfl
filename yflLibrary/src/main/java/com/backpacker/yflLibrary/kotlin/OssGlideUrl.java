package com.backpacker.yflLibrary.kotlin;

import com.bumptech.glide.load.model.GlideUrl;

/**
 * @Author : YFL  is Creating a porject in xiaole
 * @Package com.backpacker.yflLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2019/11/1 15:28
 * @Purpose :
 */
public class OssGlideUrl extends GlideUrl {

    String url;

    public OssGlideUrl(String url) {
        super(url);
        this.url = url;
    }

    @Override
    public String getCacheKey() {
        //去除Glide缓存key中的Oss token
        if (url.contains("?")) {
            return url.substring(0, url.lastIndexOf("?"));
        } else {
            return url;
        }
    }
}
