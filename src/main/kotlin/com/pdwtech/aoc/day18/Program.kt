package com.pdwtech.aoc.day18

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.withTimeout
//6605 too low
//6477 too low
class Program(val program: List<String>, val id: Long, val send : Channel<Long>, val receive: Channel<Long>) {

    private val memory : MutableMap<String, Long> = mutableMapOf()
    private val instructions : List<Instruction> = program.map { parseInstruction2(it) }
    companion object {
        const val INSTR_POINTER = "instr"
    }
    private val pid = id;

    init {
        memory[INSTR_POINTER] = 0
        memory["p"] = id
        memory["pid"] = id
        memory["sends$id"] = 0
    }

    fun parseInstruction(i : String) : Instruction {
        return when(i.slice(0..3)) {
            "set " -> {
                val (param1, param2) = parseTwoParams(i)
                Set(memory, param1, param2)
            }
            "add " -> {
                val (param1, param2) = parseTwoParams(i)
                Add(memory, param1, param2)
            }
            "mul " -> {
                val (param1, param2) = parseTwoParams(i)
                Mul(memory, param1, param2)
            }
            "mod " -> {
                val (param1, param2) = parseTwoParams(i)
                Mod(memory, param1, param2)
            }
            "rcv " -> Rcv(memory, i.slice(4 until i.length))
            "snd " -> Snd(memory, i.slice(4 until i.length))
            "jgz " -> {
                val (param1, param2) = parseTwoParams(i)
                Jgz(memory, param1, param2)
            }
            else -> throw Exception("Unknown instruction $i")
        }
    }

    fun parseInstruction2(i : String) : Instruction {
        return when(i.slice(0..3)) {
            "set " -> {
                val (param1, param2) = parseTwoParams(i)
                Set(memory, param1, param2)
            }
            "add " -> {
                val (param1, param2) = parseTwoParams(i)
                Add(memory, param1, param2)
            }
            "mul " -> {
                val (param1, param2) = parseTwoParams(i)
                Mul(memory, param1, param2)
            }
            "mod " -> {
                val (param1, param2) = parseTwoParams(i)
                Mod(memory, param1, param2)
            }
            "rcv " -> Receive(memory, i.slice(4 until i.length), receive)
            "snd " -> Send(memory, i.slice(4 until i.length), send)
            "jgz " -> {
                val (param1, param2) = parseTwoParams(i)
                Jgz(memory, param1, param2)
            }
            else -> throw Exception("Unknown instruction $i")
        }
    }

    fun parseTwoParams(i : String) : Pair<String, String> {
        val (param1, param2) = i.slice(4 until i.length).split(" ")
        return Pair(param1, param2)
    }

    suspend fun run() : Long {
        println("starting program $id")
        withTimeout(10_000) {
            while (memory[INSTR_POINTER]!! >= 0 && memory[INSTR_POINTER]!! < instructions.size) {
                val currPointer = memory[INSTR_POINTER]
                val instruction = instructions[memory.getValue(INSTR_POINTER).toInt()]
                instruction.exec()

                println("pid=$pid p=${memory[INSTR_POINTER]} i=$instruction      $memory")
                if (currPointer == memory[INSTR_POINTER]) {
                    memory[INSTR_POINTER] = memory.getOrDefault(INSTR_POINTER, 0) + 1
                }
            }
        }


        println(memory)
        return memory.getOrDefault("rcv", 0)
    }
}