package com.backpacker.yflLibrary.kotlin

import java.util.*

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.backpacker.yflLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2020/7/20 11:17
 * @Purpose :集合工具类
 */
object KotlinListUtil {
    /***
     * 去重
     * @param list 要去重集合
     * @return 返回去重集合
     */
    fun <T> goHeavyLists(list: MutableList<T>?): MutableList<T>? {
        if (list.isNullOrEmpty()) return list
        val set = TreeSet(list)
        val result = mutableListOf<T>()
        result.addAll(set)
        return result
    }

    /***
     * 去重
     * @param list 要去重集合
     * @return 返回去重集合
     */
    fun <T> goDistinctLists(list: MutableList<T>?): MutableList<T>? {
        if (list.isNullOrEmpty()) return list
        val distinct = list.distinct()
        return distinct as MutableList<T>
    }

    /***
     * 并集
     * @param arry 原数据(母)
     * @param list 要合并集合(子)
     * @return
     */
    fun <T> mergeLists(arry: MutableList<T>, list: MutableList<T>) {
        arry.removeAll(list)
        arry.addAll(list)
    }

    /***
     * 差集
     * @param listOne
     * @param listTwo
     * @return
     */
    fun <T> subtractList(listOne: MutableList<T>, listTwo: MutableList<T>): MutableList<T>? {
        if (listOne.isNullOrEmpty() && listTwo.isNullOrEmpty())
            return mutableListOf()
        if (listOne.isNullOrEmpty())
            return listTwo
        if (listTwo.isNullOrEmpty())
            return listOne
        val subtract = listOne.subtract(listTwo)
        if (subtract.isNullOrEmpty()) return mutableListOf()
        val list = mutableListOf<T>()
        subtract.forEach {
            list.add(it)
        }
        return list
    }

    /***
     * 交集
     * @param listOne
     * @param listTwo
     * @return
     */
    fun <T> intersectList(listOne:MutableList<T> ,listTwo:MutableList<T>):MutableList<T>?{
        if (listOne.isNullOrEmpty() && listTwo.isNullOrEmpty())
            return mutableListOf()
        if (listOne.isNullOrEmpty())
            return listTwo
        if (listTwo.isNullOrEmpty())
            return listOne
        val intersect = listOne.intersect(listTwo)
        if (intersect.isNullOrEmpty()) return mutableListOf()
        val list = mutableListOf<T>()
        intersect.forEach {
            list.add(it)
        }
        return list
    }
    /***
     * 并集
     * @param listOne
     * @param listTwo
     * @return
     */
    fun <T> unionList(listOne:MutableList<T> ,listTwo:MutableList<T>):MutableList<T>?{
        if (listOne.isNullOrEmpty() && listTwo.isNullOrEmpty())
            return mutableListOf()
        if (listOne.isNullOrEmpty())
            return listTwo
        if (listTwo.isNullOrEmpty())
            return listOne
        val intersect = listOne.union(listTwo)
        if (intersect.isNullOrEmpty()) return mutableListOf()
        val list = mutableListOf<T>()
        intersect.forEach {
            list.add(it)
        }
        return list
    }
    /***
     * 补集
     * @param listOne
     * @param listTwo
     * @return
     */
    fun <T> minusList(listOne:MutableList<T> ,listTwo:MutableList<T>):MutableList<T>?{
        if (listOne.isNullOrEmpty() && listTwo.isNullOrEmpty())
            return mutableListOf()
        if (listOne.isNullOrEmpty())
            return listTwo
        if (listTwo.isNullOrEmpty())
            return listOne
        val intersect = listOne.minus(listTwo)
        if (intersect.isNullOrEmpty()) return mutableListOf()
        val list = mutableListOf<T>()
        intersect.forEach {
            list.add(it)
        }
        return list
    }
/*    ArrayList<String> list = new ArrayList<>();
    list.add("1");
    list.add("2");
    list.add("3");
    ArrayList<String> data = new ArrayList<>();
    data.add("2");
    data.add("4");
    并集[1, 2, 3, 4]
    补集[1, 3]
    交集[2]
    差集[1, 3]*/

}