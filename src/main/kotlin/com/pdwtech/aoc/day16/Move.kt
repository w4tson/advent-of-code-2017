package com.pdwtech.aoc.day16


abstract class Move {
    abstract fun exec(data: MutableList<Char>)
}
data class Spin(val lastN : Int) : Move() {
    override fun exec(data: MutableList<Char>) {
        val start = data.size - lastN

        val lastNList = data.takeLast(lastN).toList()
        val firstNList = data.subList(0, start).toList()

        (0 until lastN).forEach {
            data[it] = lastNList[it]
        }

        firstNList.forEachIndexed { i, c ->
            data[i + lastN] = c
        }
    }
}

data class Exchange(val first : Int, val second: Int) : Move() {
    override fun exec(data: MutableList<Char>) {
        val a = data[first]
        data[first] = data[second]
        data[second] = a
    }
}

data class Partner(val first : Char, val second: Char) : Move() {
    override fun exec(data: MutableList<Char>) {
        val indexA = data.indexOf(first)
        val indexB = data.indexOf(second)
        data[indexA] = second
        data[indexB] = first
    }
}