package com.pdwtech.aoc.day6

import com.pdwtech.aoc.day5.Aoc6B
import org.assertj.core.api.Assertions
import org.junit.Test

class Aoc6BTest {

    @Test
    fun example() {
        Assertions.assertThat(Aoc6B.solvePuzzle(arrayOf(0, 2, 7, 0)))
                .isEqualTo(4)
    }

    @Test
    fun actual() {
        val result = Aoc6B.solvePuzzle(arrayOf(10, 3, 15, 10, 5, 15, 5, 15, 9, 2, 5, 8, 5, 2, 3, 6))
        println("result = $result")
    }
}