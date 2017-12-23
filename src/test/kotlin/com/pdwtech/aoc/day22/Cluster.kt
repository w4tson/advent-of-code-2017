package com.pdwtech.aoc.day22

import com.pdwtech.aoc.day03.Direction

data class Coord(val x : Int, val y: Int){
    
    fun advance(direction: Direction) : Coord {
        return  when(direction) {
            Direction.NORTH -> Coord(x, y - 1)
            Direction.EAST -> Coord(x + 1, y)
            Direction.SOUTH -> Coord(x, y + 1)
            Direction.WEST -> Coord(x - 1, y)
        }
    }
}

class Cluster(val initial: Map<Coord, Boolean>) {
    
    val c = initial.toMutableMap()
    var direction = Direction.NORTH
    var position = Coord(0,0)
    var infected = 0L
    
    fun infectedAfterNBursts(n: Int) : Long {
        (1..n).forEach{ burst() }
        return infected
    }
    
    fun burst() {
        
        when(c.getOrPut(position, { false })) {
            false -> {
                direction = direction.turn90AntiClockwise()
                infected ++
                c[position] = true
            }
            else -> {
                direction = direction.turn90Clockwise()
                c[position] = false
            }
        }
        position = position.advance(direction)
    }
}