package assignment2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BWTTest {

    @Test
    fun `It creates the same BWT as the first test case in slides`() {
        val string = "mono$"
        val expected = "o\$onm"
        val actual = BWT(string).encodedString

        assertEquals(expected, actual)
    }

    @Test
    fun `It creates the same BWT as the second test case in slides`() {
        val string = "oneonone\$"
        val expected = "ennooon\$e"
        val actual = BWT(string).encodedString

        assertEquals(expected, actual)
    }

    @Test
    fun `It counts n in oneonone correctly`() {
        val string = "oneonone$"
        val pattern = "n"
        val bwt = BWT(string)
        val expected = 3
        val actual = bwt.countPattern(pattern)

        assertEquals(expected, actual)
    }

    @Test
    fun `It matches positions of n in oneonone correctly`() {
        val string = "oneonone$"
        val pattern = "n"
        val bwt = BWT(string)
        val expected = listOf(6, 1, 4)
        val actual = bwt.matchPattern(pattern)

        assertEquals(expected.sorted(), actual.sorted())
    }

    @Test
    fun `It counts positions of one in oneonone correctly`() {
        val string = "oneonone$"
        val pattern = "one"
        val bwt = BWT(string)
        val expected = 2
        val actual = bwt.countPattern(pattern)

        assertEquals(expected, actual)
    }

    @Test
    fun `It matches positions of one in oneonone correctly`() {
        val string = "oneonone$"
        val pattern = "one"
        val bwt = BWT(string)
        val expected = listOf(5, 0)
        val actual = bwt.matchPattern(pattern)

        assertEquals(expected.sorted(), actual.sorted())
    }

    @Test
    fun `It matches positions of eon in oneonone correctly`() {
        val string = "oneonone$"
        val pattern = "eon"
        val bwt = BWT(string)
        val expected = listOf(2)
        val actual = bwt.matchPattern(pattern)

        assertEquals(expected.sorted(), actual.sorted())
    }

    @Test
    fun `It matches positions of eon in 1to6 correctly`() {
        val string = "onetwoonethreeonefouronefiveone$"
        val pattern = "eon"
        val bwt = BWT(string)
        val expected = listOf(13, 27)
        val actual = bwt.matchPattern(pattern)

        assertEquals(expected.sorted(), actual.sorted())
    }

}