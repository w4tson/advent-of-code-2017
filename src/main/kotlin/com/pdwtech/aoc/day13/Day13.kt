package com.pdwtech.aoc.day13

import com.pdwtech.aoc.day13.Direction.*

enum class Direction {
    FORWARD,
    BACKWARD
}

class Firewall(var data: MutableMap<Int, Array<Int>>){

    val maxDepth : Int by lazy {
        data.keys.max()!!

    }

    val maxRange : Int by lazy {
        data.values.map { it.size }.max()!!
    }

    val direction: MutableMap<Int, Direction> by lazy {
        (0..maxDepth).map { Pair(it, FORWARD) }.toMap().toMutableMap()
    }

    val f : Map<Int, Int> by lazy {
        data.entries.map { Pair(it.key, it.value.size) }.toMap()
    }

    init {
        for (i in 0..maxDepth) {
            data.putIfAbsent(i, emptyArray())
        }
    }

    fun scan(){
        if (data[0]!!.none { it == 1}) {
            initalize()
        } else {
            for (i in 0..maxDepth) {
                data[i] = scanOne(i, data[i]!!)
            }
        }
    }

    private fun scanOne(depth : Int, n: Array<Int>) : Array<Int> {
        return when (n.size) {
            0 -> n
            1 -> n
            else -> scanOne2(depth, n)
        }
    }

    private fun scanOne2(depth : Int, n: Array<Int>) : Array<Int> {
        if (n.last() == 1) {
            direction[depth] = BACKWARD
        }
        if (n.first() == 1) {
            direction[depth] = FORWARD
        }
        val result =  if (direction[depth]!! == FORWARD)
            arrayOf( n.last()) + n.sliceArray(0..n.size-2)
         else
            n.sliceArray(1..n.size - 1) + arrayOf(n.first())
        return result
    }


    fun initalize() {
        val emptyarr = emptyArray<Int>()

        for (i in 0..maxDepth) {
            val d = data.getOrElse(i, { emptyarr })
            if (d.isNotEmpty() ) {
                d[0] = 1
            }
        }
    }

    override fun toString(): String {
        println(maxRange)
        println(maxDepth)
        val sb = StringBuffer()
        for (j in 0..maxRange-1) {
            for (i in 0..maxDepth) {
                data.get(i)?.let { a ->
                    if (a.size > j) {
                        sb.append("[ ] ")
                    }
                }
            }
            sb.append("\n")

        }
        return "$sb"

    }
}

object Day13 {

    fun part2(firewall: Firewall) : Int {

        var sev : Int = 1
        var delay : Int = 0
        while(sev> 0) {

            sev = part1(firewall, delay++)
            println("sev = $sev delay = $delay")
        }
        return delay+1
    }

    fun part1(firewall : Firewall, delay : Int = 0) : Int {

        var severity = 0;
        var current = 0

        for (t in 0..firewall.maxDepth) {
            val tt = t + delay
            firewall.f[tt]?.let { range ->
                println("$tt $range is there ${isThereAtTime(tt,range)}")
                if (isThereAtTime(tt, range)){
                    severity += tt * range +1
                }
            }

        }

        return severity
        
    }

    fun isThereAtTime(t: Int, range: Int) : Boolean {
        return range !==0 &&  t % ((range-1)*2) == 0
    }


    fun buildFirewall(input: List<String>) : Firewall {
        return Firewall(input.map { line ->
            val (depth, range) = inputRegex.find(line)!!.destructured
            val arr = Array(range.toInt(), { 0 })
            Pair(depth.toInt(), arr)
        }.toMap().toMutableMap())
    }

    private val inputRegex = Regex("""^(\d+): (\d+)""")

}