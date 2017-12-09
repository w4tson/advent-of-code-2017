package com.pdewtech.aoc

import com.pdwtech.aoc.Aoc.readInput
import com.pdwtech.aoc.day9.Part1.solvePart1
import com.pdwtech.aoc.day9.Part1.solvePart2
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day9Test {

    @Test
    fun example() {
        assertThat(solvePart1("{}"))
                .isEqualTo(1)

        assertThat(solvePart1("{some}"))
                .isEqualTo(1)

        assertThat(solvePart1("{{}}"))
                .isEqualTo(3)

        assertThat(solvePart1("{{}{}}"))
                .isEqualTo(5)

        assertThat(solvePart1("{{{}}}"))
                .isEqualTo(6)

        assertThat(solvePart1("{{{},{},{{}}}}"))
                .isEqualTo(16)


        assertThat(solvePart1("{<{},{},{{}}>}"))
                .isEqualTo(1)

        assertThat(solvePart1("{{<a>},{<a>},{<a>},{<a>}}"))
                .isEqualTo(9)


        assertThat(solvePart1("{{<a!>},{<a!>},{<a!>},{<ab>}}"))
                .isEqualTo(3)

        assertThat(solvePart1("{{<!}>},{<!{>},{<!>>},{<!!>}}"))
                .isEqualTo(9)

        val result = solvePart1(readInput("aoc9")[0])
        println("Day 9 part 1 = $result")
    }

    @Test
    fun part2() {

        assertThat(solvePart2("{<>}")).isEqualTo(0)

        assertThat(solvePart2("{<random characters>}")).isEqualTo(17)

        assertThat(solvePart2("{<<<<>{}}")).isEqualTo(3)

        assertThat(solvePart2("{{}<<<<><>}")).isEqualTo(3)

        assertThat(solvePart2("{<{!>}>}")).isEqualTo(2)

        assertThat(solvePart2("{<!!>}")).isEqualTo(0)

        assertThat(solvePart2("{<!!!>>}")).isEqualTo(0)

        assertThat(solvePart2("""{<{o"i!a,<{i<a>}""")).isEqualTo(10)

        val result = solvePart2(readInput("aoc9")[0])
        println("Day 9 part 2 = $result")

    }
}