package com.pdwtech.aoc.day07

import com.pdwtech.aoc.Aoc
import org.junit.Test

class Aoc7ATest {

    @Test
    fun example() {
        val lines = Aoc.readInput("aoc7")
        val root = Tree().nameOfRoot(lines)
        println("root = $root")
    }
}