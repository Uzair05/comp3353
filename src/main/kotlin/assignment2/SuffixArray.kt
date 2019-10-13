package assignment2

class SuffixArray(private val terminatedString: String) {

    val suffixArray: List<Int>
    val lcp: List<Int>
    val permutedLcp: List<Int>

    init {

        check(terminatedString.endsWith("$")) {
            "Constructor string is non-terminated."
        }

        fun getRank(rank: List<Int>, idx: Int): Int {
            return if (idx >= rank.size) -1 else rank[idx]
        }

        fun getRankPair(rank: List<Int>, idx: Int, dx: Int): Pair<Int, Int> {
            return Pair(getRank(rank, idx), getRank(rank, idx + dx))
        }

        fun computeRank(currentRank: List<Int>, sa: List<Int>, dx: Int): List<Int> {
            val rank = MutableList(currentRank.size) { 0 }
            sa.forEachIndexed { idx, x ->
                if (idx == 0) {
                    rank[x] = (0)
                } else {
                    if (getRankPair(currentRank, x, dx) == getRankPair(currentRank, sa[idx - 1], dx)) {
                        rank[x] = rank[sa[idx - 1]]
                    } else {
                        rank[x] = rank[sa[idx - 1]] + 1
                    }
                }
            }
            return rank
        }

        fun radixSort(currentSA: List<Int>, currentRank: List<Int>, dx: Int): List<Int> {
            val minRank = currentRank.min()!!
            val maxRank = currentRank.max()!!
            val layerOneBuckets = List<MutableList<Int>>(maxRank + 2) { mutableListOf() }
            currentSA.forEach { layerOneBuckets[getRank(currentRank, it + dx) + 1].add(it) }
            val layerTwoBuckets = List<MutableList<Int>>(maxRank - minRank + 1) { mutableListOf() }
            layerOneBuckets.forEach { bucket ->
                bucket.forEach {
                    layerTwoBuckets[getRank(
                        currentRank,
                        it
                    ) - minRank].add(it)
                }
            }
            return layerTwoBuckets.flatten()
        }

        var stepSize = 1
        var currentRank = terminatedString.map { it.toInt() }
        var intermediateSuffixArray =
            terminatedString.indices.toList().sortedWith(compareBy { getRank(currentRank, it) })
        currentRank = computeRank(currentRank, intermediateSuffixArray, 0)

        while (stepSize < terminatedString.length) {
            intermediateSuffixArray = radixSort(intermediateSuffixArray, currentRank, stepSize)
            currentRank = computeRank(currentRank, intermediateSuffixArray, stepSize)
            stepSize *= 2
        }

        suffixArray = intermediateSuffixArray

        lcp = MutableList(suffixArray.size) { 0 }
        val suffixArrayInverse = MutableList(suffixArray.size) { 0 }
        suffixArray.forEachIndexed { idx, x -> suffixArrayInverse[x] = idx }

        var currentLCP = 0
        permutedLcp = suffixArray.indices.map { x ->
            currentLCP -= 1
            if (currentLCP < 0) {
                currentLCP = 0
            }

            if (suffixArrayInverse[x] == 0) {
                return@map 0
            }

            val y = suffixArray[suffixArrayInverse[x] - 1]
            while (suffixIndexMatches(x, y, currentLCP)) {
                currentLCP++
            }

            return@map currentLCP
        }
        permutedLcp.forEachIndexed { idx, x -> lcp[suffixArrayInverse[idx]] = x }

    }

    fun getSuffix(idx: Int): String {
        return terminatedString.substring(idx)
    }

    private fun suffixIndexMatches(x: Int, y: Int, dx: Int): Boolean {
        return if (x + dx > terminatedString.length || y + dx > terminatedString.length) {
            false
        } else {
            terminatedString[x + dx] == terminatedString[y + dx]
        }
    }

    fun getRankedSuffix(rank: Int): String {
        return terminatedString.substring(suffixArray[rank])
    }

}