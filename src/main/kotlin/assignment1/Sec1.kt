package assignment1

fun sec1Q1(input: String): String {
    return DnaString(input).reverseComplement().toString()
}

fun sec1Q2(input: String): String {
    return rnaToProtein(input).toString()
}