package assignment3

import assignment1.DnaCharacter
import assignment1.DnaString
import assignment2.hammingDistance
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.file.Files
import java.nio.file.Path
import kotlin.random.Random

internal class Assignment3Test {

    companion object {
        private val outContent = ByteArrayOutputStream()
        private val originalOut = System.out

        @BeforeAll
        @JvmStatic
        fun setUpStreams() {
            System.setOut(PrintStream(outContent))
        }

        @AfterAll
        @JvmStatic
        fun restoreStreams() {
            System.setOut(originalOut)
        }

        @BeforeAll
        @JvmStatic
        internal fun `Prepare sample file inputs`(@TempDir tempDir: Path) {
            val q8InputFile = tempDir.resolve("input.txt").toFile()
            q8InputFile.printWriter().use {
                it.println("A C G T")
                it.println(2)
            }

            val q9InputFile = tempDir.resolve("inputQ9.txt").toFile()
            q9InputFile.printWriter().use {
                it.println("TACAGT")
                it.println("AGTCAG")
                it.println("ACAGTC")
                it.println("TCAGAT")
            }
        }
    }

    @Test
    fun `There are five transitions in Q7 sample test case`() {
        val s1 = DnaString("GCAACGCACAACGAAAACCCTTAGGGA")
        val s2 = DnaString("TTATCTGACAAAGAAAGCCGTCAACGG")
        val actual = s1.string.zip(s2.string).count { (fst, snd) -> isTransition(fst, snd) }
        val expected = 5
        assertEquals(expected, actual)
    }

    @Test
    fun `Transition plus Transversion equals to hamming distance`() {
        repeat(1000) {
            val stringLength = Random.nextInt(1, 100)
            val s1 = (1..stringLength)
                .map { Random.nextInt(DnaCharacter.alphabet.length) }
                .map { DnaCharacter.alphabet[it] }
                .joinToString("")
                .let { DnaString(it) }
            val s2 = (1..stringLength)
                .map { Random.nextInt(DnaCharacter.alphabet.length) }
                .map { DnaCharacter.alphabet[it] }
                .joinToString("")
                .let { DnaString(it) }
            val expected = s1.hammingDistance(s2)
            val actual =
                s1.string.zip(s2.string).count { (fst, snd) -> isTransition(fst, snd) } +
                        s1.string.zip(s2.string).count { (fst, snd) -> isTransversion(fst, snd) }
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `It should pass Q7 sample test case`() {
        val s1 = DnaString("GCAACGCACAACGAAAACCCTTAGGGA")
        val s2 = DnaString("TTATCTGACAAAGAAAGCCGTCAACGG")
        val expected = 0.71
        val actual = ttRatio(s1, s2)
        assertEquals(expected, actual, 0.1)
    }

    @Test
    fun `It should pass Q8 sample test case`(@TempDir tempDir: Path) {
        sec2Q8(tempDir)
        assertTrue(Files.exists(tempDir.resolve("output.txt")))
        assertLinesMatch(
            listOf(
                "AA",
                "AC",
                "AG",
                "AT",
                "CA",
                "CC",
                "CG",
                "CT",
                "GA",
                "GC",
                "GG",
                "GT",
                "TA",
                "TC",
                "TG",
                "TT"
            ), Files.readAllLines(tempDir.resolve("output.txt"))
        )
    }

    @Test
    fun `It should pass Q9 sample test case`(@TempDir tempDir: Path) {
        sec3Q9(tempDir)
        val lines = outContent.toString().lines()
        val nodes = lines.map { it.split(Regex("[:,]? ")).first() }
        val expectedNodes = setOf(
            "TAC",
            "ACA",
            "CAG",
            "AGT",
            "AGT",
            "GTC",
            "TCA",
            "CAG",
            "ACA",
            "CAG",
            "AGT",
            "GTC",
            "TCA",
            "CAG",
            "AGA",
            "GAT"
        )

        expectedNodes.forEach { assertTrue(nodes.contains(it)) }

        lines.forEach { line ->
            val nodes = line.split(Regex("[:,]? "))
            val from = nodes.first()
            val tos = nodes.subList(1, nodes.size).filter { it.isNotBlank() }
            tos.forEach { assertEquals(from.substring(1), it.substring(0, it.length - 1)) }
        }
    }
}