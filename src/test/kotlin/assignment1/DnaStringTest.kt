package assignment1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DnaStringTest {

    @Test
    fun `it should pass the test case explicitly provided by the assignment`() {
        val input = "GAACTATT"
        val expected = "AATAGTTC"
        val actual = sec1Q1(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `it should pass the test case implicitly provided by the assignment statements`() {
        val input = "CATT"
        val expected = "AATG"
        val actual = sec1Q1(input)

        assertEquals(expected, actual)
    }


}