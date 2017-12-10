package com.pdewtech.aoc

import com.pdwtech.aoc.day10.Day10.part1
import com.pdwtech.aoc.day10.Day10.part2
import com.pdwtech.aoc.day10.Day10.toHex
import com.pdwtech.aoc.day10.Day10.xor
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day10Test {

    val input = arrayOf(227,169,3,166,246,201,0,47,1,255,2,254,96,3,97,144)

    @Test
    fun example() {
        val input = arrayOf(3, 4, 1, 5)
        val result = part1(input, 5)
        assertThat(result).isEqualTo(12)
    }

    @Test
    fun actual() {
        val result = part1(input)
        println(result)
    }

    @Test
    fun part2Examples() {
        assertThat(part2("")).isEqualTo("a2582a3a0e66e6e86e3812dcb672a272")
        assertThat(part2("AoC 2017")).isEqualTo("33efeb34ea91902bb2f59c9920caa6cd")
        assertThat(part2("1,2,3")).isEqualTo("3efbe78a8d82f29979031a4aa0b16a9d")
        assertThat(part2("1,2,4")).isEqualTo("63960835bcdc130f0b66d7ff4f6a5a8e")
    }

    @Test
    fun part2() {
        val result = part2("227,169,3,166,246,201,0,47,1,255,2,254,96,3,97,144")
        println("result = $result")
    }

    @Test
    fun testXOR() {
        val input = listOf(65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22)
        assertThat(xor(input)).isEqualTo(64)
    }

    @Test
    fun testHex() {
        val input = arrayOf(64, 7, 255)
        println(input.map { toHex(it) }.joinToString())
    }
}