package com.pdewtech.aoc

import com.pdwtech.aoc.aoc1.Aoc5A
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Aoc5ATest {
    @Test
    fun example() {
        assertThat(Aoc5A.redistribute(arrayOf(0, 2, 7, 0)))
                .isEqualTo(arrayOf(2, 4, 1, 2))

        assertThat(Aoc5A.redistribute(arrayOf(2, 4, 1, 2)))
                .isEqualTo(arrayOf(3, 1, 2, 3))

        assertThat(Aoc5A.redistribute(arrayOf(3, 1, 2, 3)))
                .isEqualTo(arrayOf(0, 2, 3, 4))

        assertThat(Aoc5A.redistribute(arrayOf(0, 2, 3, 4)))
                .isEqualTo(arrayOf(1, 3, 4, 1))

        assertThat(Aoc5A.redistribute(arrayOf(1, 3, 4, 1)))
                .isEqualTo(arrayOf(2, 4, 1, 2))

        assertThat(Aoc5A.solvePuzzle(arrayOf(0, 2, 7, 0)))
                .isEqualTo(5)

    }

    @Test
    fun actual() {
        val result = Aoc5A.solvePuzzle(arrayOf(10, 3, 15, 10, 5, 15, 5, 15, 9, 2, 5, 8, 5, 2, 3, 6))
        println("result = $result")
    }
}