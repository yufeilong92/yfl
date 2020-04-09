package com.backpacker.yflLibrary.kotlin

import android.util.Base64
import java.io.*

/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    保存加密
 * @author: L-BackPacker
 * @date:   2019/3/31 23:54
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object SerializableUtil {
    /**
     * @param list 要保存list数据
     * @param <E>
     * @return
     * @throws IOException
    </E> */
    @Throws(IOException::class)
    fun <E> list2String(list: List<E>): String {
        //实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件
        val baos = ByteArrayOutputStream()
        //然后将得到的字符数据装载到ObjectOutputStream
        val oos = ObjectOutputStream(baos)
        //writeObject 方法负责写入特定类的对象的状态，以便相应的readObject可以还原它
        oos.writeObject(list)
        //最后，用Base64.encode将字节文件转换成Base64编码，并以String形式保存
        val listString = String(Base64.encode(baos.toByteArray(), Base64.DEFAULT))
        //关闭oos
        oos.close()
        return listString
    }

    /**
     * @param obj 要保存的任意类型的
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    fun obj2Str(obj: Any?): String {
        if (obj == null) {
            return ""
        }
        //实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件
        val baos = ByteArrayOutputStream()
        //然后将得到的字符数据装载到ObjectOutputStream
        val oos = ObjectOutputStream(baos)
        //writeObject 方法负责写入特定类的对象的状态，以便相应的readObject可以还原它
        oos.writeObject(obj)
        //最后，用Base64.encode将字节文件转换成Base64编码，并以String形式保存
        val listString = String(Base64.encode(baos.toByteArray(), Base64.DEFAULT))
        //关闭oos
        oos.close()
        return listString
    }


    /**
     * @param str 将保存的任意数据还原成Object
     * @return
     * @throws StreamCorruptedException
     * @throws IOException
     */ //将序列化的数据还原成Object
    @Throws(StreamCorruptedException::class, IOException::class)
    fun str2Obj(str: String): Any? {
        val mByte = Base64.decode(str.toByteArray(), Base64.DEFAULT)
        val bais = ByteArrayInputStream(mByte)
        val ois: ObjectInputStream
        if (bais.available() !== 0)
            ois = ObjectInputStream(bais)
        else
            return null
        try {
            return ois.readObject()
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return null
    }

    /**
     * @param str 将保存的序列化的list还原成list
     * @param <E>
     * @return
     * @throws StreamCorruptedException
     * @throws IOException
    </E> */
    @Throws(StreamCorruptedException::class, IOException::class)
    fun <E> string2List(str: String): List<E>? {
        val mByte = Base64.decode(str.toByteArray(), Base64.DEFAULT)
        val bais = ByteArrayInputStream(mByte)
        val ois = ObjectInputStream(bais)
        var stringList: List<E>? = null
        try {
            stringList = ois.readObject() as List<E>?
        } catch (e: ClassNotFoundException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return stringList
    }
}