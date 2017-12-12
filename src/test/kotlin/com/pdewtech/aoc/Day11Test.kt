package com.pdewtech.aoc

import com.pdwtech.aoc.Aoc.readInput
import com.pdwtech.aoc.day11.Day11.move
import com.pdwtech.aoc.day11.ORIGIN
import org.junit.Test

class Day11Test {

    val movements = readInput("aoc11")[0].split(",")

    val finalPosition = move(movements)

    @Test
    fun part1() {
        println("Distance from 0,0,0 = ${finalPosition.distanceFrom(ORIGIN)}")
    }

    @Test
    fun part2() {
        println("Max distance from 0,0,0 = ${finalPosition.maxDist}")
    }
}