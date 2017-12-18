package com.pdwtech.aoc.day18

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import kotlin.coroutines.experimental.buildSequence
import kotlin.coroutines.experimental.*


class CoRoutineFun {

    val fibonacciSeq = buildSequence {
        var a = 0
        var b = 1

        yield(1)

        while (true) {
            yield(a + b)

            val tmp = a + b
            a = b
            b = tmp
        }
    }

    @Test
    fun name() {
        runBlocking {
            val s = listOf(7, 2, 8, -9, 4, 0)
            val c = Channel<Int>()
            launch(CommonPool) { sum(s.subList(s.size / 2, s.size), c) }
            launch(CommonPool) { sum(s.subList(0, s.size / 2), c) }
            val x = c.receive()
            val y = c.receive()
            println("$x $y ${x + y}")
        }
    }

suspend fun sum(s: List<Int>, c: SendChannel<Int>) {
    var sum = 0
    for (v in s) {
        sum += v
    }
    c.send(sum)
}

    @Test
    fun givenAsyncCoroutine_whenStartIt_thenShouldExecuteItInTheAsyncWay() {
        // given
        val res = mutableListOf<String>()

        // when
            runBlocking {
                val promise = launch(CommonPool) {
                    expensiveComputation(res)
                }
            res.add("Hello,")
            promise.join()
        }

        // then
        assertEquals(res, listOf("Hello,", "word!"))
    }



    suspend fun expensiveComputation(res: MutableList<String>) {
        delay(1000L)
        res.add("word!")
    }


    suspend fun say(s: String) {
        for (i in 0..4) {
            delay(100)
            println(s)
        }
    }

}