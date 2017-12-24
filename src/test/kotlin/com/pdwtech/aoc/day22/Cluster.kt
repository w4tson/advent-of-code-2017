package com.pdwtech.aoc.day22

import com.pdwtech.aoc.day03.Direction
import com.pdwtech.aoc.day22.InfectionStatus.*

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

enum class InfectionStatus {
    CLEAN,
    WEAKENED,
    INFECTED,
    FLAGGED
}

class Cluster(val initial: Map<Coord, InfectionStatus>) {
    
    val c = initial.toMutableMap()
    var direction = Direction.NORTH
    var position = Coord(0,0)
    var infected = 0L
    
    fun infectedAfterNBursts(n: Int) : Long {
        (1..n).forEach { burst() }
        return infected
    }

    fun infectedAfterNBursts2(n: Int) : Long {
        (1..n).forEach { burst2() }
        return infected
    }
    
    fun burst2() {
        when(c.getOrPut(position, { CLEAN })) {
            CLEAN -> {
                direction = direction.turn90AntiClockwise()
                c[position] = WEAKENED
            }
            WEAKENED -> {
                infected ++
                c[position] = INFECTED

            }
            INFECTED -> {
                direction = direction.turn90Clockwise()
                c[position] = FLAGGED

            }
            FLAGGED -> {
                direction = direction.reverse()
                c[position] = CLEAN
            }
        }
        position = position.advance(direction)
    }
    
    fun burst() {
        
        when(c.getOrPut(position, { CLEAN })) {
            CLEAN -> {
                direction = direction.turn90AntiClockwise()
                infected ++
                c[position] = INFECTED
            }
            else -> {
                direction = direction.turn90Clockwise()
                c[position] = CLEAN
            }
        }
        position = position.advance(direction)
    }
}