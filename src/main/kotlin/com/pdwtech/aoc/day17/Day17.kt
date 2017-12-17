package com.pdwtech.aoc.day17

import com.pdwtech.aoc.Aoc.circularIndexSeq

object Day17 {

    fun spin(skip: Int, n : Int = 2017) : Int {
        val buffer = spinArray(skip, n)
        val i = buffer.indexOf(2017)
        return buffer[i+1]
    }

    fun spinArray(skip: Int, n : Int) : IntArray {
        return spinOne(arrayOf(0).toIntArray(), 0, n, 1, skip)
    }

    tailrec fun spinOne(buffer: IntArray, currPos: Int, max: Int, counter: Int, skip: Int) : IntArray {
        return when(counter) {
            max+1 -> buffer
            else -> {
                val insertPos = buffer.circularIndexSeq(currPos).take(skip+1).last()
                val newBuffer = insertAfter(buffer, insertPos, counter)
                spinOne(newBuffer, insertPos+1, max, counter+1, skip)
            }
        }
    }

    fun insertAfter(buffer: IntArray, pos: Int, value: Int) : IntArray {
        return when (pos) {
            buffer.size - 1 -> (buffer + value)
            else -> (
                    buffer.slice(0..pos)
                            + listOf(value)
                            + if (pos + 1 != buffer.size) buffer.slice(pos + 1 until buffer.size) else emptyList()
                    ).toIntArray()
        }
    }

    fun part2(insertions: Int = 50_000_000, skip: Int = 328) : Int {
        return calcColumnOne(0, 0, insertions, 0, skip)
    }

    private tailrec fun calcColumnOne(currPos: Int, acc: Int, max: Int, counter: Int, skip: Int) : Int {
        return when(counter) {
            max -> acc
            else -> {
                val maybePos = currPos + skip
                val next = counter + 1
                val newPos = if (maybePos > currPos) (maybePos % next) + 1 else maybePos + 1
                val newAcc = if (newPos == 1) next else acc
                calcColumnOne(newPos, newAcc, max, next, skip)
            }
        }
    }
}