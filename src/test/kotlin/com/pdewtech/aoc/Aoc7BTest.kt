package com.pdewtech.aoc

import com.pdwtech.aoc.Aoc
import com.pdwtech.aoc.day7.Tree
import org.junit.Test

class Aoc7BTest {

    @Test
    fun example() {
        val lines = Aoc.readInput("aoc7")
        Tree().buildTree(lines)
    }
}