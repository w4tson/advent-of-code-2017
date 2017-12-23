package com.pdwtech.aoc.day23

import com.pdwtech.aoc.day18.*
import com.pdwtech.aoc.day18.Set
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

class Program(program: List<String>) {

    private val memory : MutableMap<String, Long> = mutableMapOf()
    private val instructions : List<Instruction> = program.map { parseInstruction(it) }
    companion object {
        const val INSTR_POINTER = "instr"
    }

    init {
        memory["mulTotal"] = 0   
//        memory["a"] = 1
    }

    fun parseInstruction(i : String) : Instruction {
        return when(i.slice(0..3)) {
            "set " -> {
                val (param1, param2) = parseTwoParams(i)
                Set(memory, param1, param2)
            }
            "sub " -> {
                val (param1, param2) = parseTwoParams(i)
                Sub(memory, param1, param2)
            }
            "mul " -> {
                val (param1, param2) = parseTwoParams(i)
                Mul(memory, param1, param2)
            }
            "jnz " -> {
                val (param1, param2) = parseTwoParams(i)
                Jnz(memory, param1, param2)
            }
            else -> throw Exception("Unknown instruction $i")
        }
    }

    private fun parseTwoParams(i : String) : Pair<String, String> {
        val (param1, param2) = i.slice(4 until i.length).split(" ")
        return Pair(param1, param2)
    }

     fun run() : Long? {
         return runBlocking {
             while (isValidInstructionIndex()) {
                 val currPointer = memory.getOrPut(INSTR_POINTER, { 0L })
                 val instruction = instructions[currPointer.toInt()]
                 instruction.exec()
//                 delay(500)
                 
                 println("i=$instruction      $memory")
                 if (currPointer == memory[INSTR_POINTER]) {
                     memory.compute(INSTR_POINTER, { _, v -> v?.plus(1L) })
                 }
             }

             println(memory)
             memory["mulTotal"]
         }
    }

    private fun isValidInstructionIndex() : Boolean {
        val instructionIndex = memory.getOrElse(INSTR_POINTER, {0})
        return instructionIndex >= 0 && instructionIndex< instructions.size
    }
}