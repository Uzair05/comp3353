package assignment2

import assignment1.DnaString
import assignment1.Symbol
import assignment1.SymbolString

fun <T : Symbol> SymbolString<T>.hammingDistance(other: SymbolString<T>): Int =
    this.string.zip(other.string).sumBy { (fst, snd) -> if (fst == snd) 0 else 1 }

fun sec2Q6(str1: String, str2: String): Int {
    return DnaString(str1).hammingDistance(DnaString(str2))
}