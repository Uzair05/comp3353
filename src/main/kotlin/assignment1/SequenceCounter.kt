package assignment1

import java.util.*

class SequenceCounter<T>(
    private val k: Int,
    private val filterPredicate: (T) -> Boolean
) {

    private val recentKValues = ArrayDeque<T>()
    private var counter = 0

    fun reset() {
        recentKValues.clear()
        counter = 0
    }

    fun readBit(bit: T): Int {
        if (recentKValues.size == k) {
            if (filterPredicate(recentKValues.removeFirst())) {
                counter -= 1
            }
        }

        if (filterPredicate(bit)) {
            counter += 1
        }

        recentKValues.addLast(bit)

        return if (recentKValues.size < k) {
            -1
        } else {
            counter
        }
    }

}