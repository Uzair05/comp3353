package assignment3

import assignment1.DnaCharacter
import assignment1.DnaString

fun isTransition(x: DnaCharacter, y: DnaCharacter): Boolean {
    val idx1 = "AGCT".indexOf(x.toString())
    val idx2 = "AGCT".indexOf(y.toString())
    return idx1.xor(idx2) == 1
}

fun isTransversion(x: DnaCharacter, y: DnaCharacter): Boolean {
    val idx1 = "AGCT".indexOf(x.toString())
    val idx2 = "AGCT".indexOf(y.toString())
    return idx1.xor(idx2) > 1
}

fun ttRatio(s1: DnaString, s2: DnaString): Double {
    check(s1.string.size == s2.string.size) {
        println("$s1 and $s2 has different lengths hence R(s1, s2) is not well-defined.")
    }

    val zippedList = s1.string.zip(s2.string)
    val transitionCount = zippedList.count { (fst, snd) -> isTransition(fst, snd) }.toDouble()
    val transversionCount = zippedList.count { (fst, snd) -> isTransversion(fst, snd) }.toDouble()

    return transitionCount / transversionCount
}