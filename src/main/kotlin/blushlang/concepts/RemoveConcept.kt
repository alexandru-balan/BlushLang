package blushlang.concepts

import blushlang.compiler.RootConceptsRegistrar

/**
 * @author Alexandru Balan
 * @since 2020.alpha.3
 * @last_modified 2020.alpha.3
 *
 * This concept is used to evaluate removal instructions and execute other concepts in removal mode.
 */
class RemoveConcept(private val line: String, private val lineNo: Int) : AbstractConcept(line, lineNo) {

    private var dependencyNumber: Int = -1

    init {
        super.alias = RootConceptsRegistrar.REMOVE.alias

        // Regular expressions
        super.regexps.add("$alias folder".toRegex())
        super.regexps.add("$alias file".toRegex())

        // Dependencies
        dependencies.add(FolderConcept(line, lineNo).apply { parent = this@RemoveConcept.alias })
        dependencies.add(FileConcept(line, lineNo).apply { parent = this@RemoveConcept.alias })
    }

    override fun evaluate(): Boolean {
        regexps.forEachIndexed { index, regex ->
            if (regex.containsMatchIn(line)) {
                dependencyNumber = index
                return true and dependencies[dependencyNumber]!!.evaluate()
            }
        }

        return false
    }

    override fun execute(mode: String?) {
        dependencies[dependencyNumber]?.execute(mode = alias)
    }

}