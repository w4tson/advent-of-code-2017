package com.pdewtech.aoc

import com.pdwtech.aoc.aoc1.Aoc5B
import org.assertj.core.api.Assertions
import org.junit.Test

class Aoc5BTest {

    @Test
    fun example() {
        Assertions.assertThat(Aoc5B.solvePuzzle(arrayOf(0, 2, 7, 0)))
                .isEqualTo(4)
    }

    @Test
    fun actual() {
        val result = Aoc5B.solvePuzzle(arrayOf(10, 3, 15, 10, 5, 15, 5, 15, 9, 2, 5, 8, 5, 2, 3, 6))
        println("result = $result")
    }
}