package com.pdwtech.aoc.aoc1

object Aoc2A {

    fun solvePuzzle(spreadsheet : Array<IntArray>) : Int = spreadsheet.map { rowChecksum(it) }.sum()

    private fun rowChecksum(row : IntArray) : Int {
        val max = row.max() ?: 0
        val min = row.min() ?: 0
        return max - min
    }
}