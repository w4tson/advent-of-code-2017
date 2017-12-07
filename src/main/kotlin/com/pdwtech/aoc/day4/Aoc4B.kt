package com.pdwtech.aoc.day4
import com.pdwtech.aoc.Aoc.splitWhitespace

object Aoc4B {

    fun validPassphrase(input: String): Boolean {
        return input
                .splitWhitespace()
                .map { sortWord(it) }
                .sorted()
                .fold(Pair(true, ""), { acc, elem ->
                    when {
                        !acc.first || acc.second == elem -> Pair(false, elem)
                        else -> Pair(true, elem)
                    }
                }).first
    }

    fun sortWord(phrase : String) : String = phrase
            .toCharArray()
            .sorted()
            .joinToString(separator = "")

}
