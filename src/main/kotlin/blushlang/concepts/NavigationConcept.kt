package blushlang.concepts

import blushlang.compiler.RootConceptsRegistrar

/**
 * @author Alexandru Balan
 * @since 2020.alpha.3
 * @last_modified 2020.alpha.3
 *
 * This concept is used to navigate between folders
 */
class NavigationConcept(private val line: String, private val lineNo: Int) : AbstractConcept(line, lineNo) {

    private var dependencyNo: Int = -1

    init {
        alias = RootConceptsRegistrar.NAVIGATE.alias

        // Regular expressions for evaluation
        regexps.add("$alias to".toRegex())

        // Dependencies
        dependencies.add(FolderConcept(line, lineNo).apply { parent = this@NavigationConcept.alias })
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