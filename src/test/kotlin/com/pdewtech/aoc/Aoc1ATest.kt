package com.pdewtech.aoc

import com.pdwtech.aoc.aoc1.Aoc1A
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths

class Aoc1ATest {

    fun readInput(fileName: String) : List<String> {
        val resource = javaClass.getResource("/$fileName")
        val toURI = resource.toURI()
        return Files.readAllLines(Paths.get(toURI))
    }

    @Test
    fun examples() {
        val aoc1 = Aoc1A()

        assertThat(aoc1.solvePuzzle("1122"))
                .isEqualTo(3)

        assertThat(aoc1.solvePuzzle("1111"))
                .isEqualTo(4)

        assertThat(aoc1.solvePuzzle("1234"))
                .isEqualTo(0)

        assertThat(aoc1.solvePuzzle("91212129"))
                .isEqualTo(9)
    }

    @Test
    fun actual() {
        val lines = readInput("aoc1a")
        val input = lines.get(0)
        println(input)
        val result = Aoc1A().solvePuzzle(input)
        println("result = $result")
    }

}