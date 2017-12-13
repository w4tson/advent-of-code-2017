package com.pdewtech.aoc

import com.pdwtech.aoc.Aoc.readInput
import com.pdwtech.aoc.day13.Day13
import com.pdwtech.aoc.day13.Day13.part2
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day13Test {
    val exampleInput = readInput("aoc13-example")
    val exampleFirewall = Day13.buildFirewall(exampleInput)

    val input = readInput("aoc13")
    val firewall = Day13.buildFirewall(input)

    @Test
    fun example1() {
        assertThat(Day13.part1(exampleFirewall)).isEqualTo(24)
    }

    @Test
    fun example2() {
        val input = readInput("aoc13-example2")
        val firewall = Day13.buildFirewall(input)

        Day13.part1(firewall)
    }

    @Test
    fun part1() {


        println(Day13.part1(firewall))

    }

    @Test
    fun examplePart2() {

        assertThat(part2(exampleFirewall)).isEqualTo(10)
    }

    @Test
    fun actualPaet2() {

        println( part2(firewall))
    }
}