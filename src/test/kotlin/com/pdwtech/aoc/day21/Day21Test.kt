package com.pdwtech.aoc.day21

import com.pdwtech.aoc.Aoc.readInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day21Test {
    
    val exampleRules = readInput("aoc21-example")
    val actualRules  = readInput("aoc21")

    @Test
    fun splitInto3s() {
        val biggy = ".#.#.#/....../###.../...###/..##../#.#.#."
        val listOfThrees = Day21(exampleRules).splitInto3By3(biggy)
        assertThat(listOfThrees).hasSize(4)
        assertThat(listOfThrees).contains(".#./.../###", "#.#/.../...",
                                            ".../..#/#.#","###/#../.#.")
    }

    @Test
    fun splitInto2s() {
        val biggy = ".#.#/..../###./..##"
        val listOfThrees = Day21(exampleRules).splitInto2By2(biggy)
        assertThat(listOfThrees).hasSize(4)
        assertThat(listOfThrees).contains(".#/..", ".#/..","##/..","#./##")
    }

    @Test
    fun allRotations() {
        val allRotations = Day21(exampleRules).allRotations(".#./..#/###")
        assertThat(allRotations)
                .hasSize(3)
    }

    @Test
    fun testFlipHorizontal() {
        assertThat(Day21(exampleRules).flipHorizontal(".#./..#/###"))
                .isEqualTo("###/..#/.#.")
    }

    @Test
    fun testFlipVertical() {
        assertThat(Day21(exampleRules).flipVertical(".#./..#/###"))
                .isEqualTo(".#./#../###")
    }

    @Test
    fun testRotate() {
        assertThat(Day21(exampleRules).rotate(".#./..#/###"))
                .isEqualTo(".##/#.#/..#")

        assertThat(Day21(exampleRules).rotate("../.#"))
                .isEqualTo(".#/..")
    }

    @Test
    fun testTranspose() {
        // .#.  .## 
        // ..#  #.#
        // ###  ..# 
        
        // 123 147
        // 456 258
        // 789 369
        
        // rotated 

        assertThat(Day21(exampleRules).transpose(".#./..#/###"))
                .isEqualTo("..#/#.#/.##")
    }

    @Test
    fun testReverse() {
        assertThat(Day21(exampleRules).reverse(".#./..#/###"))
                .isEqualTo("###/..#/.#.")
    }

    @Test
    fun testSize() {
        assertThat(Day21(exampleRules).size(".#./..#/###")).isEqualTo(3)
    }
}