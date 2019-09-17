package assignment1

class Nucleotide(private val symbol: Char) : Symbol(symbol, alphabet) {

    companion object {
        const val alphabet = "UCAG"
    }

    init {
        alphabet.find { it == symbol } ?: throw IllegalArgumentException("Invalid RNA character!")
    }

}

class AminoAcid(private val nucleotides: List<Nucleotide>) : SymbolString<Nucleotide>(nucleotides) {

    companion object {
        val startCodons = listOf("AUG")
        val stopCodons = listOf("UAA", "UAG", "UGA")
    }

    fun isStartCodon(): Boolean = nucleotides.joinToString("") { it.toString() } in startCodons
    fun isStopCodon(): Boolean = nucleotides.joinToString("") in stopCodons

    fun toCodon(): Char {
        check(!this.isStopCodon()) { "Stop codon does not map to a single character!" }

        return when (nucleotides.joinToString("")) {
            in listOf("UUU", "UUC") -> 'F'
            in listOf("UUA", "UUG", "CUU", "CUC", "CUA", "CUG") -> 'L'
            in listOf("AUU", "AUC", "AUA") -> 'I'
            in listOf("AUG") -> 'M'
            in listOf("GUU", "GUC", "GUA", "GUG") -> 'V'
            in listOf("UCU", "UCC", "UCA", "UCG") -> 'S'
            in listOf("CCU", "CCC", "CCA", "CCG") -> 'P'
            in listOf("ACU", "ACC", "ACA", "ACG") -> 'T'
            in listOf("GCU", "GCC", "GCA", "GCG") -> 'A'
            in listOf("UAU", "UAC") -> 'Y'
            in listOf("CAU", "CAC") -> 'H'
            in listOf("CAA", "CAG") -> 'Q'
            in listOf("AAU", "AAC") -> 'N'
            in listOf("AAA", "AAG") -> 'K'
            in listOf("GAU", "GAC") -> 'D'
            in listOf("GAA", "GAG") -> 'E'
            in listOf("UGU", "UGC") -> 'C'
            in listOf("UGG") -> 'W'
            in listOf("CGU", "CGC", "CGA", "CGG") -> 'R'
            in listOf("AGU", "AGC") -> 'S'
            in listOf("AGA", "AGG") -> 'R'
            in listOf("GGU", "GGC", "GGA", "GGG") -> 'G'
            else -> throw IllegalStateException("Contact dev -- The combination for $nucleotides is missing.")
        }
    }
}

fun rnaToProtein(input: String): ProteinString {
    val aminoAcids =
        input.toCharArray().drop(input.indexOf("AUG")).chunked(3).map {
            AminoAcid(it.map { char ->
                Nucleotide(
                    char
                )
            })
        }
    val trimmedAcids = aminoAcids.subList(0, aminoAcids.indexOfFirst { it.isStopCodon() })

    return ProteinString(trimmedAcids.map { ProteinCharacter(it.toCodon()) })
}