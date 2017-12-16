package com.pdwtech.aoc.day16

import com.pdwtech.aoc.day16.Day16.nDanceIterations
import com.pdwtech.aoc.day16.Day16.oneDanceIteration
import com.pdwtech.aoc.day16.Day16.parseMoves
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day16Test {

    private val exampleData = "abcde".toMutableList()
    private val exampleMoveStr = "s1,x3/4,pe/b"
    private val exampleMoves = parseMoves(exampleMoveStr)

    @Test
    fun example() {
        val result = oneDanceIteration(exampleMoves, exampleData)
        assertThat(result).isEqualTo("baedc")
    }

    @Test
    fun testMoveParse() {
        val moves = parseMoves(exampleMoveStr)
        assertThat(moves).hasSize(3)
        assertThat(moves).contains(Spin(1), Exchange(3,4), Partner('e', 'b'))
    }

    @Test
    fun part1() {
        println(oneDanceIteration())
    }

    @Test
    fun part2Example() {
        assertThat(nDanceIterations(exampleMoves, exampleData, 10)).isEqualTo("ceadb")
    }

    @Test
    fun part2() {
        println(nDanceIterations(iteration = 1_000_000_000))
    }
}