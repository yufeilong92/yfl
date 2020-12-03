package com.backpacker.yflLibrary.view.toast

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.backpacker.yflLibrary.view.toast.style.ToastAliPayStyle

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.backpacker.yflLibrary.view.toast
 * @Email : yufeilong92@163.com
 * @Time :2020/12/3 14:00
 * @Purpose :
 */
/*

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ToastUtils.init(this, ToastAliPayStyle(this))
//        ToastUtils.init(this, ToastBlackStyle(this))
//        ToastUtils.init(this, ToastQQStyle(this))

        // 设置 Toast 拦截器

        // 设置 Toast 拦截器
        ToastUtils.setToastInterceptor(object : ToastInterceptor() {
            override fun intercept(toast: Toast, text: CharSequence): Boolean {
                val intercept = super.intercept(toast, text)
                if (intercept) {
                    Log.e("Toast", "空 Toast")
                } else {
                    Log.i("Toast", text.toString())
                }
                return intercept
            }
        })

    }


    MainActivity : AppCompatActivity() ,ToastAction{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_content.setOnClickListener {
           toast("222222")
        }
    }
}*/
