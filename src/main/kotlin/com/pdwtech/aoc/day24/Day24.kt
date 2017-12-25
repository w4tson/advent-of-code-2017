package com.pdwtech.aoc.day24
import com.pdwtech.aoc.Aoc.isSingleton
import kotlin.math.max
import kotlin.math.min

data class Component(val a: Int, val b: Int) {
    fun isStarter() : Boolean = a == 0 || b == 0
    
    fun reversed() : Component = Component(this.b, this.a)

    override fun toString(): String {
        return "$a/$b"
    }
}

typealias Bridge = List<Component>


class Day24(val components: List<Component>) {
    
    fun findLongestBridge() : Int? {
        val allBridges = findAllBridges()
        val maxLength = allBridges.maxBy { it.size }?.size
        return allBridges.filter { it.size == maxLength }
                .map { bridge -> bridge.sumBy { it.a + it.b } }
                .max()
    }
    
    fun findStrongestBridge() : Int? = 
            findAllBridges().map { bridge -> bridge.sumBy { it.a + it.b } }.max()
    
    fun findAllBridges() : List<Bridge> = components.fold(emptyList(), { acc, next->
            acc + permute(listOf(next), components.minus(next), emptySet())
        })
    
    fun permute(currentBridge: Bridge,
                remaining: List<Component>,
                           acc: Set<Bridge> ) : Set<Bridge> {
        if (acc.isEmpty() && currentBridge.isSingleton() && !currentBridge[0].isStarter()) return emptySet()
        
        val connectable = connectableWith(currentBridge.last(), remaining)
        
        return when (connectable.size) {
            0 -> acc
            else -> acc + connectable.flatMap { permute(currentBridge + it, remaining.minusReversed(it), acc + (setOf(currentBridge + listOf(it)))  ) }.toSet()
        }
    } 
    
    fun List<Component>.minusReversed(c: Component) : List<Component> = if (this.any { it == c }) {
            this.minus(c)
        } else {
            this.minus(c.reversed())
        }

    fun connectableWith(c : Component, components: List<Component>) : List<Component> {
        return components.filter { it.a == c.b } + components.filter { it.b == c.b }.map(Component::reversed)
    }
    
    companion object {
        fun parseComponents(input : List<String>) : List<Component> = input.map(this::toComponent)

        fun toComponent(s: String) = s.split("/").map(String::toInt).let { (a, b) -> Component(min(a,b), max(a,b)) }
        
    }
    
    
}