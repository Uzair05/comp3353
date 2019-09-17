package assignment1

class DnaString(private val string: List<DnaCharacter>) : SymbolString<DnaCharacter>(string) {

    constructor(string: String) : this(string.toCharArray().map { DnaCharacter(it) })

    fun reverseComplement(): DnaString = this.reversed().complement()

    fun complement(): DnaString {
        return DnaString(string.map { it.complement() })
    }

    fun reversed(): DnaString {
        return DnaString(string.reversed())
    }
}

class MaskedDnaString(private val string: List<MaskedDnaCharacter>) : SymbolString<MaskedDnaCharacter>(string) {

    constructor(string: String) : this(string.toCharArray().map { MaskedDnaCharacter(it) })

    fun countHighGc(
        k: Int = 100,
        threshold: Double = 0.7
    )
            : Int {

        val counter = SequenceCounter<MaskedDnaCharacter>(k) { "GC".contains(it.toString().toUpperCase()) }

        val scaledThreshold = threshold * k

        return string.map { symbol ->
            if (counter.readBit(symbol) > scaledThreshold) {
                1
            } else {
                0
            }
        }.sum()
    }

    fun maskByGc(
        k: Int = 100,
        threshold: Double = 0.7
    ): MaskedDnaString {
        val maskedCharacters = mutableListOf<MaskedDnaCharacter>()
        val counter = SequenceCounter<MaskedDnaCharacter>(k) { "GC".contains(it.toString().toUpperCase()) }
        val scaledThreshold = threshold * k

        string.forEachIndexed { idx, bit ->
            if (counter.readBit(bit) > scaledThreshold) {
                while (maskedCharacters.size <= idx) {
                    maskedCharacters.add(string[maskedCharacters.size].toMasked(maskedCharacters.size + k > idx))
                }
            }
        }

        maskedCharacters.addAll(string.drop(maskedCharacters.size).map { it.toMasked(false) })
        return MaskedDnaString(maskedCharacters)
    }

}