package com.pdwtech.aoc.day18

import com.pdwtech.aoc.Aoc.readInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class Day18Test {

    val exampleInput = readInput("aoc18-example")
    val actualInput = readInput("aoc18")

    @Test
    fun examplePart1() {
        assertThat(Machine(exampleInput).run()).isEqualTo(4)
    }

    @Test
    fun part1() {
        println(Machine(actualInput).run())
    }
}