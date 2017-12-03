package com.pdewtech.aoc

import com.pdwtech.aoc.aoc1.Aoc3A
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Aoc3ATest {

    @Test
    fun example() {

//        re 1 is carried 0 steps, since it's at the access port.
//        Data from square 12 is carried 3 steps, such as: down, left, left.
//        Data from square 23 is carried only 2 steps: up twice.
//        Data from square 1024

        assertThat(Aoc3A.solvePuzzle(12))
                .isEqualTo(3)

//        assertThat(Aoc3A.solvePuzzle(23))
//                .isEqualTo(2)
//
//        assertThat(Aoc3A.solvePuzzle(1024))
//                .isEqualTo(31)
//
//        assertThat(Aoc3A.solvePuzzle(1))
//                .isEqualTo(0)
    }

    @Test
    fun testCorner() {
        assertThat(5/2).isEqualTo(2)

        Aoc3A.cornerSequence().take(5).forEach { println(it) }

        assertThat(Aoc3A.calcCorner(25))
                .isEqualTo(25)

        assertThat(Aoc3A.calcCorner(26))
                .isEqualTo(49)

        assertThat(Aoc3A.calcCorner(27))
                .isEqualTo(49)
    }
}