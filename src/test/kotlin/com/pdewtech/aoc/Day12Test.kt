package com.pdewtech.aoc

import com.pdwtech.aoc.Aoc.readInput
import com.pdwtech.aoc.Day12.Day12
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day12Test {

    @Test
    fun examples() {
        val input = readInput("aoc12-example")
        assertThat(Day12.part1(input)).isEqualTo(6)

    }

    @Test
    fun actual() {
        val input = readInput("aoc12-example")
        val result = Day12.part1(input)
        println(result)

    }
}