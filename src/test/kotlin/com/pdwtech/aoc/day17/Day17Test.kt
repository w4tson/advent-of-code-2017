package com.pdwtech.aoc.day17

import com.pdwtech.aoc.day17.Day17.part2
import com.pdwtech.aoc.day17.Day17.spin
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class Day17Test {

    val exampleInput = 3
    val actualInput  = 328
    val iteration = 2017

    @Test
    fun testSequnce() {
        assertThat(Day17.spinArray(3,1)).isEqualTo(arrayOf(0,1).toIntArray())
        assertThat(Day17.spinArray(3,2)).isEqualTo(arrayOf(0,2,1).toIntArray())
        assertThat(Day17.spinArray(3,3)).isEqualTo(arrayOf(0,2,3,1).toIntArray())
        assertThat(Day17.spinArray(3,4)).isEqualTo(arrayOf(0,2,4,3,1).toIntArray())
        assertThat(Day17.spinArray(3,5)).isEqualTo(arrayOf(0,5,2,4,3,1).toIntArray())
        assertThat(Day17.spinArray(3,6)).isEqualTo(arrayOf(0,5,2,4,3,6,1).toIntArray())
        assertThat(Day17.spinArray(3,7)).isEqualTo(arrayOf(0,5,7,2,4,3,6,1).toIntArray())
        assertThat(Day17.spinArray(3,8)).isEqualTo(arrayOf(0,5,7,2,4,3,8,6,1).toIntArray())
    }

    @Test
    fun insert() {
        val input4 = arrayOf(0,2,1).toIntArray()
        assertThat(Day17.insertAfter(input4, 0, 5)).isEqualTo(arrayOf(0,2,3,1).toIntArray())

        val input = arrayOf(4, 5, 6).toIntArray()
        assertThat(Day17.insertAfter(input, 1, 1)).isEqualTo(arrayOf(4,5,1,6).toIntArray())

        val input2 = arrayOf(7, 8, 9, 2).toIntArray()
        assertThat(Day17.insertAfter(input2, 3, 1)).isEqualTo(arrayOf(7,8,9,2,1).toIntArray())

        val input3 = arrayOf(1,2,3,4).toIntArray()
        assertThat(Day17.insertAfter(input3, 0, 5)).isEqualTo(arrayOf(1,5,2,3,4).toIntArray())
    }

    @Test
    fun example() {
        assertThat(spin(3)).isEqualTo(638)
    }

    @Test
    fun part1() {
        println(spin(skip = 328))
    }

    @Test
    fun part2Example() {
        assertThat(part2(1, 3)).isEqualTo(1)
        assertThat(part2(2, 3)).isEqualTo(2)
        assertThat(part2(3, 3)).isEqualTo(2)
        assertThat(part2(5, 3)).isEqualTo(5)
        assertThat(part2(6, 3)).isEqualTo(5)
        assertThat(part2(7, 3)).isEqualTo(5)
        assertThat(part2(8, 3)).isEqualTo(5)
        assertThat(part2(9, 3)).isEqualTo(9)
    }

    @Test
    fun part2() {
        println(part2(insertions = 50_000_000, skip = 328))
    }
}