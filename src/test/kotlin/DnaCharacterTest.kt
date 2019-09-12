import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DnaCharacterTest {

    @Test
    fun `it should output the correct complements`() {
        val expectedCharMapping = mapOf(
            'A' to 'T',
            'T' to 'A',
            'G' to 'C',
            'C' to 'G'
        )

        val expectedDnaCharacterMapping =
            expectedCharMapping.map { (k, v) -> DnaCharacter(k) to DnaCharacter(v) }.toMap()

        expectedDnaCharacterMapping.forEach { (input, expected) ->
            val actual = input.complement()
            assertEquals(expected, actual)
        }
    }

}