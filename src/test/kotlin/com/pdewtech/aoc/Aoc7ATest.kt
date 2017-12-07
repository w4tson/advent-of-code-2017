package com.pdewtech.aoc

import com.pdwtech.aoc.Aoc
import com.pdwtech.aoc.day7.Tree
import org.junit.Test

class Aoc7ATest {

    @Test
    fun example() {
        val lines = Aoc.readInput("aoc7")
        val root = Tree().nameOfRoot(lines)
        println("root = $root")
    }
}