package assignment1

class ProteinCharacter(val symbol: Char) : Symbol(symbol, "FLIMVSPTAYHQNKDECWRG") {

    companion object {
        val alphabet = "FLIMVSPTAYHQNKDECWRG"
    }

}

class ProteinString(override val string: List<ProteinCharacter>) : SymbolString<ProteinCharacter>(string) {

    constructor(string: String) : this(string.toCharArray().map { ProteinCharacter(it) })

}