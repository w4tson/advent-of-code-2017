package com.pdwtech.aoc.day14

import com.pdwtech.aoc.day12.Graph



object GraphHelper {


    //TODO the termination of this is wrong.
    tailrec fun numOfGroups(g: Graph<Int>, acc: Int) : Int {
        println(acc)

        return when(g.nodes.size) {
            0 ->  acc
            else -> {
                val rest = notConnectedTo(g.nodes.first(), g)
                numOfGroups(rest, acc + 1 )
            }
        }
    }

    fun notConnectedTo(node: Int, g: Graph<Int>) : Graph<Int> {
        val nodes = g.nodes.filter { !hasPathTo(it, node, g) }.toSet()
        val edges = g.edges.filter { nodes.contains(it.first)|| nodes.contains(it.second) }.toList()
        return Graph(nodes, edges)
    }

    fun hasPathTo(name: Int, to: Int, g: Graph<Int>) : Boolean {
        val distances = g.nodes.associate{ Pair(it, Int.MAX_VALUE) }.toMutableMap()
        distances[name] = 0

        val unvisited = mutableSetOf(*g.nodes.toTypedArray())
        var current = name

        while(!arrivedOrExhausted(unvisited, distances, to)){
            val neighbours = g.neighbours(current)
            for (n in neighbours) {
                val tentativeDistance = distances.getValue(current) + 1
                if (tentativeDistance < distances.getValue(n))  {
                    distances[n] = tentativeDistance
                }
            }
            unvisited.remove(current)
            current = distances.entries
                    .filter { unvisited.contains(it.key) }
                    .minBy { it.value }?.key ?: throw Exception("Int Not found")

        }

        return !unvisited.contains(to)
    }

    fun arrivedOrExhausted(unvisited : Set<Int>, distances: Map<Int, Int>, destination: Int) : Boolean {
        val zeroVisited = !unvisited.contains(destination)
        val allInfinity = distances.entries
                .filter { unvisited.contains(it.key) }
                .all { it.value == Int.MAX_VALUE }
        return zeroVisited || allInfinity
    }
}