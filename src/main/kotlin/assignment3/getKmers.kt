package assignment3

fun getKmers(str: String, k: Int): List<String> {
    return (0..str.length - k).toList().map { str.substring(it, it + k) }
}