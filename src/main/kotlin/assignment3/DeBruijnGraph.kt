package assignment3

class DeBruijnGraph(reads: List<String>, k: Int) {

    private val edges: MutableMap<String, MutableList<String>> = mutableMapOf()

    init {
        val kmers = reads.flatMap { getKmers(it, k) }.toSet()
        kmers.forEach { edges[it] = mutableListOf() }
        kmers.forEach { from ->
            kmers.forEach { to ->
                if (from != to && from.substring(1) == to.substring(0, to.length - 1)) {
                    edges[from]!!.add(to)
                }
            }
        }
    }

    fun printGraph() {
        edges.forEach { (from, tos) ->
            println("$from: ${tos.joinToString(", ")}")
        }
    }
}