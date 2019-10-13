package assignment2

interface SequenceCounter {
    fun countLessThan(value: Int, until: Int): Int {
        return countLessEqualTo(value, until) - countEqualTo(value, until)
    }

    fun countLessThan(value: Int, from: Int, to: Int): Int {
        return countLessThan(value, to) - countLessThan(value, from)
    }

    fun countLessEqualTo(value: Int, until: Int): Int
    fun countLessEqualTo(value: Int, from: Int, to: Int): Int {
        if (from >= to) return 0
        return countLessEqualTo(value, to) - countLessEqualTo(value, from)
    }

    fun countEqualTo(value: Int, until: Int): Int {
        return countLessEqualTo(value, until) - countLessEqualTo(value - 1, until)
    }

    fun countEqualTo(value: Int, from: Int, to: Int): Int {
        if (from >= to) return 0
        return countEqualTo(value, to) - countEqualTo(value, from)
    }
}