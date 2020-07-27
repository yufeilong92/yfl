package com.backpacker.yflLibrary.kotlin

import android.content.Context
import java.io.*


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
object KotlinFileUtil {
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
    /***
     * @param con  保存log 日志
     * @param com  保存内容
     * @return
     */
    fun saveLog(con: Context, com: String) {
        var dir=con.externalCacheDir
        if (null==dir)
            dir=con.cacheDir

        val path = "${dir!!.absolutePath+File.separator}log.txt"
        val file = File(path)
        var raf: RandomAccessFile? = null
        try {
            raf = RandomAccessFile(file, "rw")
            raf.seek(file.length())
            raf.write(com.toByteArray())
            raf.close()

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
    /***
     * 保存数据
     * @param m 上下文
     * @param fileName 文件名称
     * @param str 内容
     * @return
     */
    fun saveFile(m: Context,fileName:String,str: String) {
        try {
            val output =m.openFileOutput(fileName, Context.MODE_APPEND)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(str)
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
    /***
     * 读取数据
     * @param m  上下文
     * @param fileName  文件名称
     * @return
     */
    fun loadFile(m: Context,fileName:String):String{
        val com= java.lang.StringBuilder()
        try {
            val input=m.openFileInput(fileName)
            val reader=BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    com.append(it)
                }
            }
        }catch (e : java.lang.Exception){
            e.printStackTrace()
        }
        return com.toString()
    }
}