package com.pdwtech.aoc.day22

import com.pdwtech.aoc.Aoc.readInput
import com.pdwtech.aoc.day22.InfectionStatus.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test



class Day22Test {
    
    val exampleInput = readInput("aoc22-example")
    val actualInput = readInput("aoc22")
    val initialExample = initialMap(exampleInput)
    val initial = initialMap(actualInput)

    @Test
    fun part1() {
        println(Cluster(initial).infectedAfterNBursts(10_000))
    }

    @Test
    fun example() {
        
        assertThat(Cluster(initialExample).infectedAfterNBursts(10_000))
                .isEqualTo(5587)
    }

    @Test
    fun example2() {
        assertThat(Cluster(initialExample).infectedAfterNBursts2(10_000_000))
                .isEqualTo(2511944)
    }

    @Test
    fun part2() {
        println(Cluster(initial).infectedAfterNBursts2(10_000_000))
    }

    @Test
    fun testParseInput() {
        
        val i = initialMap(exampleInput)
        assertThat(i[Coord(-1,-1)]).isEqualTo(CLEAN)
        assertThat(i[Coord(0,-1)]).isEqualTo(CLEAN)
        assertThat(i[Coord(1,-1)]).isEqualTo(INFECTED)

        assertThat(i[Coord(-1,0)]).isEqualTo(INFECTED)
        assertThat(i[Coord(0,0)]).isEqualTo(CLEAN)
        assertThat(i[Coord(1,0)]).isEqualTo(CLEAN)
        
        assertThat(i[Coord(-1,1)]).isEqualTo(CLEAN)
        assertThat(i[Coord(0,1)]).isEqualTo(CLEAN)
        assertThat(i[Coord(1,1)]).isEqualTo(CLEAN)

        //#.#.#.##.#.##.###.#.###.#
        val a = initialMap(actualInput)
        assertThat(a[Coord(-12,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(-11,-12)]).isEqualTo(CLEAN)
        assertThat(a[Coord(-10,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(-9,-12)]).isEqualTo(CLEAN)
        assertThat(a[Coord(-8,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(-7,-12)]).isEqualTo(CLEAN)
        assertThat(a[Coord(-6,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(-5,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(-4,-12)]).isEqualTo(CLEAN)
        assertThat(a[Coord(-3,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(-2,-12)]).isEqualTo(CLEAN)
        assertThat(a[Coord(-1,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(0,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(1,-12)]).isEqualTo(CLEAN)
        assertThat(a[Coord(2,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(3,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(4,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(5,-12)]).isEqualTo(CLEAN)
        assertThat(a[Coord(6,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(7,-12)]).isEqualTo(CLEAN)
        assertThat(a[Coord(8,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(9,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(10,-12)]).isEqualTo(INFECTED)
        assertThat(a[Coord(11,-12)]).isEqualTo(CLEAN)
        assertThat(a[Coord(12,-12)]).isEqualTo(INFECTED)

        
    }
    
    fun initialMap(input: List<String>) : Map<Coord, InfectionStatus> {
        val size = input[0].length
        val n = (size -1) /2 
        return  input.foldIndexed(mapOf(), { y , acc, next ->
            acc + next.mapIndexed { x, c -> Pair(Coord(x-n, y-n), if (c == '#') INFECTED else CLEAN) }.toMap()
        })
    }
}