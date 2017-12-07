package com.pdwtech.aoc.day3

import com.pdwtech.aoc.day3.Direction.*


data class GridCell(val index: Int, val locStat: LocStat, val value : Int) {

    fun shouldTurn() : Boolean {
        return when(index) {
            0 -> false
            1 -> true
            else -> calcAllCorners().isACorner(index)
        }
    }

    fun nextLocStat() : LocStat = when(shouldTurn()) {
            true -> locStat.turnAndAdvance()
            else -> locStat.advance()
        }

    fun calcAllCorners() : Corners {
        val br = calcCorner(index)
        val br2 = calcCorner(index-1)
        val ring = ring(br)
        val side = width(ring) -1
        return Corners(br2 + 1, br - side, br - 2 * side, br - 3 * side)
    }

    fun calcCorner(index : Int) : Int = cornerSequence().first { it >= index }

    fun ring(bottomRight: Int): Int = cornerSequence().indexOf(bottomRight) //ZERO based

    fun width(ring: Int) : Int = (ring+1)* 2 -1

    fun cornerSequence(): Sequence<Int> {
        return generateSequence(0, { (Math.pow((Math.sqrt(it+1.toDouble())+2.0), 2.0)-1).toInt() })
    }
}

enum class Direction {
    NORTH { override fun turn90AntiClockwise() = WEST  },
    SOUTH { override fun turn90AntiClockwise() = EAST  },
    WEST  { override fun turn90AntiClockwise() = SOUTH },
    EAST  { override fun turn90AntiClockwise() = NORTH };

    abstract fun turn90AntiClockwise(): Direction
}

data class Location(val x: Int, val y: Int) {
    fun locationsAroundMe() : List<Location> {
        return listOf(
                Location(x + 1, y),
                Location(x + 1, y + 1),
                Location(x, y + 1),
                Location(x - 1, y + 1),
                Location(x - 1, y),
                Location(x - 1, y - 1),
                Location(x, y - 1),
                Location(x + 1, y - 1)
        )
    }
}

data class LocStat(val location: Location, val direction: Direction) {

    fun turnAndAdvance() : LocStat {
        val newDirection = direction.turn90AntiClockwise()
        return LocStat(location, newDirection).advance()
    }

    fun advance() : LocStat {
        val newLocation = when(direction) {
            NORTH -> Location(location.x, location.y + 1)
            EAST -> Location(location.x + 1, location.y)
            SOUTH -> Location(location.x, location.y - 1)
            WEST  -> Location(location.x - 1, location.y)
        }
        return LocStat(newLocation, direction)
    }
}

data class Corners(val bottomRight: Int,
                   val bottomLeft: Int,
                   val topLeft: Int,
                   val topRight: Int) {

    fun isACorner(n : Int) : Boolean {
        return when(n) {
            bottomRight -> true
            bottomLeft -> true
            topLeft -> true
            topRight -> true
            else -> false
        }
    }
}

