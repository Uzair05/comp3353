package assignment1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MaskedDnaStringTest {
    @Test
    fun `it should mask nothing when the input string has only AT`() {
        val input = "ATTTATattttatatTATATAtatTATA"
        val expected = input
        val actual = MaskedDnaString(expected).maskByGc(4, 0.75).toString()

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `it should count nothing when the input string has only AT`() {
        val input = "ATTTATattttatatTATATAtatTATA"
        val expected = 0
        val actual = MaskedDnaString(input).countHighGc(4, 0.75)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `it should mask the entire string when the input string has only capitalized GC`() {
        val input = "GCGCGCGCGCCCGCGCGCCGCCC"
        val expected = input.map { if (it.isUpperCase()) 'N' else 'n' }.joinToString("")
        val actual = MaskedDnaString(input).maskByGc(4, 0.75).toString()

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `it should mask the entire string when the input string has only GC`() {
        val input = "GCCCgCcGCGCCGCCGgcCGG"
        val expected = input.map { if (it.isUpperCase()) 'N' else 'n' }.joinToString("")
        val actual = MaskedDnaString(input).maskByGc(4, 0.75).toString()

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `it should count all k-substrings when the input string has only GC`() {
        val input = "GCCCgCcGCGCCGCCGgcCGG"
        val k = 4
        val expected = input.length - k + 1
        val actual = MaskedDnaString(input).countHighGc(4, 0.75)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `it should count input string composed of ATGC properly`() {
        val input = "ATGcTTgCAaa"
        val expected = input.length - 4
        val actual = MaskedDnaString(input).countHighGc(4, 0.49)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `it should count only substrings that are strictly above the threshold`() {
        val input = "ATGcTTgCAaa"
        val expected = 0
        val actual = MaskedDnaString(input).countHighGc(4, 0.5)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `it should mask all input string composed of ATGC properly`() {
        val input = "ATGcTTgcCAa"
        val expected = "ATGcTNnnNNa"
        val actual = MaskedDnaString(input).maskByGc(4, 0.5).toString()

        Assertions.assertEquals(expected, actual)
    }

}