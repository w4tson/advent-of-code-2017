package com.pdwtech.aoc.day18

import com.pdwtech.aoc.Aoc.readInput
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
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
    fun example() {
        duet(exampleInput2)
    }

    @Test
    fun part1() {
        duet(actualInput)
    }  

    fun duet(input: List<String>) {
        runBlocking {
            val program0 = Channel<Long>(1000)
            val program1 = Channel<Long>(1000)

            val send0Count = launch { Program(input, 0L, program0, program1).run() }
            val send1Count = launch { Program(input, 1L, program1, program0).run() }
            send0Count.join()
            send1Count.join()
        }
    }


}