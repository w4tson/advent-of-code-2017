package com.pdwtech.aoc.day07

import com.pdwtech.aoc.Aoc
import org.junit.Test

class Aoc7BTest {

    @Test
    fun example() {
        val lines = Aoc.readInput("aoc7")
        Tree().buildTree(lines)
    }
}