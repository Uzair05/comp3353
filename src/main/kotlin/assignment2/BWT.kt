package assignment2

class BWT(private val terminatedString: String) {

    private val suffixArray = SuffixArray(terminatedString)
    val encodedString: String = suffixArray.suffixArray
        .map { terminatedString[(it - 1 + terminatedString.length) % terminatedString.length] }
        .joinToString("")
    private val counter = WaveletTree(encodedString.map { it.toInt() })

    init {
        check(terminatedString.endsWith("$")) {
            "WARNING: The constructor of this class takes a terminated string."
        }
    }

    private fun countCharacter(value: Char, from: Int, to: Int): Int {
        return counter.countEqualTo(value.toInt(), from, to)
    }

    private fun extractRange(char: Char): Pair<Int, Int> {
        val end = counter.countLessEqualTo(char.toInt(), terminatedString.length)
        val start = end - counter.countEqualTo(char.toInt(), terminatedString.length) + 1

        return Pair(start, end)
    }

    private fun extractRange(char: Char, previousRange: Pair<Int, Int>): Pair<Int, Int> {
        if (previousRange == Pair(-1, -1)) return extractRange(char)

        val (u, v) = previousRange
        val countLess = counter.countLessThan(char.toInt(), terminatedString.length)
        val start = countLess + counter.countEqualTo(char.toInt(), u - 1) + 1
        val end = countLess + counter.countEqualTo(char.toInt(), v)

        return Pair(start, end)
    }

    private fun extractRange(string: String): Pair<Int, Int> {
        val initialRange = extractRange(string.last())
        return string.dropLast(1).foldRight(initialRange) { c, acc -> return@foldRight extractRange(c, acc) }
    }

    fun countPattern(pattern: String): Int {
        val (u, v) = extractRange(pattern)
        return v - u + 1
    }

    fun matchPattern(pattern: String): List<Int> {
        val (u, v) = extractRange(pattern)
        return (u..v).map { suffixArray.suffixArray[it - 1] }
    }

}