package com.pdewtech.aoc

import com.pdwtech.aoc.Aoc.readInput
import com.pdwtech.aoc.aoc1.Aoc1A
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Aoc1ATest {

    @Test
    fun examples() {
        assertThat(Aoc1A.solvePuzzle("1122"))
                .isEqualTo(3)

        assertThat(Aoc1A.solvePuzzle("1111"))
                .isEqualTo(4)

        assertThat(Aoc1A.solvePuzzle("1234"))
                .isEqualTo(0)

        assertThat(Aoc1A.solvePuzzle("91212129"))
                .isEqualTo(9)
    }

    @Test
    fun actual() {
        val input = readInput("aoc1")[0]
        val result = Aoc1A.solvePuzzle(input)
        println("result = $result")
    }

}