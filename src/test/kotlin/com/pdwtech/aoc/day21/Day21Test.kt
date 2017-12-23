package com.pdwtech.aoc.day21

import com.pdwtech.aoc.Aoc.readInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Day21Test {
    
    val exampleRules = readInput("aoc21-example")
    val actualRules  = readInput("aoc21")

    @Test
    fun part1() {
        
        println(Day21(actualRules).pixelsAfterNIterations(5))
    }

    @Test
    fun part2() {

        println(Day21(actualRules).pixelsAfterNIterations(18))
    }

    @Test
    fun part1Example() {
        assertThat(Day21(exampleRules).fractal().take(1).last())
                .isEqualTo(".#./..#/###")
        
        assertThat(Day21(exampleRules).stitch3s(listOf("#..#/..../..../#..# "))).isEqualTo("#..#/..../..../#..#")

        assertThat(Day21(exampleRules).fractal().take(2).last())
                .isEqualTo("#..#/..../..../#..#")
        
        assertThat(Day21(exampleRules).fractal().take(3).last())
                .isEqualTo("##.##./#..#../....../##.##./#..#../......")

        assertThat(Day21(exampleRules).pixelsAfterNIterations(2))
                .isEqualTo(12)

//        ##.|##.
//        #..|#..
//        ...|...
//        ---+---
//        ##.|##.
//        #..|#..
//        ...|...

       
    }

    @Test
    fun name() {
        assertThat(Day21(exampleRules).allCombos(".#./..#/###")).contains(".#./..#/###")
        assertThat(Day21(exampleRules).rotationsToRules.keys).contains(".#./..#/###")
    }

    @Test
    fun stitch3s() {
        val stitched = Day21(exampleRules).stitch3s(listOf(".#./.../###", "#.#/.../...",
                ".../..#/#.#","###/#../.#."))
        assertThat(stitched).isEqualTo(".#.#.#/....../###.../...###/..##../#.#.#.")    
    }

    @Test
    fun stitch2s() {
        val stitched = Day21(exampleRules).stich2s(listOf(".#/..", ".#/..","##/..","#./##"))
        assertThat(stitched).isEqualTo(".#.#/..../###./..##")

        val stitched2 = Day21(exampleRules).stich2s(listOf(".#/.."))
        assertThat(stitched2).isEqualTo(".#/..")
    }

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