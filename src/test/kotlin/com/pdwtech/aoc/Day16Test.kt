package com.pdwtech.aoc

import com.pdwtech.aoc.Day16.parseMoves
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day16Test {

    val exampleData = "abcde".toMutableList()
    val exampleMoveStr = "s1,x3/4,pe/b"
    val exampleMoves = parseMoves(exampleMoveStr)

    @Test
    fun example() {
        val result = Day16.oneDanceIteration(exampleMoves, exampleData)
        assertThat(result).isEqualTo("baedc")
    }

    @Test
    fun testMoveParse() {
        val moves = Day16.parseMoves(exampleMoveStr)
        assertThat(moves).hasSize(3)
        assertThat(moves).contains(Spin(3), Exchange(3,4), Partner('e', 'b'))
    }

    @Test
    fun part1() {
        println(Day16.oneDanceIteration())
    }

    @Test
    fun part2Example() {
        assertThat(Day16.nDanceIterations(exampleMoves, exampleData, 10)).isEqualTo("ceadb")
    }

    @Test
    fun part2() {
        println(Day16.nDanceIterations(iteration = 1_000_000_000))
    }
}