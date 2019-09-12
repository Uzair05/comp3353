import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AminoAcidTest {

    @Test
    fun `it should not have missing combination`() {
        repeat(10000) {
            val acid = AminoAcid((0 until 3).toList().map { Nucleotide.alphabet.random() }.map { Nucleotide(it) })
            if (acid.isStopCodon()) return@repeat
            assertDoesNotThrow { acid.toCodon() }
        }
    }

    @Test
    fun `it should pass the assignment's test case`() {
        val input = "GAUGAGUACCCGUUAAAACGGGAUGGCCAUGGCGCCCAGAACUGAG"
        val expected = "MSTR"
        val actual = Q2(input)

        assertEquals(expected, actual)
    }

}