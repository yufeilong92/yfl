package com.backpacker.yflLibrary.java;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.backpacker.yflLibrary.java
 * @Email : yufeilong92@163.com
 * @Time :2020/7/20 11:44
 * @Purpose :集合工具类
 */
class JavaListUtil {
    /**
     * 去重
     *
     * @param one 要去重集合
     * @param <T> 类型
     * @return
     */
    public static <T> List<T> goHeavyLists(ArrayList<T> one) {
        if (null == one || one.isEmpty()) return one;
        TreeSet<T> set = new TreeSet<>(one);
        List<T> result = new ArrayList<>();
        result.addAll(set);
        return result;
    }

    /**
     * 并集
     * @param one 集合one
     * @param two 集合two
     * @param <T> 类型
     */
    public static <T> void mergeLists(ArrayList<T> one, ArrayList<T> two) {
        one.removeAll(two);
        one.addAll(two);
       
    }
    
    
    

}
