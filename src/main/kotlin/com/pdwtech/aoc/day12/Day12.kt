package com.pdwtech.aoc.Day12

object Day12 {

    fun groups(g: Map<Int, List<Int>>) : Set<Set<Int>> {
        return g.entries.fold(setOf()) { acc, (node, children)  ->
            when(acc.toList().flatten().contains(node)) {
                true -> acc
                else -> acc + setOf(findAllInGroup(g, children.toSet(), setOf(node)))
            }
        }
    }

    tailrec private fun findAllInGroup(g: Map<Int, List<Int>>, unvisited: Set<Int>, acc: Set<Int>) : Set<Int> {
        return when {
            unvisited.isEmpty() -> acc
            else -> {
                val visited = unvisited.first()
                val u = unvisited + if (!acc.contains(visited)) g.getOrDefault(visited, emptyList()) else emptyList()

                findAllInGroup(g, u.minus(visited), acc + visited)
            }
        }
    }
}