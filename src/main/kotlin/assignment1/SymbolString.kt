package assignment1

abstract class SymbolString<T : Symbol>(open val string: List<T>) {

    @Override
    override fun toString(): String {
        return string.joinToString("")
    }

    @Override
    override fun equals(other: Any?): Boolean {
        return (other is SymbolString<*> && other.string == string)
    }

    override fun hashCode(): Int {
        return string.hashCode()
    }

}