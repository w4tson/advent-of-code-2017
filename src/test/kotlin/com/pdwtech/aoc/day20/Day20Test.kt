package com.pdwtech.aoc.day20

import com.pdwtech.aoc.Aoc.readInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day20Test {
    
    val exampleInput = readInput("aoc20-example")
    val exampleInput2 = readInput("aoc20-example2")
    val actualInput = readInput("aoc20")
    
    @Test
    fun example() {
        val particles = parseParticles(exampleInput)
        val nearest = com.pdwtech.aoc.day20.Day20.tick(particles, 4)
        assertThat(nearest).isEqualTo(0)
    }

    @Test
    fun part1() {
        val particles = parseParticles(actualInput)
        val nearest = com.pdwtech.aoc.day20.Day20.tick(particles, 500000)
        println(nearest)
    }

    @Test
    fun example2() {
        val particles = parseParticles(exampleInput2)
        val remaining = com.pdwtech.aoc.day20.Day20.tickWithKnockout(particles, 4)
        assertThat(remaining).isEqualTo(1)
    }

    @Test
    fun part2() {
        val particles = parseParticles(actualInput)
        val remaining = com.pdwtech.aoc.day20.Day20.tickWithKnockout(particles, 100000)
        println(remaining)
    }
    
    @Test
    fun testKnockout() {
        val p = listOf(Particle(Coord(0,0,0), Coord( 3,0,0), Coord(0,0,0)),
        Particle(Coord(0,0,0), Coord( 2,0,0), Coord(0,0,0)),
        Particle(Coord(0,0,0), Coord( 1,0,0), Coord(0,0,0)),
        Particle(Coord(1,0,0), Coord(-1,0,0), Coord(0,0,0)))
        
        val m = p.groupBy { it.p }
        
        assertThat(m.entries).hasSize(2)
        assertThat(m[Coord(0,0,0)]).hasSize(3)
        
        assertThat(Day20.knockout(p)).hasSize(1)
        assertThat(Day20.knockout(p)).contains(Particle(Coord(1,0,0), Coord(-1,0,0), Coord(0,0,0)))
    }


    @Test
    fun testPlus() {
        assertThat(Coord(-2,0,0) + Coord(0,0,0)).isEqualTo(Coord(-2, 0,0))
    }

    @Test
    fun testOneTick() {
        val t = Day20.tick(Particle(Coord(4,0,0), Coord(0,0,0), Coord(-2,0,0)))
        assertThat(t.p).isEqualTo(Coord(2,0,0))
        assertThat(t.v).isEqualTo(Coord(-2,0,0))
        assertThat(t.a).isEqualTo(Coord(-2,0,0))
    }

    fun parseParticles(input: List<String>) : List<Particle> = input.map{ stringToParticle(it) }
    
    fun stringToParticle(s: String) : Particle {
        val (p, v, a) = s.split(", ")
                .map{ it.slice(3 until it.length-1) }
                .map { toCoord(it) }
        return Particle(p, v, a)
    }
    
    fun toCoord(s : String) : Coord {
        val (x, y, z) = s.split(",").map{ it.trim().toLong() }
        return Coord(x, y, z)
    }
    
    
}