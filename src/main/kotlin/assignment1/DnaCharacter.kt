package assignment1

open class DnaCharacter(private val symbol: Char) : Symbol(symbol, alphabet) {

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

    fun toMasked(masked: Boolean): MaskedDnaCharacter {
        if (!masked) {
            return MaskedDnaCharacter(symbol)
        }
        return if (symbol.isUpperCase()) MaskedDnaCharacter('N')
        else MaskedDnaCharacter('n')
    }

}

class MaskedDnaCharacter(private val symbol: Char) : Symbol(symbol, alphabet) {

    companion object {
        const val alphabet = "ATGCN"
    }

    fun complement(): DnaCharacter {
        throw IllegalArgumentException("The complement of masked strings is not well defined!")
    }

    fun toMasked(masked: Boolean): MaskedDnaCharacter {
        if (!masked) {
            return MaskedDnaCharacter(symbol)
        }
        return if (symbol.isUpperCase()) MaskedDnaCharacter('N')
        else MaskedDnaCharacter('n')
    }

}