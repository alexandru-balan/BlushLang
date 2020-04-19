package blushlang.compiler

import blushlang.handlers.ExtraArgsHandler
import java.io.File
import kotlin.system.exitProcess

private lateinit var inputFile : File

fun main (arguments : Array<String>) {

    ExtraArgsHandler.arguments = arguments
    ExtraArgsHandler.handle()

    if (ExtraArgsHandler.hasInputFile) {
        inputFile = ExtraArgsHandler.inputFile
    }

    Evaluator.inputFile = inputFile
    val validity = Evaluator.evaluate()

    if (validity) {
        Executor.concepts = Evaluator.concepts
        Executor.execute()
    } else {
        exitProcess(-1)
    }
}
