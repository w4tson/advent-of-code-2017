package com.pdwtech.aoc.aoc6

import com.pdwtech.aoc.aoc6.Direction.EAST

class Grid() {

    var cells: Array<GridCell> = arrayOf(GridCell(0, LocStat(Location(0, 0), EAST), 1))

    fun sequence() : Sequence<Int> {
        return generateSequence { this.next() }
    }

    fun next() : Int {
        val previous = cells.last()
        val newPosition = previous.nextLocStat()
        val newValue = calcNewValue(newPosition)

        val nextCell = GridCell(cells.size, newPosition, newValue)

        cells = cells.plus(nextCell)

        return previous.value
    }

    fun calcNewValue(position: LocStat) : Int {
        val surroundingLocations = position.location.locationsAroundMe()
        return cells.filter { surroundingLocations.contains(it.locStat.location) }
                .map { it.value }
                .sum()
    }
}