package com.pdwtech.aoc.day12

typealias Node = String
typealias Edge = Pair<Node, Node>

class Graph(val nodes: Set<Node>, val edges : List<Edge>) {


    fun neighbours(node: Node) : Set<Node> {
        return edges.filter { it.first == node }
                .flatMap { listOf(it.first, it.second) }
                .toSet()
    }



}