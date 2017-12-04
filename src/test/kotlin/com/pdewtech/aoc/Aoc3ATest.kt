package com.pdewtech.aoc

import com.pdwtech.aoc.aoc1.Aoc3A
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Aoc3ATest {

    @Test
    fun actual() {
        val result = Aoc3A.solvePuzzle(289326)
        println("Aoc 2B Result = ${result}")
    }

    @Test
    fun examples() {
        assertThat(Aoc3A.solvePuzzle(12))
                .isEqualTo(3)

        assertThat(Aoc3A.solvePuzzle(23))
                .isEqualTo(2)

        assertThat(Aoc3A.solvePuzzle(1024))
                .isEqualTo(31)

        assertThat(Aoc3A.solvePuzzle(1))
                .isEqualTo(0)
    }

    @Test
    fun testCorner() {
        Aoc3A.cornerSequence().take(5).forEach { println(it) }

        assertThat(Aoc3A.calcCorner(25))
                .isEqualTo(25)

        assertThat(Aoc3A.calcCorner(26))
                .isEqualTo(49)

        assertThat(Aoc3A.calcCorner(27))
                .isEqualTo(49)
    }

    /**
     *  Distance map for first 4 rings
     *
     *  6 5 4 3 4 5 6
     *  5 4 3 2 3 4 5
     *  4 3 2 1 2 3 4
     *  3 2 1 0 1 2 3
     *  4 3 2 1 2 3 4
     *  5 4 3 2 3 4 5
     *  6 5 4 3 4 5 6
     */
    @Test
    fun testDistSeq() {
        val seqRing2 = Aoc3A.distSequence(2).take(8).toList()
        assertThat(seqRing2).isEqualTo(listOf(2,1,2,1,2,1,2,1))

        val seqRing3 = Aoc3A.distSequence(3).take(12).toList()
        assertThat(seqRing3).isEqualTo(listOf(4, 3, 2, 3, 4, 3, 2, 3, 4, 3, 2, 3))
    }


}