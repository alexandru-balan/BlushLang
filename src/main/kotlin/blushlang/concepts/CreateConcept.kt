package blushlang.concepts

class CreateConcept(private val line : String, private val lineNo : Int) : AbstractConcept(line, lineNo) {

    private var dependencyNo : Int = -1

    init {
        super.alias = "create"

        // Setting the evaluation regular expressions
        super.regexps.add( Regex("create file"))
        super.regexps.add(Regex("create folder"))

        // Setting the dependencies for evaluation
        super.dependencies.add(FileConcept(line, lineNo))
        super.dependencies.add(FolderConcept(line, lineNo))
    }

    override fun evaluate(): Boolean {
        regexps.forEachIndexed { index, regex ->
            if (regex.containsMatchIn(line)) {
                dependencyNo = index
                return true and dependencies[index]?.evaluate()!!
            }
        }

        return false
    }

    override fun execute(mode: String?) {
        dependencies[dependencyNo]?.execute("create")
    }
}