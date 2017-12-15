package com.pdwtech.aoc.day15

import com.pdwtech.aoc.day15.Day15.matchingPairs
import com.pdwtech.aoc.day15.Day15.matchingPairs2
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class Day15Test {

    private val exampleSeed = Pair(65,8921)
    private val input = Pair(516,190)

    @Test
    fun part1Example() {
        assertThat(matchingPairs(exampleSeed)).isEqualTo(588)
    }

    @Test
    fun part1() {
        println(matchingPairs(input))
    }

    @Test
    fun part2Example() {
        assertThat(matchingPairs2(exampleSeed)).isEqualTo(309)
    }

    @Test
    fun part2() {
        println(matchingPairs2(input))
    }
}