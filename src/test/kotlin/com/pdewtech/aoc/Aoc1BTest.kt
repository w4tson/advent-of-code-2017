package com.pdewtech.aoc

import com.pdwtech.aoc.Aoc.readInput
import com.pdwtech.aoc.day1.Aoc1B
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Aoc1BTest {

    @Test
    fun examples() {
        assertThat(Aoc1B.solvePuzzle("1212")).isEqualTo(6)
        assertThat(Aoc1B.solvePuzzle("1221")).isEqualTo(0)
        assertThat(Aoc1B.solvePuzzle("123425")).isEqualTo(4)
        assertThat(Aoc1B.solvePuzzle("123123")).isEqualTo(12)
        assertThat(Aoc1B.solvePuzzle("12131415")).isEqualTo(4)
    }

    @Test
    fun actual() {
        val input = readInput("aoc1")[0]
        println(Aoc1B.solvePuzzle(input))
    }
}