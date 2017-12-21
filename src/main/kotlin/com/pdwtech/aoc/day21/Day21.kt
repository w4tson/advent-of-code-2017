package com.pdwtech.aoc.day21

import java.lang.Math.pow

class Day21(val rulesStr : List<String>) {
    
    private val initial = ".#./..#/###"

    private val rules = rulesStr.map {
        val (k, v) = it.split(" => ")
        Pair(k, v)
    }.toMap()
    
    private val rotationsToRules = rules.keys.flatMap { rule ->  allCombos(rule).map { Pair(rule, it) } }.toMap()
    
    fun part1(n : Int) {
//        generateSequence(initial, { 
//            when(size(it) % 2) {
//                0 -> splitInto2By2(it)
//            }
        }
    }
    
    //TODO
    fun stich2s(list: List<String>, width: Int) : String {
        val size = size(list[0])
        val w = width / 2
        
        var str = ""

        (0 until w).map { i ->
            val row = list.slice(i*w until w+i)
            row.map { it.slice(0 until 2) }.joinToString(separator = "") + "/" +
            row.map { it.slice(2 until 4) }.joinToString(separator = "") 
        }
        return ""
    }

    fun splitInto3By3(square: String) : List<String> {
        val size = size(square)
        val newWidth = size / 3
        val s = square.filter { it != '/' }

        var list = mutableListOf<String>()
        
        for (y in 0 until newWidth) {
            for (x in 0 until newWidth) {
                val yoffset = y*size*3
                val xoffset = x * 3
                val n = yoffset + xoffset

                list.add(s.slice(n..n+2) + '/' +
                        s.slice(n+size..n+size+2) + '/' +
                        s.slice(n+size+size..n+size+size+2))
            }
        }
        return list
    }
    
    fun splitInto2By2(square : String) : List<String> {
        val size = size(square)
        val newWidth = size / 2
        val s = square.filter { it != '/' }

        var list = mutableListOf<String>()

        for (y in 0 until newWidth) {
            for (x in 0 until newWidth) {
                val yoffset = y*size*2
                val xoffset = x * 2
                val n = yoffset + xoffset

                list.add(s.slice(n..n+1) + '/' +
                        s.slice(n+size..n+size+1))
            }
        }
        return list
    }
    
    fun allCombos(square: String) : List<String> = allRotations(square) + allRotations(flipHorizontal(square)) + allRotations(flipVertical(square))
    
    fun allRotations(square: String) : List<String> = (1..3)
            .fold(listOf(), { acc, c -> acc + if (acc.isNotEmpty()) rotate(acc.last()) else rotate(square) })
        
    fun rotate(square: String) : String = reverse(transpose(square))
    
    fun transpose(square: String) : String {
        val size = size(square)
        val s = square.filter { it != '/' }
        return (0 until size).map { num ->
            s.foldIndexed("", { i, acc, c -> if (i % size == num) acc + c else acc })
        }.joinToString(separator = "/")
    }
    
    fun reverse(square: String) : String {
        return square.split('/').reversed().joinToString(separator = "/")
    }
        
    fun flipHorizontal(square : String) : String = square.split('/').reversed().joinToString(separator = "/")
    
    fun flipVertical(square : String) : String {
        return square.split('/').map { it.reversed() }.joinToString(separator = "/")
    }
    
    fun size(square: String) : Int = square.indexOfFirst { it == '/' }
    
    fun part1() {
        // String to Int representation
        // 
        // map possible input permutations -> output
    }
}