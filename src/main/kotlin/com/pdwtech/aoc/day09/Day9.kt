package com.pdwtech.aoc.day09

import com.pdwtech.aoc.day09.Mode.GARBAGE_START


object Part1 {

    fun solvePart1(stream : String) : Int = solve(stream.toCharArray(), 0 , State()).first

    fun solvePart2(stream : String) : Int = solve(stream.toCharArray(), 0 , State()).second

    tailrec fun solve(stream : CharArray, index: Int, state: State) : Pair<Int, Int> {
        return when(index) {
            stream.size -> Pair(state.totalGroups, state.totalGarbage)
            else -> solve(stream, index+1, state.next(stream[index]))
        }
    }
}

data class State(private var mode : Mode = Mode.GROUP_START,
                 private var depth: Int = 0,
                 private var escaping: Boolean = false,
                 var totalGroups: Int = 0,
                 var totalGarbage: Int = 0){

    fun next(ch : Char) : State {
        when {
            escaping  -> escaping = false
            ch == '!' -> escaping = true
            ch == '>' -> mode = Mode.GARBAGE_END
            ch == '<' && mode != GARBAGE_START -> mode = GARBAGE_START
            ch == '{' && mode != GARBAGE_START -> depth += 1
            ch == '}' && mode != GARBAGE_START -> { totalGroups += depth; depth -= 1 }
            mode == GARBAGE_START -> totalGarbage += 1
            else -> Unit
        }
        return this
    }
}

enum class Mode {
    GROUP_START,
    GROUP_END,
    ESCAPING,
    GARBAGE_START,
    GARBAGE_END
}