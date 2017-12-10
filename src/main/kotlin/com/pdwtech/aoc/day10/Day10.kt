package com.pdwtech.aoc.day10

import com.pdwtech.aoc.Aoc.circularIndexSeq


class State(var index: Int = 0,
            var skip : Int = 0,
            val hashSize: Int){

    val hash: Array<Int> by lazy { IntRange(0, hashSize -1).toList().toTypedArray() }
}

object Day10 {

    fun solvePuzzle(input : Array<Int>, hashSize: Int = 256) : Int {
        val result = input.fold(State(hashSize = hashSize),{ acc, next ->
            val slice = acc.hash.circularIndexSeq(acc.index).take(next).toList().also {  }
            val rev = acc.hash.slice(slice.asIterable()).reversed().iterator()
            slice.forEach { acc.hash[it] =  rev.next() }
            acc.index = acc.hash.circularIndexSeq(acc.index).take(next + acc.skip + 1).last()
            acc.skip += 1
            acc
        })

        return result.hash[0] * result.hash[1]
     }
}