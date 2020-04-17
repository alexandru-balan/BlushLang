package blushlang.handlers

import blushlang.compiler.FILE_EXTENSION
import blushlang.compiler.VERSION
import blushlang.compiler.VERSION_NAME
import java.io.File
import java.lang.Exception

/**
 * @author Alexandru Balan
 * @since 2020.alpha.1
 *
 * @property [hasInputFile] : [Boolean] is true if an input script has been found
 * @property [arguments] : [Array] contains the arguments passed o the main method
 */
object ExtraArgsHandler {
    var hasInputFile : Boolean = false
    lateinit var inputFile : File
    var arguments : Array<String> = emptyArray()

    /**
     * Handles the arguments and interprets them
     */
    fun handle() {
        if (arguments.isEmpty()) {
            error("Arguments provided to the blush compiler!")
        }

        if (arguments.size > 1) {
            error("Too many arguments!")
        }

        if (arguments[0] matches Regex("[a-zA-Z0-9./]+.$FILE_EXTENSION")) {

            try {
                inputFile = File(arguments[0])
                hasInputFile = true
            }
            catch (exception : Exception) {
                hasInputFile = false
                error("Cannot open input file: ${exception.message}")
            }

            return
        }

        when (arguments[0]) {
            "--help" -> printHelpMessage()
            "--version" -> printVersion()
        }
    }

    private fun printHelpMessage() {
        val separator = "\t:\t"

        println("Welcome to the Blush compiler!")
        println("--help${separator}Prints this message.")
        println("--version${separator}Check the version of the compiler.")
        println()
        println("Pass the path to a blush file to compile it. Example: blushc ./file.$FILE_EXTENSION")
    }

    private fun printVersion() {
        val separator = "\t--\t"

        println("$VERSION$separator$VERSION_NAME")
    }
}