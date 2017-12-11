package com.pdwtech.aoc.day10

import com.pdwtech.aoc.Aoc.circularIndexSeq


class State(var index: Int = 0, var skip : Int = 0, val hashSize: Int) {
    val hash: Array<Int> by lazy { IntRange(0, hashSize -1).toList().toTypedArray() }
}

object Day10 {

    fun part1(input : Array<Int>, hashSize: Int = 256) : Int {
        val state = State(hashSize = hashSize)
        knotHashRound(input, state)
        return state.hash[0] * state.hash[1]
     }

    fun part2(input : String) : String {
        val modifiedInput = input.toCharArray().map { it.toInt() }
                .map { "$it".toInt() }.toTypedArray() + arrayOf(17, 31, 73, 47, 23)

        val state = State(hashSize = 256)

        for (i in 1..64) {
            knotHashRound(modifiedInput, state)
        }

        return (0..15)
                .map { xor(state.hash.slice(16*it..16*it+15)) }
                .map { toHex(it) }
                .joinToString(separator = "")
    }

    fun knotHashRound(input: Array<Int>, state: State) : State {
        return input.fold(state, { acc, next ->
            val slice = acc.hash.circularIndexSeq(acc.index).take(next).toList()
            val rev = acc.hash.slice(slice.asIterable()).reversed().iterator()
            slice.forEach { acc.hash[it] =  rev.next() }
            acc.index = acc.hash.circularIndexSeq(acc.index).take(next + acc.skip + 1).last()
            acc.skip += 1
            acc
        })
    }

    fun xor(input: List<Int>) = input.reduce({ a, b -> a.xor(b)})

    fun toHex(n : Int) : String = Integer.toHexString(n).padStart(length = 2, padChar = '0')

}