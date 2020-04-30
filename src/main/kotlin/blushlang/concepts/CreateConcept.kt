package blushlang.concepts

import blushlang.compiler.RootConceptsRegistrar


class CreateConcept(private val line: String, private val lineNo: Int) : AbstractConcept(line, lineNo) {

    private var dependencyNo: Int = -1

    init {
        alias = RootConceptsRegistrar.CREATE.alias

        // Setting the evaluation regular expressions
        super.regexps.add(Regex("create file"))
        super.regexps.add(Regex("create folder"))

        // Setting the dependencies for evaluation
        super.dependencies.add(FileConcept(line, lineNo).apply { parent = this@CreateConcept.alias })
        super.dependencies.add(FolderConcept(line, lineNo).apply { parent = this@CreateConcept.alias })
    }

    override fun evaluate(): Boolean {
        regexps.forEachIndexed { index, regex ->
            if (regex.containsMatchIn(line)) {
                dependencyNo = index
                return true and dependencies[index]!!.evaluate()
            }
        }

        return false
    }

    override fun execute(mode: String?) {
        dependencies[dependencyNo]?.execute(alias)
    }
}