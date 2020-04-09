package com.backpacker.yflLibrary.kotlin

import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/3/31 23:07
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object FileUtil {
    /**
     * 删除文件
     * @param path
     * @return
     */
    fun delectFile(path: File): Boolean {
        if (path.exists()) {
            if (path.isFile) {
                path.delete()//如果是文件，直接删除
            } else if (path.isDirectory) {
                val files = path.listFiles()
                for (i in files.indices) {
                    files[i].delete()
                }
            }
            path.delete()
            return true
        }
        return false
    }


    fun getFromAssets(m: Context, str: String): String {
        try {
            var resul: StringBuffer = StringBuffer()
            val open = m.resources.assets.open(str)
            val inputRead: InputStreamReader = InputStreamReader(open)
            val bufReader = BufferedReader(inputRead)
            var line: String = ""
            while ((bufReader.readLine().also { line = it }) != null) {
                resul.append(line)
            }
            open.close()
            inputRead.close()
            return resul.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }

    fun getFormAssets(context: Context, fileName: String): String? {
        val stringBuilder = StringBuilder()
        BufferedReader(InputStreamReader(context.assets.open(fileName), "UTF-8")).run {
            var line: String? = ""
            do {
                line = readLine()
                if (line != null) {
                    stringBuilder.append(line)
                } else {
                    break
                    close()
                }
            } while (true)
            return stringBuilder.toString()
        }
        return null
    }
}