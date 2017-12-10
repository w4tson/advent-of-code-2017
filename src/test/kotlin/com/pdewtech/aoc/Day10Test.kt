package com.pdewtech.aoc

import com.pdwtech.aoc.day10.Day10.solvePuzzle
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day10Test {


    @Test
    fun example() {
        val input = arrayOf(3, 4, 1, 5)
        val result = solvePuzzle(input, 5)
        assertThat(result).isEqualTo(12)
    }

    @Test
    fun actual() {
        val input = arrayOf(227,169,3,166,246,201,0,47,1,255,2,254,96,3,97,144)
        val result = solvePuzzle(input)
        println(result)
    }
}