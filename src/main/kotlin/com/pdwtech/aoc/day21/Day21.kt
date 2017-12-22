package com.pdwtech.aoc.day21

import java.lang.Math.pow

class Day21(val rulesStr : List<String>) {
    
    val initial = ".#./..#/###"

    val rules = rulesStr.map {
        val (k, v) = it.split(" => ")
        Pair(k, v)
    }.toMap()
    
    val rotationsToRules = rules.keys.flatMap { rule ->  allCombos(rule).map { Pair(it, rule) } }.toMap() 
    
    fun part1() : Sequence<String> {
        return generateSequence(initial, { 
             when(size(it) % 2) {
                0 -> stich2s(splitInto2By2(it).map {
                    val r = rotationsToRules[it] ?: throw Exception("(2) Rule not found for $it")


                    rules[r]!! 
                })
                else -> stitch3s(splitInto3By3(it).map { 
                    println(it)
                    val r = rotationsToRules[it] ?: throw Exception("(3) Rule not found for $it")
                    println("$it is rules $r => ${rules[r]!!} ")
                    rules[r]!! 
                })
            }
        })
    }

    fun stitch3s(list: List<String>) : String {
        val resultantWidth = Math.sqrt(list.sumBy { it.length }.toDouble())
        val w = resultantWidth.toInt() / 4

        return (0 until w).map { i ->
            val row = list.slice(i*w until i*w+w)

            row.map { it.stripSlashes().slice(0 until 4) }.joinToString(separator = "") + "/" +
            row.map { it.stripSlashes().slice(4 until 8) }.joinToString(separator = "") + "/" +
            row.map { it.stripSlashes().slice(8 until 12) }.joinToString(separator = "") + "/" +
            row.map { it.stripSlashes().slice(12 until 16) }.joinToString(separator = "") 
        }.joinToString(separator = "/")
    }
    
    fun stich2s(list: List<String>) : String {
        val resultantWidth = Math.sqrt(list.sumBy { it.length }.toDouble())
        val w = resultantWidth.toInt() / 3
        
        return (0 until w).map { i ->
            val row = list.slice(i*w until i*w+w)
            
            row.map { it.stripSlashes().slice(0 until 3) }.joinToString(separator = "") + "/" +
            row.map { it.stripSlashes().slice(3 until 6) }.joinToString(separator = "") + "/" + 
            row.map { it.stripSlashes().slice(6 until 9) }.joinToString(separator = "") 
        }.joinToString(separator = "/")
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
    
    fun allCombos(square: String) : List<String> = allRotations(square) + allRotations(flipHorizontal(square)) + allRotations(flipVertical(square)) + listOf(square)
    
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
    
    fun String.stripSlashes() : String = this.filter { it != '/' }
}