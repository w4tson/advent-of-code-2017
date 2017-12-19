package com.pdwtech.aoc.day18

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking


abstract class Instruction(val memory: MutableMap<String, Long>) {

    abstract suspend fun exec()

    fun eval(expr : String) : Long {
        try {
            return java.lang.Long.parseLong(expr)
        } catch (e: NumberFormatException) {
            return memory.getOrDefault(expr, 0)
        }
    }
}


class Set(memory: MutableMap<String, Long>, val register: String, val value: String) : Instruction(memory) {
    override suspend fun exec() {
        this.memory.put(register, eval(value))
    }

    override fun toString(): String =  "${javaClass.simpleName} $register $value"
}

class Add(memory: MutableMap<String, Long>, val register: String, val value: String) : Instruction(memory) {
    override suspend fun exec() {
        this.memory.put(register, eval(register) + eval(value))
    }

    override fun toString(): String =  "${javaClass.simpleName} $register $value"
}

class Mul(memory: MutableMap<String, Long>, val register: String, val value: String) : Instruction(memory) {
    override suspend fun exec() {
        this.memory.put(register, eval(register) * eval(value))
    }

    override fun toString(): String =  "${javaClass.simpleName} $register $value"
}

//TODO pass in higher order function
class Mod(memory: MutableMap<String, Long>, val register: String, val value: String) : Instruction(memory) {
    override suspend fun exec() {
        this.memory.put(register, eval(register) % eval(value))
    }

    override fun toString(): String =  "${javaClass.simpleName} $register $value"
}

class Rcv(memory: MutableMap<String, Long>, val register: String) : Instruction(memory) {
    override suspend fun exec() {
        if (eval(register) != 0L) {
            this.memory.put("rcv", eval("snd"))
        }
    }

    override fun toString(): String =  "${javaClass.simpleName} $register"
}

class Receive(memory: MutableMap<String, Long>, val register: String, val receive: Channel<Long>) : Instruction(memory) {
    override suspend fun exec() {
        val pid = memory["pid"]
        println("pid$pid waiting to receive")
        val result = receive.receive()
        println("pid$pid received $result storing it in $register")
        memory[register] = result
        memory.computeIfPresent("sends$pid") { _, v -> v + 1 }

    }

    override fun toString(): String =  "${javaClass.simpleName} $register"
}

class Send(memory: MutableMap<String, Long>, val register: String, val send: Channel<Long>) : Instruction(memory) {
    override suspend fun exec() {
        launch {
            val pid = memory["pid"]
            println("pid$pid sending $register")
            send.send(eval(register))
        }

    }

    override fun toString(): String =  "${javaClass.simpleName} $register "
}

class Snd(memory: MutableMap<String, Long>, val register: String) : Instruction(memory) {
    override suspend fun exec() {
        this.memory.put("snd", eval(register))
    }

    override fun toString(): String =  "${javaClass.simpleName} $register "
}

class Jgz(memory: MutableMap<String, Long>, val register: String, val value: String) : Instruction(memory) {
    override suspend fun exec() {
        if (eval(register) > 0) {
            memory.put(Program.INSTR_POINTER, eval(Program.INSTR_POINTER) + eval(value))
        }
    }

    override fun toString(): String =  "${javaClass.simpleName} $register $value"
}