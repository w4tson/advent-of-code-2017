package com.pdewtech.aoc

import com.pdwtech.aoc.aoc6.Direction.*
import com.pdwtech.aoc.aoc6.Grid
import com.pdwtech.aoc.aoc6.GridCell
import com.pdwtech.aoc.aoc6.LocStat
import com.pdwtech.aoc.aoc6.Location
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Aoc3BTest {

    @Test
    fun testLocations() {
        val around = Location(3, 4).locationsAroundMe()
        assertThat(around).contains(
                Location(4, 4),
                Location(4, 5),
                Location(3, 5),
                Location(2, 5),
                Location(2, 4),
                Location(2, 3),
                Location(3, 3),
                Location(4, 3)
        )
    }

    @Test
    fun shouldTurn() {
        assertThat(GridCell(0, LocStat(Location(0, 0), EAST), 1).shouldTurn()).isFalse()
        assertThat(GridCell(1, LocStat(Location(1, 0), EAST), 1).shouldTurn()).isTrue()
        assertThat(GridCell(2, LocStat(Location(1, 1), NORTH), 1).shouldTurn()).isTrue()
        assertThat(GridCell(3, LocStat(Location(1, 1), NORTH), 1).shouldTurn()).isFalse()
        assertThat(GridCell(4, LocStat(Location(1, 1), NORTH), 1).shouldTurn()).isTrue()
        assertThat(GridCell(5, LocStat(Location(1, 1), NORTH), 1).shouldTurn()).isFalse()
        assertThat(GridCell(6, LocStat(Location(1, 1), NORTH), 1).shouldTurn()).isTrue()
        assertThat(GridCell(7, LocStat(Location(1, 1), NORTH), 1).shouldTurn()).isFalse()
        assertThat(GridCell(8, LocStat(Location(1, 1), NORTH), 1).shouldTurn()).isFalse()
        assertThat(GridCell(9, LocStat(Location(1, 1), NORTH), 1).shouldTurn()).isTrue()
        assertThat(GridCell(10, LocStat(Location(1, 1), NORTH), 1).shouldTurn()).isFalse()
        assertThat(GridCell(11, LocStat(Location(1, 1), NORTH), 1).shouldTurn()).isFalse()
        assertThat(GridCell(12, LocStat(Location(1, 1), NORTH), 1).shouldTurn()).isTrue()
        assertThat(GridCell(13, LocStat(Location(1, 1), NORTH), 1).shouldTurn()).isFalse()
    }

    @Test
    fun example() {

        val result = Grid().sequence().take(23).toList()
        println("result = ${result}")
        assertThat(result)
                .isEqualTo(listOf(1, 1, 2, 4, 5, 10, 11, 23, 25, 26, 54, 57, 59, 122, 133, 142, 147, 304, 330, 351, 362, 747, 806))
//        147  142  133  122   59
//        304    5    4    2   57
//        330   10    1    1   54
//        351   11   23   25   26
//        362  747  806--->   ...
    }

    @Test
    fun actual() {
        val result = Grid().sequence().first { it > 289326 }
        println("result = $result")
    }
}
