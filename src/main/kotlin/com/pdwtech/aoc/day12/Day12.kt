package com.pdwtech.aoc.day12


object Day12 {

    fun part1(input : List<String>) : Int {
        val g = buildGraph(input)

        return g.nodes.count { hasPathTo(it,"0", g) }
    }

    fun part2(input : List<String>) {
        val g = buildGraph(input)
        val remainingGraph = notConnectedTo("0", g)
        val groups = numOfGroups(remainingGraph, 0)
        println("groups=$groups")

    }

    //TODO the termination of this is wrong.
    tailrec fun numOfGroups(g: Graph<Node>, acc: Int) : Int {
        //println("GroupFound! acc : ${acc} new node: ${g.nodes.first()} node size : ${g.nodes.size} nodes ${g.nodes}")
        if (acc % 20 == 0) {
            println(acc)
        }

        return when(g.nodes.size) {
            0 ->  acc
            else -> {
                val rest = notConnectedTo(g.nodes.first(), g)
                numOfGroups(rest, acc + 1 )
            }
        }
    }

    fun notConnectedTo(node: Node, g: Graph<Node>) : Graph<Node> {
        val nodes = g.nodes.filter { !hasPathTo(it, node, g) }.toSet()
        val edges = g.edges.filter { nodes.contains(it.first)|| nodes.contains(it.second) }.toList()
        return Graph(nodes, edges)
    }

    fun hasPathTo(name: Node, to: Node, g: Graph<Node>) : Boolean {
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
                    .minBy { it.value }?.key ?: throw Exception("Node Not found")

        }

        return !unvisited.contains(to)
    }

    fun arrivedOrExhausted(unvisited : Set<Node>, distances: Map<Node, Int>, destination: Node) : Boolean {
        val zeroVisited = !unvisited.contains(destination)
        val allInfinity = distances.entries
                            .filter { unvisited.contains(it.key) }
                            .all { it.value == Int.MAX_VALUE }
        return zeroVisited || allInfinity
    }

    fun buildGraph(input: List<String>) : Graph<Node> {
        var nodes: MutableSet<Node> = mutableSetOf()
        var edges: MutableList<Edge> = mutableListOf()

        input.forEach{
            val (name, adjacent) = parse(it)
            nodes.add(name)
            nodes.addAll(adjacent)
            edges.addAll(adjacent.flatMap { listOf(Edge(name, it), Edge(it, name)) })
        }

        return Graph(nodes, edges)
    }

    fun parse(line : String) : Pair<String, List<String>> {
        val (name, adjacentString) = inputRegex.find(line)!!.destructured
        val adjacent = when(adjacentString) {
            "" -> emptyList()
            else -> adjacentString.split(Regex(",\\s*")).toList()
        }
        return Pair(name, adjacent)
    }

    private val inputRegex = Regex("""^(\d+) <-> (.*)?""")

}