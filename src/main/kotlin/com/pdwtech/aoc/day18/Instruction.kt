package com.pdwtech.aoc.day18


abstract class Instruction(val memory: MutableMap<String, Long>) {

    abstract fun exec()

    fun eval(expr : String) : Long {
        try {
            return java.lang.Long.parseLong(expr)
        } catch (e: NumberFormatException) {
            return memory.getOrDefault(expr, 0)
        }
    }
}


class Set(memory: MutableMap<String, Long>, val register: String, val value: String) : Instruction(memory) {
    override fun exec() {
        this.memory.put(register, eval(value))
    }

    override fun toString(): String =  "${javaClass.simpleName} $register $value"
}

class Add(memory: MutableMap<String, Long>, val register: String, val value: String) : Instruction(memory) {
    override fun exec() {
        this.memory.put(register, eval(register) + eval(value))
    }

    override fun toString(): String =  "${javaClass.simpleName} $register $value"
}

class Mul(memory: MutableMap<String, Long>, val register: String, val value: String) : Instruction(memory) {
    override fun exec() {
        this.memory.put(register, eval(register) * eval(value))
    }

    override fun toString(): String =  "${javaClass.simpleName} $register $value"
}

//TODO pass in higher order function
class Mod(memory: MutableMap<String, Long>, val register: String, val value: String) : Instruction(memory) {
    override fun exec() {
        this.memory.put(register, eval(register) % eval(value))
    }

    override fun toString(): String =  "${javaClass.simpleName} $register $value"
}

class Rcv(memory: MutableMap<String, Long>, val register: String) : Instruction(memory) {
    override fun exec() {
        if (eval(register) != 0L) {
            this.memory.put("rcv", eval("snd"))
        }
    }

    override fun toString(): String =  "${javaClass.simpleName} $register"
}

class Snd(memory: MutableMap<String, Long>, val register: String) : Instruction(memory) {
    override fun exec() {
        this.memory.put("snd", eval(register))
    }

    override fun toString(): String =  "${javaClass.simpleName} $register "
}

class Jgz(memory: MutableMap<String, Long>, val register: String, val value: String) : Instruction(memory) {
    override fun exec() {
        if (eval(register) > 0) {
            memory.put(Machine.INSTR_POINTER, eval(Machine.INSTR_POINTER) + eval(value))
        }
    }

    override fun toString(): String =  "${javaClass.simpleName} $register $value"
}