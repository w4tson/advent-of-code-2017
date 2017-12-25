package com.pdwtech.aoc.day24

import com.pdwtech.aoc.Aoc.readInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test



class Day24Test {
    
    val exampleInput = readInput("aoc24-example")
    val actualInput = readInput("aoc24")
    val exampleComponents = Day24.parseComponents(exampleInput)
    val components = Day24.parseComponents(actualInput)
    val day24Example = Day24(exampleComponents)
    val day24 = Day24(components)

    @Test
    fun part1() {
        println(day24.findStrongestBridge())
    }

    @Test
    fun part2() {
        println(day24.findLongestBridge())
    }

    @Test
    fun example() {
        assertThat(day24Example.findStrongestBridge()).isEqualTo(31)
    }

    @Test
    fun findAllBridges() {
        
        day24Example.findAllBridges().map { it.joinToString(separator = "--") }.forEach {
            println(it) 
        }
    }

    @Test
    fun testPermute() {

        val c = Component(0, 2)
        val remaining = exampleComponents.minus(c)
        val result = day24Example.permute(listOf(c), remaining, setOf(listOf(c)))
        assertThat(result).contains(
                listOf(Component(0, 2)),
                listOf(Component(0, 2), Component(2, 3))
        )
    }
}