package com.pdwtech.aoc.day5

import com.pdwtech.aoc.Aoc.circularIndexSeq
import com.pdwtech.aoc.Aoc.indexOfMax

object Aoc6A {

    fun solvePuzzle(input : Array<Int>) : Int = solvePuzzle(input, emptyList(), 0)

    tailrec fun solvePuzzle(input : Array<Int>, history: List<String>, acc: Int) : Int {
        val combo = input.joinToString(separator = "")
        val redistributed = redistribute(input)

        return when(history.contains(combo)) {
            true  -> acc
            false -> solvePuzzle(redistributed, history + combo, 1 + acc)
        }
    }

    fun redistribute(input : Array<Int>) : Array<Int> {
        val indexMax = input.indexOfMax()
        val blocks = input[indexMax]
        input[indexMax] = 0

        input.circularIndexSeq(indexMax)
                .drop(1)
                .take(blocks)
                .forEach { input[it] += 1 }

        return input
    }
}