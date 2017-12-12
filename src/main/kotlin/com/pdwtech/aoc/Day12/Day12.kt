package com.pdwtech.aoc.Day12


typealias Node = String
typealias Edge = Pair<Node, Node>

data class Graph(val input : List<String>) {
    private val inputRegex = Regex("""^(\d+) <-> (.*)?""")

    var nodes: MutableSet<Node> = mutableSetOf()
    var edges: MutableList<Edge> = mutableListOf()

    fun neighbours(node: Node) : Set<Node> {
        return edges.filter { it.first == node }
                .flatMap { listOf(it.first, it.second) }
                .toSet()
    }

    init {
        input.forEach{
            val (name, adjacent) = parse(it)
            nodes.add(name)
            nodes.addAll(adjacent)
            edges.addAll(adjacent.flatMap { listOf(Edge(name, it), Edge(it, name)) })
        }
    }

    fun parse(line : String) : Pair<String, List<String>> {
        val (name, adjacentString) = inputRegex.find(line)!!.destructured
        val adjacent = when(adjacentString) {
            "" -> emptyList()
            else -> adjacentString.split(Regex(",\\s*")).toList()
        }


        return Pair(name, adjacent)
    }

}



object Day12 {

    fun part1(input : List<String>) : Int {
        val g = Graph(input)

        return g.nodes.count { hasPathToZero(it, g) }

    }

    fun hasPathToZero(name: Node, g: Graph) : Boolean {
        val distances = g.nodes.associate{ Pair(it, Int.MAX_VALUE) }.toMutableMap()
        distances[name] = 0

        val unvisited = mutableSetOf<Node>()
        unvisited.addAll(g.nodes)
//        unvisited.remove(name)
        var current = name

        while(!arrivedOrExhausted(unvisited, distances)){
//            println(unvisited)
            val neighbours = g.neighbours(current)
            for (n in neighbours) {
                val tentativeDistance = distances[current]!! + 1
                when  {
                    tentativeDistance < distances[n]!! -> distances[n] = tentativeDistance

                }
            }
            unvisited.remove(current)
//            println(unvisited)
            current = distances.entries
                    .filter { unvisited.contains(it.key) }
                    .minBy { it.value }!!.key

//            println("new min = $current (${distances[current] == Int.MAX_VALUE}) (max val = ${Int.MAX_VALUE})")
//            println(distances)
        }

        return !unvisited.contains("0")
    }

    fun arrivedOrExhausted(unvisited : Set<Node>, distances: Map<Node, Int>) : Boolean {
        val zeroVisited = !unvisited.contains("0")
        val allInfinity = distances.entries
                            .filter { unvisited.contains(it.key) }
                            .all { it.value == Int.MAX_VALUE }
//        println("zeroVisited = $zeroVisited  allInfinity = $allInfinity")
        return zeroVisited || allInfinity
    }



}