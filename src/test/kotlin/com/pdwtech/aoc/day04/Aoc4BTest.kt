package com.pdwtech.aoc.day04

import com.pdwtech.aoc.Aoc
import com.pdwtech.aoc.day04.Aoc4B.validPassphrase
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Aoc4BTest {

    @Test
    fun examples() {
        assertThat(validPassphrase("abcde fghij"))
                .isEqualTo(true)

        assertThat(validPassphrase("abcde xyz ecdab"))
                .isEqualTo(false)

        assertThat(validPassphrase("a ab abc abd abf abj"))
                .isEqualTo(true)

        assertThat(validPassphrase("iiii oiii ooii oooi oooo"))
                .isEqualTo(true)

        assertThat(validPassphrase("oiii ioii iioi iiio"))
                .isEqualTo(false)

    }

    @Test
    fun actual() {
        val numValid = Aoc.readInput("aoc4")
                .map{ validPassphrase(it) }
                .count{ it }

        println("Aoc 4B Result = ${numValid}")
    }
}