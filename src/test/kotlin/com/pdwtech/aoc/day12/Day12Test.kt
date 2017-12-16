package com.pdwtech.aoc.day12

import com.pdwtech.aoc.Aoc.readInput
import com.pdwtech.aoc.Day12.Day12
import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test

class Day12Test {

    @Test
    fun examples() {
        val input = readInput("aoc12-example")
        assertThat(Day12.part1(input)).isEqualTo(6)

    }

    @Ignore
    @Test
    fun actual() {
        val input = readInput("aoc12")
        val result = Day12.part1(input)
        println(result)

    }

    @Test
    @Ignore
    fun part2() {
        val input = readInput("aoc12")
        Day12.part2(input)
    }
}