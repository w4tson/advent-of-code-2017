package com.pdwtech.aoc.day14

import com.pdwtech.aoc.day14.Day14.adjacentIndicies
import com.pdwtech.aoc.day14.Day14.countRegions
import org.junit.Test

import org.assertj.core.api.Assertions.assertThat


class Day14Test {

    val exampleKey = "flqrgnkx"
    val key = "stpzcrnm"

    @Test
    fun toBinary() {

        assertThat(Day14.toBinary('0')).isEqualTo("0000")
        assertThat(Day14.toBinary('1')).isEqualTo("0001")
        assertThat(Day14.toBinary('e')).isEqualTo("1110")
        assertThat(Day14.toBinary('f')).isEqualTo("1111")
    }

    @Test
    fun example() {
        assertThat(Day14.usedSquares(exampleKey)).isEqualTo(8108)
    }

    @Test
    fun actual() {
        println("Day 14 used squares = ${Day14.usedSquares(key)}")
    }

    @Test
    fun testAjacentIndicies() {
        assertThat(adjacentIndicies(0)).contains(1,128)
        assertThat(adjacentIndicies(129)).contains(1,128,130,129+128)
    }

    @Test
    fun part2Example() {
        assertThat(countRegions(exampleKey)).isEqualTo(1242)
    }

    @Test
    fun part2() {
        println(countRegions(key))
    }
}