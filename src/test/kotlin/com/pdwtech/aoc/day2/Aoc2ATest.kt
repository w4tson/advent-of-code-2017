package com.pdwtech.aoc.day2

import com.pdwtech.aoc.Aoc.readInput
import com.pdwtech.aoc.Aoc.toIntArray
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Aoc2ATest {

    @Test
    fun examples() {
        val spreadsheet : Array<IntArray> = arrayOf(
            intArrayOf(5, 1, 9, 5),
            intArrayOf(7, 5, 3),
            intArrayOf(2, 4, 6, 8)
        )

        assertThat(Aoc2A.solvePuzzle(spreadsheet))
                .isEqualTo(18)
    }

    @Test
    fun actual() {
        val input = readInput("aoc2")
        val spreadsheet = input.map { toIntArray(it) }.toTypedArray()

        println("Aoc 2A Result = ${Aoc2A.solvePuzzle(spreadsheet)}")

    }
}