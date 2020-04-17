package blushlang.concepts

class FolderConcept (private val line : String, private val lineNo : Int) : AbstractConcept(line, lineNo) {

    init {
        super.alias = "folder"

        super.regexps.add(Regex("'(((((\\.)|(\\.\\.))/)*)|(/))([a-zA-Z0-9_]+/?)*'"))
        super.regexps.add(Regex("\"(((((\\.)|(\\.\\.))/)*)|(/))([a-zA-Z0-9_]+/?)*\""))
        super.regexps.add(Regex("here"))
    }

    override fun evaluate(): Boolean {
        super.regexps.forEach {
            if (it.containsMatchIn(line)) return true
        }

        return false
    }

    override fun execute() {

    }
}