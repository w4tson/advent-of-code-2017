package com.pdwtech.aoc

import com.pdwtech.aoc.Aoc.readInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class TuringMachineTest {
    
    val exampleInput = readInput("aoc25-example")
    val actualInput = readInput("aoc25")
    val exampleTM = parseTM(exampleInput)
    

    @Test
    fun part1() {
        val turingMachine = parseTM(actualInput)
        println(turingMachine.checksum())

    }

    @Test
    fun example() {
        assertThat(exampleTM.checksum()).isEqualTo(3)
    }

    @Test
    fun testParse() {
        assertThat(exampleTM.initialState).isEqualTo('A')    
        assertThat(exampleTM.iterations).isEqualTo(6)    
        assertThat(exampleTM.states.keys).containsExactly('A', 'B')
        assertThat(exampleTM.states['A']?.transitions)
                .containsExactly(
                        Transition(0,1,1,'B'),
                        Transition(1,0, -1, 'B')
                )
    }
}