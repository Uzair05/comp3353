package assignment1

abstract class Symbol(
    private val symbol: Char,
    val alphabet: String
) {

    init {
        alphabet.find { it == symbol.toUpperCase() }
            ?: throw IllegalArgumentException("Passed symbol $symbol into invalid alphabet space $alphabet")
    }

    @Override
    override fun toString(): String {
        return symbol.toString()
    }

    @Override
    override fun equals(other: Any?): Boolean {
        return if (other == null) false
        else {
            if (other::class != this::class) return false
            other as Symbol
            return other.symbol == symbol
        }
    }

    @Override
    override fun hashCode(): Int {
        return symbol.hashCode()
    }
}