package assignment2

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.greaterThan
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

internal class SuffixArrayTest {

    private val alphabet = CharArray(26) { (it + 97).toChar() }.toList()

    @Test
    fun `it should reject non-terminated strings`() {
        repeat(1000) {
            val randomNonTerminatedString = List(Random.nextInt(1000)) { alphabet[Random.nextInt(26)] }.joinToString("")
            val exception = assertThrows<IllegalStateException> { SuffixArray(randomNonTerminatedString) }
            val expected = "Constructor string is non-terminated."
            assertThat(exception.message, equalTo(expected))
        }
    }

    @Test
    fun `It should sort random suffixes properly`() {
        repeat(1000) {
            val randomString = List(Random.nextInt(1000)) { alphabet[Random.nextInt(26)] }.joinToString("") + "$"
            val suffixArray = SuffixArray(randomString)
            (1 until randomString.length).forEach {
                assertThat(suffixArray.getRankedSuffix(it), greaterThan(suffixArray.getRankedSuffix(it - 1)))
            }
        }
    }

    @Test
    fun `It should pass this test case from the Competitive Programming 3 textbook`() {
        val string = "GATAGACA$"
        val expected = listOf(8, 7, 5, 3, 1, 6, 4, 0, 2)
        val suffixArray = SuffixArray(string)
        assertThat(suffixArray.suffixArray, equalTo(expected))
    }

    @Test
    fun `It should pass the first test case from the lecture slides`() {
        val string = "mono$"
        val expected = listOf(4, 0, 2, 3, 1)
        val suffixArray = SuffixArray(string)
        assertThat(suffixArray.suffixArray, equalTo(expected))
    }

    @Test
    fun `It should pass the second test case from the lecture slides`() {
        val string = "oneonone$"
        val expected = listOf(8, 7, 2, 6, 1, 4, 5, 0, 3)
        val suffixArray = SuffixArray(string)
        assertThat(suffixArray.suffixArray, equalTo(expected))
    }

    @Test
    fun `The generated LCP and LCP should pass the test case from the CP3 textbook`() {
        val string = "GATAGACA$"
        val expectedLCP = listOf(0, 0, 1, 1, 1, 0, 0, 2, 0)
        val expectedPLCP = listOf(2, 1, 0, 1, 0, 1, 0, 0, 0)
        val suffixArray = SuffixArray(string)
        val actualLCP = suffixArray.lcp
        val actualPLCP = suffixArray.permutedLcp

        assertThat(actualLCP, equalTo(expectedLCP))
        assertThat(actualPLCP, equalTo(expectedPLCP))
    }

}