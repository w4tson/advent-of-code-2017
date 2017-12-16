package com.pdwtech.aoc

import com.pdwtech.aoc.Aoc.readInput

abstract class Move
data class Spin(val lastN : Int) : Move() {

}

data class Exchange(val first : Int, val second: Int) : Move() {

}

data class Partner(val first : Char, val second: Char) : Move() {

}


object Day16 {
    private val aToP = "abcdefghijklmnop"
    private val actualData = aToP.toCharArray().toMutableList()
    private val actualMoves = parseMoves(readInput("aoc16")[0])


    fun oneDanceIteration(moves : List<Move> = actualMoves, data : MutableList<Char> = actualData) : String {
        return dance(moves, data).joinToString(separator = "")
    }

    fun nDanceIterations(moves : List<Move> = actualMoves, data : MutableList<Char> = actualData, iteration: Int) : String {
        val original = data.toList().toMutableList()
        val repeats = repeatNumber(moves, data)

        println("repeats $repeats times")
        val n = iteration % repeats
        (1..n).forEach { dance(moves, original) }

        return original.joinToString(separator = "")
    }

    fun repeatNumber(moves: List<Move>, data : MutableList<Char>) : Int {
        val orig = data.toList().joinToString()
        return (1..Int.MAX_VALUE).takeWhile {
            dance(moves, data)
            data.joinToString() != orig
        }.last() + 1
    }

    fun dance(moves: List<Move>, data : MutableList<Char>) : MutableList<Char> {
        return moves.fold(data) { acc, move ->
            when(move) {
                is Spin -> spin(data, move.lastN)
                is Exchange -> {
                    val a = data[move.first]
                    data[move.first] = data[move.second]
                    data[move.second] = a
                    data
                }
                is Partner -> {
                    val indexA = data.indexOf(move.first)
                    val indexB = data.indexOf(move.second)
                    data[indexA] = move.second
                    data[indexB] = move.first
                    data
                }
                else -> throw Exception("Unknown move")
            }
        }
    }

    fun spin(data: MutableList<Char>, lastN : Int) : MutableList<Char> {
        val start = data.size-lastN

        val lastNList = data.takeLast(lastN).toList()
        val firstNList = data.subList(0,start).toList()

        (0 until lastN).forEach{
            data[it] = lastNList[it]
        }

        firstNList.forEachIndexed { i, c ->
            data[i + lastN] = c
        }

        return data
    }

    fun parseMoves(input: String) : List<Move> = input.split(",").map { toMove(it) }

    fun toMove(move: String) : Move = when(move.first()) {
            's' -> Spin(move.drop(1).toInt())
            'x' -> {
                    val (first, second) = move.drop(1).split('/')
                    Exchange(first.toInt(), second.toInt())
                }
            'p' -> {
                val (first, second) = move.drop(1).split('/')
                Partner(first.toCharArray().first(), second.toCharArray().first())
            }
            else -> throw Exception("Unknown move")
        }
}