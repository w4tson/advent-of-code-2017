package com.pdwtech.aoc.day23

object ProgramTranslated {
    
    //This is just calculating primes!
    fun run() {

        var b = 61
        var c = 61
        var h = 0
        

        do {
            var hasDivisors = false
            
            for (d in 2 until b) {
    
                for (e in 2 until b) {
                    
                    if (d * e == b )  {
//                        println("$d * $e = $b")
                        hasDivisors = true 
                    }
                }
            } 
    
            if (hasDivisors) {
                h += 1
            }
    
            var t = b-c
    
            b += 17
            
        } while (t != 0)
        
        println("h = $h")
        
    }
    
    fun countNonePrimesBetween(x: Int, y:Int) : Int = (x..y step 17).sumBy { if (!isPrime(it)) 1 else 0 }

    private fun isPrime(num: Int): Boolean = (2..num / 2).none { num % it == 0 }
    

}