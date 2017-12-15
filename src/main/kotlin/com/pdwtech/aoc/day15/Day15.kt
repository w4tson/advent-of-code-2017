package com.pdwtech.aoc.day15

import java.math.BigInteger
import java.math.BigInteger.*

object Day15 {
    private val FORTY_MILLION = 40000000
    private val FIVE_MILLION = 5000000
    private val LOWEST_16_MASK = valueOf(0x0000FFFF)
    private val seqAQuotient = 16807L
    private val seqBQuotient = 48271L

    fun matchingPairs(seed: Pair<Int, Int>) : Long {
        val seqA = genSeq(seed.first, seqAQuotient)
        val seqB = genSeq(seed.second, seqBQuotient)

        return seqA.zip(seqB, ::compareLowest16Bits).take(FORTY_MILLION).sum()
    }

    fun matchingPairs2(seed: Pair<Int, Int>) : Long {
        val seqA = genSeq(seed.first, seqAQuotient).filter { (it % valueOf(4)) == ZERO }
        val seqB = genSeq(seed.second, seqBQuotient).filter { (it % valueOf(8)) == ZERO }
        return seqA.zip(seqB, ::compareLowest16Bits).take(FIVE_MILLION).sum()
    }

    fun compareLowest16Bits(a: BigInteger, b: BigInteger) : Long =
            if (a.and(LOWEST_16_MASK).xor(b.and(LOWEST_16_MASK)) > ZERO) 0 else 1


    fun genSeq(start: Int, quotient: Long) : Sequence<BigInteger> = generateSequence(valueOf(start.toLong()), {
        (it.times(valueOf(quotient))) % valueOf(2147483647)
    }).drop(1)

}