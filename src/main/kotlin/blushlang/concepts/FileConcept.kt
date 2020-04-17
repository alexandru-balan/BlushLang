package blushlang.concepts

class FileConcept(private val line : String, private val lineNo : Int) : AbstractConcept(line, lineNo) {

    init {
        super.alias = "file"

        // Setting up the evaluation regular expressions
        super.regexps.add(Regex("'(((((\\.)|(\\.\\.))/)*)|(/))([a-zA-Z0-9_]+/?)*'"))
        super.regexps.add(Regex("\"(((((\\.)|(\\.\\.))/)*)|(/))([a-zA-Z0-9_]+/?)*\""))
        super.regexps.add(Regex("here"))
    }

    override fun evaluate(): Boolean {
        regexps.forEach {
            if (it.containsMatchIn(line)) return true
        }

        return false
    }

    override fun execute() {
        TODO("Not yet implemented")
    }
}