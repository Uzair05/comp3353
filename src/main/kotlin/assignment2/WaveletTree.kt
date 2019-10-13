package assignment2

class WaveletTree(val sequence: List<Int>) : SequenceCounter {

    private val lowerBound = sequence.min()!!
    private val upperBound = sequence.max()!! + 1
    private val rootNode: WaveletNode = WaveletNode(sequence.indices.toList(), lowerBound, upperBound)

    inner class WaveletNode(indices: List<Int>, lowerBound: Int, upperBound: Int) {
        private val leftChild: WaveletNode?
        private val rightChild: WaveletNode?
        private val threshold: Int = (lowerBound + upperBound) / 2
        private val leftPointers: MutableList<Int> = mutableListOf()
        private val rightPointers: MutableList<Int> = mutableListOf()

        init {
            if (threshold > lowerBound) {
                val childrenIndices = indices.partition { sequence[it] < threshold }
                leftChild = WaveletNode(childrenIndices.first, lowerBound, threshold)
                rightChild = WaveletNode(childrenIndices.second, threshold, upperBound)

                var smallCount = 0
                var largeCount = 0

                indices.forEach {
                    leftPointers.add(smallCount)
                    rightPointers.add(largeCount)

                    if (sequence[it] < threshold) {
                        smallCount++
                    } else {
                        largeCount++
                    }
                }

                leftPointers.add(smallCount)
                rightPointers.add(largeCount)
            } else {
                leftChild = null
                rightChild = null
            }
        }

        fun isLeaf(): Boolean {
            return leftChild == null
        }

        fun traverse(value: Int, index: Int): Int {
            return if (this.isLeaf()) {
                index
            } else {
                if (value >= threshold) {
                    leftPointers[index] + rightChild!!.traverse(value, rightPointers[index])
                } else {
                    leftChild!!.traverse(value, leftPointers[index])
                }
            }
        }
    }

    override fun countLessEqualTo(value: Int, until: Int): Int {
        return when {
            value < lowerBound -> 0
            value >= upperBound -> until
            else -> rootNode.traverse(value, until.coerceAtMost(sequence.size).coerceAtLeast(0))
        }
    }

}