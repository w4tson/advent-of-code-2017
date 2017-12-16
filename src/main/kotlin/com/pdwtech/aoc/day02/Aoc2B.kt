package com.pdwtech.aoc.day02

import com.pdwtech.aoc.Aoc.head
import com.pdwtech.aoc.Aoc.tail

object Aoc2B {

    fun solvePuzzle(spreadsheet : Array<IntArray>) : Int = spreadsheet.map { rowChecksum(it) }.sum()

    private fun rowChecksum(arr: IntArray) : Int {
        return when(arr.size) {
            0 -> 0
            1 -> 0
            else -> sumList(arr) + rowChecksum(arr.tail)
        }
    }

    /**
     * If a pair is not evenly divisible just return 0, else return the result of the division
     */
    private fun sumList(arr: IntArray) : Int = pairWithList(arr.head, arr.tail).sumBy { evenlyDivisible(it) }

    private fun pairWithList(i: Int, arr: IntArray) : List<Pair<Int, Int>> {
        return arr.map {
            Pair(i, it)
        }
    }

    fun evenlyDivisible(pair: Pair<Int, Int>) : Int = evenlyDivisible(pair.first, pair.second)

    fun evenlyDivisible(a: Int, b: Int) : Int = when {
        a % b == 0 -> a / b
        b % a == 0 -> b / a
        else       -> 0
    }
}


