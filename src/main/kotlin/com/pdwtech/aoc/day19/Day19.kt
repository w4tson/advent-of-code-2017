package com.pdwtech.aoc.day19

import com.pdwtech.aoc.day03.Direction

data class LettersDistance(val letters: String, val distance: Long)

class Location(val x: Int, val y: Int, val direction: Direction) {
    fun nextLocation() = when(direction) {
            Direction.NORTH -> Location(x, y-1, direction)
            Direction.EAST -> Location(x+1, y, direction)
            Direction.SOUTH -> Location(x, y+1, direction)
            Direction.WEST -> Location(x-1, y, direction)
    }
}

class Day19(val input: List<String>) {

    private val width = input[0].length
    private val height = input.size

    fun walk() : LettersDistance {
        val initialPos = Location(input[0].indexOfFirst { !it.isWhitespace() }, 0, Direction.SOUTH)
        return walk(initialPos, LettersDistance("", 0))
    }
    
    tailrec fun walk(pos: Location, acc: LettersDistance) : LettersDistance {
        return when (hasEnded(pos)) {
            true -> acc
            else -> {
                val currChar = charAt(pos)
                val newLetters = if (currChar.isLetter()) acc.letters + currChar else acc.letters
                val newPos = if (currChar == '+') turn(pos) else pos.nextLocation()
                walk(newPos, LettersDistance(newLetters, acc.distance+1))
            }
        }
    }

    fun turn(pos: Location) : Location {
        val westOne = Location(pos.x-1, pos.y, Direction.WEST)
        val northOne = Location(pos.x, pos.y-1, Direction.NORTH)
        val eastOne = Location(pos.x+1, pos.y, Direction.EAST)
        val southOne = Location(pos.x, pos.y+1, Direction.SOUTH)
        return when (pos.direction) {
            Direction.SOUTH, Direction.NORTH -> if (charAt(pos.x-1, pos.y).isEastWest()) westOne else eastOne
            Direction.EAST, Direction.WEST -> if (charAt(pos.x, pos.y+1).isNorthSouth()) southOne else northOne
        }
    }
    
    fun Char.isNorthSouth() : Boolean = this == '|' || this.isLetter()
    
    fun Char.isEastWest() : Boolean = this == '-' || this.isLetter()

    fun hasEnded(pos: Location) : Boolean = outOfBounds(pos) || charAt(pos).isWhitespace()

    fun charAt(pos: Location) : Char = charAt(pos.x, pos.y)

    fun charAt(x: Int, y: Int) : Char = input[y][x]

    fun outOfBounds(pos: Location) : Boolean = pos.x >= width || pos.x < 0 || pos.y < 0 || pos.y >= height

}