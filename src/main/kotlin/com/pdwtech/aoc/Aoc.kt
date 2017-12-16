package com.pdwtech.aoc

import java.nio.file.Files
import java.nio.file.Paths

object Aoc {

    fun parseInt(c : Char) : Int {
        return "${c}".toInt()
    }

    fun readInput(fileName: String) : List<String> {
        val resource = javaClass.getResource("/$fileName")
        val toURI = resource.toURI()
        return Files.readAllLines(Paths.get(toURI))
    }

    fun toIntArray(row : String) : IntArray = row.split(Regex("\\s")).map { it.toInt() }.toIntArray()

    val IntArray.head: Int
        get() = this[0]

    val IntArray.tail: IntArray
        get() = drop(1).toIntArray()

    fun String.splitWhitespace() : List<String> {
        return split(Regex("\\s"))
    }

    fun <T> Array<T>.circularIndexSeq(start: Int): Sequence<Int> {
        return generateSequence(start, {
             when(it) {
                this.size - 1 -> 0
                else          -> it +1
            }
        })
    }

    fun Array<Int>.indexOfMax() : Int = this.indexOf(this.max())


}

