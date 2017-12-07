package com.pdwtech.aoc.day7


class Node(val name: String, private val weight: Int) {
    var children: List<Node> = listOf()

    fun combinedWeight() : Int = weight + children.sumBy { it.combinedWeight() }

    fun isBalanced() : Boolean = when(children.size) {
            0 -> true
            else ->  children.map{ it.combinedWeight() }.toSet().size == 1
        }
}