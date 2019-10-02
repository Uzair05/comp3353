package assignment1

import java.io.File
import java.util.*

fun main(args: Array<String>) {

    check(args.size == 1) { println("Usage: args = [FILENAME]") }

    val threshold = 0.7
    val k = 100
    val counter = SequenceCounter<MaskedDnaCharacter>(k) { "GC".contains(it.toString().toUpperCase()) }
    val scaledThreshold = threshold * k

    val fileName = args[0]
    val file = File(fileName)
    val fileContent = file.bufferedReader().readLines()

    val outputStream = file.resolveSibling("Sec3Q2output.txt").outputStream().bufferedWriter()

    val pendingOutput = ArrayDeque<Char>()
    var readPointer = 0
    var writePointer = 0

    val eol = "\n"
//        System.getProperty("line.separator")

    fileContent.forEachIndexed { idx, line ->
        if (idx == 0) {
            outputStream.write(line)
        } else {
            line.forEach { bit ->
                pendingOutput.addLast(bit)
                readPointer++
                if (counter.readBit(MaskedDnaCharacter(bit)) > scaledThreshold) {
                    while (writePointer < readPointer) {
                        val outputBit = pendingOutput.removeFirst()
                        if (eol.find { outputBit == it } != null) {
                            outputStream.write(outputBit.toString())
                        } else {
                            outputStream.write(MaskedDnaCharacter(outputBit).toMasked(writePointer + k >= readPointer).toString())
                            writePointer++
                        }
                    }
                }
            }
            while (pendingOutput.size > k) {
                val outputBit = pendingOutput.removeFirst()
                outputStream.write(outputBit.toString())
                eol.find { it == outputBit } ?: writePointer++
            }
        }
        eol.forEach { pendingOutput.addLast(it) }
    }

    while (pendingOutput.isNotEmpty()) {
        val outputBit = pendingOutput.removeFirst()
        outputStream.write(outputBit.toString())
        eol.find { it == outputBit } ?: writePointer++
    }

    outputStream.close()

}