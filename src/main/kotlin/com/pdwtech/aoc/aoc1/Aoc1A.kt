package com.pdwtech.aoc.aoc1

class Aoc1A {

    fun solvePuzzle(input : String) : Int {
        val wrappedInput = input + input[0]
        return wrappedInput.fold(Pair(0, 0), { acc, c ->
            when (acc.second == parseInt(c)) {
                true  -> Pair(acc.first + acc.second, acc.second)
                false -> Pair(acc.first , parseInt(c))
            }
        }).first
    }

    fun parseInt(c : Char) : Int {
        return "${c}".toInt()
    }
}

