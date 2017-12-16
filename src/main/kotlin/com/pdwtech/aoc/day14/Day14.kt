package com.pdwtech.aoc.day14

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

    fun buildGraph(key: String) {
        val data = (0..127).flatMap { row ->
            Day10.part2("$key-$row")
                    .toCharArray()
                    .map { toBinary(it) }
                    .joinToString(separator = "")
                    .toCharArray()
                    .mapIndexed{ i, c-> if ("$c".toInt() == 0) -1 else i*row + i }
                    .asIterable()
        }.toIntArray()

        (0..20).map { data[it]}.joinToString().also { println(it) }
        println(data[137])

        val g: Map<Int, List<Int>> = data.associate { Pair(it, adjacentNodes(data, it)) }
        g.entries.take(10).forEach { println(it) }

        val edges = g.entries.flatMap { entry ->
            entry.value.flatMap { listOf(Pair(entry.key, it), Pair(entry.key, it))}
        }

//        val graph = Graph<Int>(g.keys, edges)
//        GraphHelper.numOfGroups(graph, 0)

    }

    fun adjacentIndicies(index: Int) : List<Int> {
        return arrayOf(index+128, index+1, index-1, index-128)
                .filter { it >= 0 && it <= 128*128 }
    }

    fun adjacentNodes(data: IntArray, index: Int) : List<Int> {
        return adjacentIndicies(index)
                .filter { data.get(it) != -1 }
    }


}