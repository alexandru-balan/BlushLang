package blushlang.compiler

import blushlang.handlers.ExtraArgsHandler
import java.io.File

private lateinit var inputFile : File

fun main (arguments : Array<String>) {

    ExtraArgsHandler.arguments = arguments
    ExtraArgsHandler.handle()

    if (ExtraArgsHandler.hasInputFile) {
        inputFile = ExtraArgsHandler.inputFile
    }

    inputFile.forEachLine { line -> line.split(Regex(" ")).forEach { println(it) } }

    Evaluator.inputFile = inputFile
    println(Evaluator.evaluate())

    Executor.concepts = Evaluator.concepts
    Executor.execute()
}
