package blushlang.compiler

const val VERSION = "2020.alpha.1"
const val VERSION_NAME = "Alpaca"
const val FILE_EXTENSION = "blsh"

lateinit var WorkingDirectory: String

enum class RootConceptsRegistrar(val alias: String) {
    CREATE("create"),
    REMOVE("delete"),
    NAVIGATE("navigate")
}