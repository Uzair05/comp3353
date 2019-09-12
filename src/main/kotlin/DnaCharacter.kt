class DnaCharacter(private val symbol: Char) : Symbol(symbol, "ATGC") {

    companion object {
        const val alphabet = "ATGC"
    }

    fun complement(): DnaCharacter {
        return DnaCharacter(
            when (symbol) {
                'A' -> 'T'
                'T' -> 'A'
                'G' -> 'C'
                'C' -> 'G'
                else -> throw IllegalStateException("Invalid DNA Character!")
            }
        )
    }

}