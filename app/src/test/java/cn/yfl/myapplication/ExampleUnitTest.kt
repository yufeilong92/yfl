package cn.yfl.myapplication

import org.junit.Test

import org.junit.Assert.*
import kotlin.random.Random

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun text() {
        val list = mutableListOf<B>()
        for (index in 0..50) {
            val random = Math.random()*10
            list.add(B(index, random.toInt() ));
        }
        list.sortWith(compareBy(
            {it.age},{it.index}
        ))
        println(list)
    }

    inner class B(
        val index: Int,
        val age: Int
    )
}
