package com.pdwtech.aoc.day1

import com.pdwtech.aoc.Aoc.parseInt

object Aoc1B {

    fun solvePuzzle(input : String) : Int {
        val circularInput = input + input
        val halfwayIndex : Int = input.length / 2

        return input.foldIndexed(0, { i, acc, curr ->
            when(curr == circularInput[i+halfwayIndex]) {
                true  -> acc + parseInt(curr)
                false -> acc
            }
        })
    }

}