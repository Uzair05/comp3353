package assignment3

import java.nio.file.Path

fun <T> extendPermutations(permutations: List<List<T>>, ext: List<T>): List<List<T>> {
    return permutations.flatMap { str -> ext.map { str + it } }
}

fun sec2Q8(directory: Path) {
    val file = directory.resolve("input.txt").toFile()
    val lines = file.readLines()
    val alphabet = lines[0].split(" ")
    val n = lines[1].toInt()

    var allPermutations = mutableListOf<List<String>>(listOf())
    repeat(n) {
        allPermutations = extendPermutations(allPermutations, alphabet).toMutableList()
    }

    directory.resolve("output.txt").toFile().printWriter().use { out ->
        allPermutations.forEach { out.println(it.joinToString("")) }
    }
}