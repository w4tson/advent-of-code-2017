package com.pdwtech.aoc.day12

typealias Node = String
typealias Edge = Pair<Node, Node>

class Graph<T>(val nodes: Set<T>, val edges : List<Pair<T, T>>) {

    fun neighbours(node: T) : Set<T> {
        return edges.filter { it.first == node }
                .flatMap { listOf(it.first, it.second) }
                .toSet()
    }



}