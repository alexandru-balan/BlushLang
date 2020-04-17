package blushlang.concepts

abstract class AbstractConcept (private val line : String, private val lineNo : Int) {
    var alias: String = ""
    var regexps : MutableList<Regex> = MutableList(0) { Regex("") }
    var dependencies : MutableList<AbstractConcept?> = MutableList(0) { null }

    abstract fun evaluate() : Boolean
    abstract fun execute(mode : String? = null)
}