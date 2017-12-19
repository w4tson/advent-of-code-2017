package com.pdwtech.aoc.day19

import com.pdwtech.aoc.Aoc.readInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day19Test {

    val exampleInput = readInput("aoc19-example")
    val actualInput = readInput("aoc19")

    @Test
    fun example() {
        assertThat(Day19(exampleInput).walk().letters).isEqualTo("ABCDEF")
    }

    @Test
    fun part1() {
        println(Day19(actualInput).walk().letters)
    }

    @Test
    fun example2() {
        assertThat(Day19(exampleInput).walk().distance).isEqualTo(48)
    }
    
    
    @Test
    fun part2() {
        println(Day19(actualInput).walk().distance)

    }
}