package assignment3

import java.nio.file.Path

fun sec3Q9(directory: Path) {
    val reads = directory.resolve("inputQ9.txt").toFile().readLines()
    val graph = DeBruijnGraph(reads, 3)
    graph.printGraph()
}