package com.pdwtech.aoc.day8


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

data class State(var mode : Mode = Mode.GROUP_START,
                 var depth: Int = 0,
                 var escaping: Boolean = false,
                 var totalGroups: Int = 0,
                 var totalGarbage: Int = 0){

    fun next(ch : Char) : State {
        return when {
            escaping  -> stopEscaping()
            ch == '!' -> startEscaping()
            ch == '>' -> setMode(Mode.GARBAGE_END)
            ch == '<' && mode != Mode.GARBAGE_START -> setMode(Mode.GARBAGE_START)
            ch == '{' && mode != Mode.GARBAGE_START -> incDepth()
            ch == '}' && mode != Mode.GARBAGE_START -> addGroup().decDepth()
            mode == Mode.GARBAGE_START -> countGarbage()
            else -> this
        }
    }

    fun setMode(m : Mode) : State {
        mode = m
        return this
    }

    fun incDepth() : State {
        depth += 1
        return this
    }

    fun decDepth() : State {
        depth -= 1
        return this
    }

    fun addGroup() : State {
        totalGroups += depth
        return this
    }

    fun stopEscaping() : State {
        escaping = false
        return this
    }

    fun startEscaping() : State {
        escaping = true
        return this
    }

    fun countGarbage() : State {
        totalGarbage += 1
        return this
    }

    fun stopGarbageCounting() : State {
        totalGarbage -= 1
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