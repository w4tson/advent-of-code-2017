package com.pdwtech.aoc.day12

import com.pdwtech.aoc.Aoc.readInput
import com.pdwtech.aoc.day12.Day12.groups
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class Day12Test {

    val exampleInput = readInput("aoc12-example")
    val actualInput = readInput("aoc12")
    val r = Regex("""^(\d+) <-> (.*)""")

    @Test
    fun exampleGroups() {
        val exampleGraph = parse(exampleInput)
        val groups = groups(exampleGraph)
        assertThat(groups).hasSize(2)
    }

    @Test
    fun part1() {
        val graph = parse(actualInput)
        println(groups(graph).filter { it.contains(0) }.first().size)
    }

    @Test
    fun part2() {
        val graph = parse(actualInput)
        println(groups(graph).size)
    }

    fun parse(input: List<String>) : Map<Int, List<Int>>  {
        return input.associate { a -> toPair(a) }.toMap()
    }

    fun toPair(s : String) : Pair<Int, List<Int>> {
        val (node, childStr) = r.find(s)!!.destructured
        return Pair(node.toInt(), childStr.split(", ").map(String::toInt))
    }
}
