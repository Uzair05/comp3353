fun Q1(input: String): String {
    return DnaString(input).reverseComplement().toString()
}

fun Q2(input: String): String {
    return rnaToProtein(input).toString()
}