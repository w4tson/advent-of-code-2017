package com.pdwtech.aoc.day07

class Tree {

    private val inputRegex = Regex("""^(\w+) \((\d+)\)(?: -> )?(.*)?""")

    fun buildTree(input: List<String>) : Node {
        val nodeMap = input.associate { toNodePair(it) }
        val nodesByName = nodeMap.keys.associateBy { it.name }

        nodeMap.entries
                .filter { it.value.isNotEmpty() }
                .forEach {
                    it.key.children = it.value.map { nodesByName[it]!! }.toList()
                }

        val root = nodesByName[nameOfRoot(input)]!!

        nodeMap.keys.filter { !it.isBalanced() }.forEach {
            println("node ${it.name} is unbalanced. Children are: ")
            nodesByName[it.name]?.let { unbalanced ->
                unbalanced.children.forEach{ println("${it.name} ${it.combinedWeight()}")}
            }
        }

        return root
    }

    private fun toNodePair(s :String) : Pair<Node, List<String>> {
        val (name, weight, kids) = inputRegex.find(s)!!.destructured

        val children = when(kids) {
            "" -> emptyList()
            else -> kids.split(Regex(",\\s*")).toList()
        }

        return Pair(Node(name, weight.toInt()), children)
    }

    fun nameOfRoot(input: List<String>) : String {
        val col = input.flatMap { allNodeNames(it) }.toSet()
        val others  = input.flatMap { getSubtreeStrings(it)  }.toSet()
        val remainder = col.minus(others.asIterable())

        return remainder.first()
    }

    private fun getSubtreeStrings(s : String) : List<String> {
        val (_, _, rest) = inputRegex.find(s)!!.destructured
        return rest.split(Regex(",\\s*")).filter{ it.isNotBlank() }.toList()
    }

    private fun allNodeNames(s: String) : List<String> {
        val groupValues = inputRegex.find(s)!!.groupValues
        return when (groupValues[3]) {
            "" -> emptyList()
            else -> listOf(groupValues[1])
        }
    }
}