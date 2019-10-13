package assignment2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class WaveletTreeTest {

    @Test
    fun `it should count equal on a simple short list correctly`() {
        val input = listOf(1, 2, 3, 2, 1)
        val waveletTree = WaveletTree(input)

        assertEquals(0, waveletTree.countEqualTo(1, 1, 4))
        assertEquals(1, waveletTree.countEqualTo(1, 4))
        assertEquals(2, waveletTree.countEqualTo(1, 0, 5))
        assertEquals(1, waveletTree.countEqualTo(2, 2, 5))
    }

    @Test
    fun `it should count less equal on random input correctly`() {
        repeat(10) {
            val input = List(Random.nextInt(1000)) { Random.nextInt(1, 10) }
            val waveletTree = WaveletTree(input)
            repeat(100) {
                var left = Random.nextInt(input.size)
                var right = Random.nextInt(input.size)
                val x = Random.nextInt(1, 10)
                if (left > right) {
                    left = right.also { right = left }
                }
                right++

                val expected = input.subList(left, right).count { it <= x }
                val actual = waveletTree.countLessEqualTo(x, left, right)

                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun `it should count equal on random input correctly`() {
        repeat(10) {
            val input = List(Random.nextInt(1000)) { Random.nextInt(1, 10) }
            val waveletTree = WaveletTree(input)
            repeat(100) {
                var left = Random.nextInt(input.size)
                var right = Random.nextInt(input.size)
                val x = Random.nextInt(1, 10)
                if (left > right) {
                    left = right.also { right = left }
                }
                right++

                val expected = input.subList(left, right).count { it == x }
                val actual = waveletTree.countEqualTo(x, left, right)

                assertEquals(expected, actual)
            }
        }
    }

}