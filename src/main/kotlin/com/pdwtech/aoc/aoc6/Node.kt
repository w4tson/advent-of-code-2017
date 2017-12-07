package com.pdwtech.aoc.aoc6


class Node(val name: String) {
    var children: MutableList<Node> = mutableListOf()

    fun hasChildren() : Boolean =  children.isNotEmpty()

    fun addChild(n : Node) {
        children + n
    }

    fun calcWeight() : Int = 0

}