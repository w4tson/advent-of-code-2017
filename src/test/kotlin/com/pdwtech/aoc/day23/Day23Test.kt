package com.pdwtech.aoc.day23

import com.pdwtech.aoc.Aoc.readInput
import org.junit.Test

class Day23Test {
    
    val actualInput = readInput("aoc23")

    @Test
    fun name() {
        println(Program(actualInput).run())
    }


    @Test
    fun countPrimes() {
        val primes = ProgramTranslated.countNonePrimesBetween(105700, 105700+17000)
        println(primes)
    }
}