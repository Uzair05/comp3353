package assignment2

import assignment1.DnaCharacter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class Assignment2Test {
    @Test
    fun `passes the provided test case of Q6`() {
        val str1 = "GAGCCTACTAACGGGAT"
        val str2 = "CATCGTAATGACGGCCT"
        val expected = 7
        val actual = sec2Q6(str1, str2)
        assertEquals(expected, actual)
    }

    @Test
    fun `the hamming distance of any string to itself is 0`() {
        repeat(1000) {
            val stringLength = Random.nextInt(1, 100)
            val randomString = (1..stringLength)
                .map { Random.nextInt(DnaCharacter.alphabet.length) }
                .map { DnaCharacter.alphabet.get(it) }
                .joinToString("")
            val expected = 0
            val actual = sec2Q6(randomString, randomString)
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `the sum of hamming distance to the four bases is 3 x length`() {
        repeat(1000) {
            val stringLength = Random.nextInt(1, 100)
            val randomString = (1..stringLength)
                .map { Random.nextInt(DnaCharacter.alphabet.length) }
                .map { DnaCharacter.alphabet[it] }
                .joinToString("")
            val expected = 3 * stringLength
            val actual = DnaCharacter.alphabet.map { ch ->
                val base = (1..stringLength).map { ch }.joinToString("")
                return@map sec2Q6(randomString, base)
            }.sum()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `the total weight of the protein alphabet is around 2400 kilo`() {
        val expected = 2400.0
        val actual = sec2Q7("ACDEFGHIKLMNPQRSTVWY")
        assertEquals(expected, actual, 50.0)
    }

    @Test
    fun `passes the provided test case of Q7`() {
        val expected = 821.39192
        val actual = sec2Q7("SKADYEK")
        assertEquals(expected, actual, 1e-4)
    }
}