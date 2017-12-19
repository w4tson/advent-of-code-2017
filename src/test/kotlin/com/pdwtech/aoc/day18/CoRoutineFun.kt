package com.pdwtech.aoc.day18

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test


class CoRoutineFun {


    @Test
    fun channels() {
        val channel = Channel<Int>()
        runBlocking {
            launch {
                // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
                for (x in 1..5) channel.send(x * x)
            }
            // here we print five received integers:
            repeat(5) { println(channel.receive()) }
            println("Done!")
        }
    }
}