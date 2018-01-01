package com.pdwtech.aoc

class TuringMachine(val initialState: Char, val iterations: Int, val statesList: List<State>) {
    val states = statesList.associateBy { it.name }
    
    var tape : MutableMap<Long, Int> = mutableMapOf(Pair(0L,0))
    
    var currentState = initialState
    var cursor = 0L
    
    fun checksum() : Int {
        (1..iterations).forEach { step() }
        return tape.values.sum()
    }
    
    fun step() {
        val value = tape.getOrDefault(cursor, 0)
        
        states[currentState]!!.transitions
                .find { it.condition ==  value }
                .let { maybeTransition ->
                    val t = maybeTransition ?: throw IllegalStateException("Transition not found")
                    tape.put(cursor, t.write)
                    cursor += t.move
                    currentState = t.next
                }
        
    }
    

}



data class State(val name: Char, val transitions: List<Transition>)

data class Transition(val condition: Int, val write: Int, val move: Int, val next: Char)

fun parseTM(instructions: List<String>) : TuringMachine {
    val initialState = instructions[0][15]
    val iterations = Regex("""Perform a diagnostic checksum after (\d+) steps.""")
            .find(instructions[1])
            ?.groups
            ?.get(1)
            ?.value
            ?.toInt() 
            ?: throw IllegalStateException("Problem finding checksum steps")
    
    val states = instructions.drop(3).chunked(10).map(::parseState)
    return TuringMachine(initialState, iterations, states)
}

fun parseState(s : List<String>) : State {
    val name = s[0][9]
    val transitions = s.drop(1).dropLast(1).chunked(4).map(::parseTransition)
    return State(name, transitions)
}

fun parseTransition(t : List<String>) : Transition {
    val condition = "${t[0][26]}".toInt()
    val write = "${t[1][22]}".toInt()
    val move = if (t[2][27] == 'l') -1 else 1
    val next = t[3][26]
    return Transition(condition, write, move, next)
}