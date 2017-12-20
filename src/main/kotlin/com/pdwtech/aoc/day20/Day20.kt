package com.pdwtech.aoc.day20

import kotlin.math.abs

data class Coord(val x: Long, val y: Long, val z: Long) {
    infix operator fun plus(c: Coord): Coord = Coord(c.x+ x, c.y + y, c.z + z)
}

data class Particle(val p : Coord, val v: Coord, val a:Coord) {
    fun distanceFromOrigin() : Long = abs(p.x) + abs(p.y) + abs(p.z)
}

object Day20 {

    fun tick(particles: List<Particle>, n : Int) : Int? = 
            generateSequence(particles, { tick(it) })
                .take(n)
                .last()
                .mapIndexed { index, particle ->  Pair(index, particle.distanceFromOrigin()) }
                .minBy { it.second }?.first

    fun tickWithKnockout(particles: List<Particle>, n : Int) : Int? =
                generateSequence(particles, { knockout(tick(it)) })
                .take(n)
                .last()
                .size
    
    fun tick(particles: List<Particle>) : List<Particle> = particles.map { tick(it) }
    
    fun knockout(p : List<Particle>) : List<Particle> = p.groupBy { it.p }.filter { it.value.size == 1 }.values.flatten()
        
    fun tick(p : Particle) : Particle {
        val v = p.v + p.a
        return Particle(p.p + v, v, p.a)
    }
 }