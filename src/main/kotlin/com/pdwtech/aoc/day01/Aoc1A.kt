package com.pdwtech.aoc.day01

import com.pdwtech.aoc.Aoc.parseInt

object Aoc1A {

    fun solvePuzzle(input : String) : Int {
        val wrappedInput = input + input[0]
        return wrappedInput.fold(Pair(0, 0), { acc, c ->
            when (acc.second == parseInt(c)) {
                true  -> Pair(acc.first + acc.second, acc.second)
                false -> Pair(acc.first , parseInt(c))
            }
        }).first
    }
}

