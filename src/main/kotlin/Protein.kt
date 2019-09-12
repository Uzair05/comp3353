class ProteinCharacter(val symbol: Char) : Symbol(symbol, "FLIMVSPTAYHQNKDECWRG")

class ProteinString(val string: List<ProteinCharacter>) : SymbolString<ProteinCharacter>(string)