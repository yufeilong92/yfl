package com.backpacker.yflLibrary;

import com.backpacker.yflLibrary.kotlin.KotlinListUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void text() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        ArrayList<String> data = new ArrayList<>();
        data.add("2");
        data.add("4");
  /*      KotlinListUtil.INSTANCE.mergeLists(list, data);
        System.out.println(list);
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(1);
        ints.add(3);
        ints.add(14);
        List<Integer> strings = KotlinListUtil.INSTANCE.goDistinctLists(ints);
        System.out.println(strings);*/

        System.out.println("并集"+KotlinListUtil.INSTANCE.unionList(list,data));
        System.out.println("补集"+KotlinListUtil.INSTANCE.minusList(list,data));
        System.out.println("交集"+KotlinListUtil.INSTANCE.intersectList(list,data));
        System.out.println("差集"+KotlinListUtil.INSTANCE.subtractList(list,data));
    }
}