package com.backpacker.yflLibrary.log

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.backpacker.yflLibrary.log
 * @Email : yufeilong92@163.com
 * @Time :2020/5/30 11:19
 * @Purpose :
 */
class demo {
    /*<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
   >

    <Button
        android:layout_width="match_parent"
        android:text="打开定时器打印log"
        android:onClick="onTimer"
        android:layout_height="wrap_content" />

    <Button
        android:layout_width="match_parent"
        android:text="关闭定时器打印log"
        android:onClick="onTimeroff"
        android:layout_height="wrap_content" />

    <Button
        android:layout_width="match_parent"
        android:text="open logcat"
        android:onClick="onTest"
        android:layout_height="wrap_content" />

    <Button
        android:layout_width="match_parent"
        android:text="open logcat自定义标题 "
        android:onClick="onTest2"
        android:layout_height="wrap_content" />

    <Button
        android:layout_width="match_parent"
        android:text="open logcat自定义搜索内容"
        android:onClick="onTest3"
        android:layout_height="wrap_content" />

    <Button
        android:layout_width="match_parent"
        android:text="open logcat自定义目标tag"
        android:onClick="onTest4"
        android:layout_height="wrap_content" />
    <Button
        android:layout_width="match_parent"
        android:text="open logcat自定义log级别"
        android:onClick="onTest5"
        android:layout_height="wrap_content" />

    <Button
        android:layout_width="match_parent"
        android:text="清除LogCatDialog"
        android:onClick="onClear"
        android:layout_height="wrap_content" />

</LinearLayout>

       private val TAG = "MainActivity"
    private var timer: Timer? = null
    private var task: TimerTask? = null
    private var isStop = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * 默认
     *
     * @param view
     */
    fun onTest(view: View?) {
        LogCatControl.getBuilder(this).clear()
        LogCatControl.getBuilder(this).show()
    }


    /**
     * 自定义标题
     *
     * @param view
     */
    fun onTest2(view: View?) {
        LogCatControl.getBuilder(this).clear()
        LogCatControl.getBuilder(this).setTitle("test").show()
    }


    /**
     * 自定义搜索内容
     *
     * @param view
     */
    fun onTest3(view: View?) {
        LogCatControl.getBuilder(this).clear()
        LogCatControl.getBuilder(this).setSearchContent("search").show()
    }


    /**
     * 自定义tag
     *
     * @param view
     */
    fun onTest4(view: View?) {
        LogCatControl.getBuilder(this).clear()
        LogCatControl.getBuilder(this).setSearchTag("MainActivity").show()
    }


    /**
     * 自定义log级别
     *
     * @param view
     */
    fun onTest5(view: View?) {
        LogCatControl.getBuilder(this).clear()
        LogCatControl.getBuilder(this).setShowGrade(3).show()
    }

    /**
     * 清除dialog
     *
     * @param view
     */
    fun onClear(view: View?) {
        LogCatControl.getBuilder(this).clear()
    }

    /**
     * 启动定时器
     *
     * @param view
     */
    fun onTimer(view: View?) {
        startTimer()
    }

    /**
     * 停止定时器
     *
     * @param view
     */
    fun onTimeroff(view: View?) {
        stopTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
        LogCatControl.getBuilder(this).clear()
    }

    private fun startTimer() {
        if (isStop) {
            timer = Timer()
            task = object : TimerTask() {
                override fun run() {
                    Log.i(
                        TAG,
                        "log i " + System.currentTimeMillis()
                    )
                    Log.d(
                        TAG,
                        "log d " + System.currentTimeMillis()
                    )
                    Log.v(
                        TAG,
                        "log v " + System.currentTimeMillis()
                    )
                    Log.w(
                        TAG,
                        "log w " + System.currentTimeMillis()
                    )
                    Log.wtf(
                        TAG,
                        "log wtf " + System.currentTimeMillis()
                    )
                    Log.e(
                        TAG,
                        "log e " + System.currentTimeMillis()
                    )
                }
            }
            timer!!.schedule(task, 0, 2000)
            isStop = false
        }
    }

    private fun stopTimer() {
        if (!isStop) {
            task!!.cancel()
            timer!!.cancel()
            task = null
            timer = null
            isStop = true
        }
    }
    */
}