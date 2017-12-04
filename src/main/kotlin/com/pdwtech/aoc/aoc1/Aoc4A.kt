package com.pdwtech.aoc.aoc1

object Aoc4A {

    fun validPassphrase(input : String) : Boolean {
        return input
                .split(Regex("\\s"))
                .sorted()
                .fold(Pair(true, ""), {acc, elem -> when {
                    !acc.first || acc.second == elem -> Pair(false, elem)
                    else -> Pair(true, elem)
                }
        }).first
    }
}