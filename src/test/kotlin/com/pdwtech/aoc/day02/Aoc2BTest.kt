package com.pdwtech.aoc.day02

import com.pdwtech.aoc.Aoc.readInput
import com.pdwtech.aoc.Aoc.toIntArray
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Aoc2BTest {

    @Test
    fun examples() {
        val spreadsheet : Array<IntArray> = arrayOf(
            intArrayOf(5, 9, 2, 8),
            intArrayOf(9, 4, 7, 3),
            intArrayOf(3, 8, 6, 5)
        )

        assertThat(Aoc2B.solvePuzzle(spreadsheet))
                .isEqualTo(9)
    }

    @Test
    fun shouldBeEvenlyDivisible() {
        assertThat(Aoc2B.evenlyDivisible(6, 3))
                .isEqualTo(2)

        assertThat(Aoc2B.evenlyDivisible(3, 6))
                .isEqualTo(2)

        assertThat(Aoc2B.evenlyDivisible(6, 6))
                .isEqualTo(1)

        assertThat(Aoc2B.evenlyDivisible(7, 6))
                .isEqualTo(0)
    }

    @Test
    fun actual() {
        val input = readInput("aoc2")
        val spreadsheet = input.map { toIntArray(it) }.toTypedArray()

        println("Aoc 2B Result = ${Aoc2B.solvePuzzle(spreadsheet)}")

    }
}