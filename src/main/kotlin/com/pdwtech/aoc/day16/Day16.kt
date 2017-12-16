package com.pdwtech.aoc.day16

import com.pdwtech.aoc.Aoc.readInput



object Day16 {
    private val aToP = "abcdefghijklmnop"
    private val actualData = aToP.toCharArray().toMutableList()
    private val actualMoves = parseMoves(readInput("aoc16")[0])

    fun parseMoves(input: String) : List<Move> = input.split(",").map { toMove(it) }

    fun oneDanceIteration(moves : List<Move> = actualMoves, data : MutableList<Char> = actualData) : String {
        return dance(moves, data).joinToString(separator = "")
    }

    fun nDanceIterations(moves : List<Move> = actualMoves, data : MutableList<Char> = actualData, iteration: Int) : String {
        val original = data.toList().toMutableList()
        val repeats = repeatsAfter(moves, data)

        println("repeats $repeats times")
        val n = iteration % repeats
        (1..n).forEach { dance(moves, original) }

        return original.joinToString(separator = "")
    }

    private fun dance(moves: List<Move>, data : MutableList<Char>) : MutableList<Char> {
        moves.forEach { it.exec(data) }
        return data
    }

    private fun repeatsAfter(moves: List<Move>, data : MutableList<Char>) : Int {
        val orig = data.toList().joinToString()
        return (1..Int.MAX_VALUE).takeWhile {
            dance(moves, data)
            data.joinToString() != orig
        }.last() + 1
    }

    private fun toMove(move: String) : Move = when(move.first()) {
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