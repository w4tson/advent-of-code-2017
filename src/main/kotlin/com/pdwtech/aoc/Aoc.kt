package com.pdwtech.aoc

import java.nio.file.Files
import java.nio.file.Paths


object Aoc {

    fun parseInt(c : Char) : Int {
        return "${c}".toInt()
    }

    fun readInput(fileName: String) : List<String> {
        val resource = javaClass.getResource("/$fileName")
        val toURI = resource.toURI()
        return Files.readAllLines(Paths.get(toURI))
    }
}