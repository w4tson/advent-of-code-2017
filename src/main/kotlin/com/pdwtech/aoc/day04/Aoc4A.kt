package com.pdwtech.aoc.day04
import com.pdwtech.aoc.Aoc.splitWhitespace


object Aoc4A {

    fun validPassphrase(input : String) : Boolean {
        return input
                .splitWhitespace()
                .sorted()
                .fold(Pair(true, ""), {acc, elem -> when {
                    !acc.first || acc.second == elem -> Pair(false, elem)
                    else -> Pair(true, elem)
                }
        }).first
    }
}