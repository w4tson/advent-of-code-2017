package com.pdwtech.aoc.day14

import com.pdwtech.aoc.day12.Day12
import com.pdwtech.aoc.day10.Day10

object Day14 {

    fun toBinary(hexChar: Char) : String {
        return "$hexChar".toInt(16).toString(2).padStart(4,'0')
    }

    fun usedSquares(key: String) : Int {
        return (0..127).sumBy {
            Day10.part2("$key-$it")
                    .toCharArray()
                    .map{ toBinary(it) }
                    .joinToString()
                    .count { it == '1' }
        }
    }

    fun countRegions(key: String) : Int {
        val data = (0..127).flatMap { row ->
            Day10.part2("$key-$row")
                    .toCharArray()
                    .map { toBinary(it) }
                    .joinToString(separator = "")
                    .toCharArray()
                    .mapIndexed { i, c-> if ("$c".toInt() == 0) -1 else row*128 + i }
                    .asIterable()
        }.toIntArray()

        data.forEachIndexed { i, a ->
            val o = if (a ==-1) "." else "#"
            print(o.padStart(1,' '))
            if (i % 128 == 127 && i != 0) {
                println()
            }
        }

        val g: Map<Int, List<Int>> = data.filter{ it >=0 }.associate { Pair(it, adjacentNodes(data, it)) }
        g.values.count { it.size == 5 }.also { println(it) }
        return Day12.groups(g).size
    }

    fun adjacentIndicies(index: Int) : List<Int> {
        var i = mutableListOf<Int>()

        if (index % 128 != 0 ) i.add(index-1)
        if (index % 128 != 127 ) i.add(index+1)
        if (index  > 127 ) i.add(index-128)
        if (index < 127*128 ) i.add(index+128)

        return i
    }

    fun adjacentNodes(data: IntArray, index: Int) : List<Int> {
        return if (data[index] == -1) emptyList() else adjacentIndicies(index)
                .filter { data.get(it) != -1 }
    }


}