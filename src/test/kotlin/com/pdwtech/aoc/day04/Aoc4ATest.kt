package com.pdwtech.aoc.day04

import com.pdwtech.aoc.Aoc
import com.pdwtech.aoc.day04.Aoc4A.validPassphrase
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Aoc4ATest {

    @Test
    fun examples() {
        assertThat(validPassphrase("aa bb cc dd ee"))
                .isEqualTo(true)

        assertThat(validPassphrase("aa bb cc dd aa"))
                .isEqualTo(false)

        assertThat(validPassphrase("aa bb cc dd aaa"))
                .isEqualTo(true)

        assertThat(validPassphrase("aa c bb cc dd c aaa"))
                .isEqualTo(false)

    }

    @Test
    fun actual() {
        val numValid = Aoc.readInput("aoc4")
                .map{ validPassphrase(it) }
                .count{ it }

        println("Aoc 4A Result = ${numValid}")
    }
}