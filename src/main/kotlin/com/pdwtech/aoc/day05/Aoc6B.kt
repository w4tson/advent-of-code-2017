package com.pdwtech.aoc.day05

import com.pdwtech.aoc.day05.Aoc6A.redistribute

object Aoc6B {

    fun solvePuzzle(input : Array<Int>) : Int = solvePuzzle(input, emptyList(), 0)

    tailrec fun solvePuzzle(input : Array<Int>, history: List<String>, acc: Int) : Int {
        val combo = input.joinToString(separator = "")
        val redistributed = redistribute(input)

        return when(history.count{ it == combo}) {
            0 -> solvePuzzle(redistributed, history + combo, acc)
            1 -> solvePuzzle(redistributed, history + combo, 1 + acc)
            else  -> acc
        }
    }
}