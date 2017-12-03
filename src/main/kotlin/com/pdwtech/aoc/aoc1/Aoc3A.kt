package com.pdwtech.aoc.aoc1

import java.lang.Math.*

object Aoc3A {

    fun solvePuzzle(num : Int) : Int {
        val bottomCorner = calcCorner(num)
        val ring = cornerSequence().indexOf(bottomCorner)
        val size = sqrt(bottomCorner.toDouble()).toInt()

        return 0
    }


    fun cornerSequence(): Sequence<Int> {
        return generateSequence(1, { Math.pow((Math.sqrt(it.toDouble())+2.0), 2.0).toInt() })
    }

    fun distSequence(ring: Int): Sequence<Int> {
        val width = ring * 2 - 1
        val halfway = ring - (width / 2)
        return generateSequence(ring, {
            when(it) {
                ring -> it - 1
                halfway -> it +1
                else -> 0
            }
        })
    }

    fun calcCorner(n : Int) : Int {
        val cornerIndex = cornerSequence().indexOfFirst { it >= n }
        return cornerSequence().elementAt(cornerIndex)
    }


//    bc  #
//    1   1 1^2
//    9   2 3^2
//    25  3 5^2
//    49  4 7^2
//
//    37 36  35  34  33  32 31
//    38 17  16  15  14  13 30
//    39 18   5   4   3  12 29
//    40 19   6   1   2  11 28
//    41 20   7   8   9  10 27
//    42 21  22  23  24  25 26
//    43 44  45  46  47  48 49

//    4 3 2 3 4
//    3 2 1 2 3
//    2 1 0 1 2
//    3 2 1 2 3
//    4 3 2 3 4
}