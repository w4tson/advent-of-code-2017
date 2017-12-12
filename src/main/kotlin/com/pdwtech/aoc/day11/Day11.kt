package com.pdwtech.aoc.day11

import java.lang.IllegalArgumentException
import java.lang.Math.abs

//   \ n  /
// nw +--+ ne
//   /    \
// -+      +-
//   \    /
// sw +--+ se
//   / s  \

//       \ y  /
//        +--+ -z
//       /    \
//  -x -+      +- x
//       \    /
//      z +--+
//       / -y \


class HexCoord(var x: Int =0, var y: Int = 0, var z: Int=0){

    var maxDist = 0

    fun move(direction: String) : HexCoord {
        when (direction) {
            "n"  -> { y += 1; z -= 1 }
            "ne" -> { x += 1; z -= 1 }
            "se" -> { x += 1; y -= 1 }
            "s"  -> { y -= 1; z += 1 }
            "sw" -> { x -= 1; z += 1 }
            "nw" -> { x -= 1; y += 1 }
            else -> throw IllegalArgumentException(direction)
        }

        val newDistance = distanceFrom(ORIGIN)

        maxDist = if (newDistance > maxDist) newDistance else maxDist

        return this
    }

    fun distanceFrom(from: HexCoord) : Int {
        return (abs(from.x - x) + abs(from.y - y) + abs(from.z - z)) /2
    }

    override fun toString(): String {
        return "($x, $y, $z)"
    }
}

val ORIGIN = HexCoord()

object Day11 {

    fun move(movements: List<String>) : HexCoord =
            movements.fold(HexCoord(), { acc, move -> acc.move(move) })

}