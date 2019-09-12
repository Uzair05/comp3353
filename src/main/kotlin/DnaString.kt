class DnaString(private val string: List<DnaCharacter>) : SymbolString<DnaCharacter>(string) {

    constructor(string: String) : this(string.toCharArray().map { it -> DnaCharacter(it) })

    fun reverseComplement(): DnaString = this.reversed().complement()

    fun complement(): DnaString {
        return DnaString(string.map { it.complement() })
    }

    fun reversed(): DnaString {
        return DnaString(string.reversed())
    }

}