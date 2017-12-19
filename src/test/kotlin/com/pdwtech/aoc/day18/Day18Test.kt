package com.pdwtech.aoc.day18

import com.pdwtech.aoc.Aoc.readInput
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.withTimeout
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class Day18Test {

    val exampleInput = readInput("aoc18-example")
    val exampleInput2 = readInput("aoc18-example2")
    val actualInput = readInput("aoc18")

    @Test
    fun examplePart1() {
//        assertThat(Program(exampleInput).run()).isEqualTo(4)
    }

    @Test
    fun part1() {
        runBlocking {
                val program0 = Channel<Long>(1000)
                val program1 = Channel<Long>(1000)

//                channel.send(1L)
                val j1 = async(coroutineContext) { Program(actualInput, 0L, program0, program1).run() }
                val j2 = async(coroutineContext) { Program(actualInput, 1L, program1, program0).run() }
//                j1.await()
//                j2.join()

        }
    }

    @Test
    fun exampe() {
        runBlocking {
            val program0 = Channel<Long>(100)
            val program1 = Channel<Long>(100)

            val j1 = async(coroutineContext) { Program(exampleInput2, 0L, program0, program1).run() }
            val j2 = async(coroutineContext) { Program(exampleInput2, 1L, program1, program0).run() }

        }
    }
}