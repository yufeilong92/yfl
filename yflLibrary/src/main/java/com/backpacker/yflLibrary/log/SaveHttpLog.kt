package com.backpacker.yflLibrary.log

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Message
import android.text.Html
import android.text.TextUtils
import java.io.*
import java.util.*

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.liang.myapplication.log
 * @Email : yufeilong92@163.com
 * @Time :2020/6/2 10:06
 * @Purpose :请求log 可以单独使用
 */
class SaveHttpLog {
    private var mContext: Context? = null

    private var searchContent = ""

    /*显示级别，0 所有，1 系统，2 警告,3 错误*/
    private val showGrade = 0
    private val WHAT_NEXT_LOG = 778
    private val WHAT_STATUS_LOG = 779
    var searchTag = "" //过滤tag

    private var isRuning = true
    private val contentList: MutableList<String> =
        ArrayList()

    private val mLog = StringBuffer()

    @SuppressLint("HandlerLeak")
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                WHAT_NEXT_LOG -> {
                    val line = msg.obj as String
                    if (!TextUtils.isEmpty(searchTag)) {
                        if (line.contains(searchTag)) {
                            if (!TextUtils.isEmpty(searchContent)) {
                                if (line.contains(searchContent)) { //同时搜索
                                    contentList.add(line)
                                    append(line)
                                }
                            } else {
                                contentList.add(line) //只搜索tag
                                append(line)
                            }
                        }
                    } else {
                        if (!TextUtils.isEmpty(searchContent)) {
                            if (line.contains(searchContent)) { //只搜索内容
                                contentList.add(line)
                                append(line)
                            }
                        } else {
                            contentList.add(line) //所有
                            append(line)
                        }
                    }

//                    saveHttp(mLog.toString())
                }
                WHAT_STATUS_LOG ->       {
                    //todo 保存log
                    saveLog(mLog.toString())
                }

            }
        }
    }

    fun init(cn:Context) {
        this.mContext=cn
        Thread(Runnable {
            var logcatProcess: Process? = null
            var bufferedReader: BufferedReader? = null
            val log = StringBuilder()
            var line: String?
            try {
                while (isRuning) {
                    logcatProcess = Runtime.getRuntime().exec("logcat")
                    bufferedReader =
                        BufferedReader(InputStreamReader(logcatProcess.inputStream))
                    while (bufferedReader.readLine().also { line = it } != null) {
                        log.append(line)
                        val message = mHandler.obtainMessage()
                        message.what = WHAT_NEXT_LOG
                        message.obj = line
                        mHandler.sendMessage(message)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }).start()
    }

    /**
     * 追加内容
     *
     * @param line
     */
    private fun append(line: String) {
        if (showGrade == 0 || showGrade == 1) {
            if (line.contains(" E ")) {
                mLog.append("\n\n")
                showError(line)
            } else if (line.contains(" W ")) {
                mLog.append("\n\n")
                showWarning(line)
            } else {
                mLog.append(
                    """
                        $line
                        """.trimIndent()
                )
            }
        } else if (showGrade == 2) {
            if (line.contains(" W ")) {
                mLog.append("\n\n")
                showWarning(line)
            }
        } else if (showGrade == 3) {
            if (line.contains(" E ")) {
                mLog.append("\n\n")
                showError(line)
            }
        }
        mHandler.sendEmptyMessageDelayed(WHAT_STATUS_LOG, 2000)
    }

    /**
     * 显示警告级别的日志
     *
     * @param line
     */
    private fun showWarning(line: String) {
        showLine(line, "#ba8a27")
    }

    /**
     * 显示错误级别的信息
     *
     * @param line
     */
    private fun showError(line: String) {
        showLine(line, "red")
    }

    private fun showLine(line: String, color: String) {
        if (line.contains("http://") || line.contains("https://")) {
            val url = line.substring(line.indexOf("http"))
            mLog.append(
                Html.fromHtml(
                    "<font color='$color'>" + line.substring(
                        0,
                        line.indexOf("http")
                    ) + "</font>"
                )
            )
            mLog.append(Html.fromHtml("<a href='$url'>$url</a>"))
        } else {
            mLog.append(Html.fromHtml("<font color='$color'>$line</font>"))
        }
    }

    /**
     * 关闭任务
     */
    fun closeTask() {
        isRuning = false
    }

    /**
     * 搜索内容
     *
     * @param content
     */
    private fun searchContent(content: String) {
        searchContent = content
        mLog.delete(0, mLog.length)
        for (item in contentList) {
            if (item.contains(content)) {
                append(item)
            }
        }
    }

    private fun saveLog(com: String) {
        mLog.delete(0,com.length)
        var dir = mContext!!.externalCacheDir
        if (null == dir)
            dir = mContext!!.cacheDir
        val path="${dir!!.absolutePath+File.separator}log.txt"
        val file=File(path)
        var raf:RandomAccessFile?=null
        try {
            raf=RandomAccessFile(file,"rw")
            raf.seek(file.length())
            raf.write(com.toByteArray())
            raf.close()

        }catch (e:java.lang.Exception){

        }
    }
}