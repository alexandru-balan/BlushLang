package blushlang.compiler

import blushlang.concepts.AbstractConcept
import blushlang.concepts.ConceptFactory
import java.io.File

/**
 * @author Alexandru Balan
 * @since 2020.alpha.1
 * @last_modified 2020.alpha.2
 *
 * This is an object of the compiler that evaluates the validity of the Blush script. It will also save a list of all the
 * created concepts. This way the Executor won't have to recreate the concepts, and we can also save valuable data in
 * the concepts that will help us execute the code faster.
 */
object Evaluator {
    var inputFile: File = File("")

    var concepts: MutableList<AbstractConcept?> = MutableList(0) { null }

    fun evaluate(): Boolean {
        var index = 0
        inputFile.forEachLine { it ->
            index++
            val keyword = it.split(" ")[0]
            val newConcept = ConceptFactory.create(keyword, it, index).also { concept -> concepts.add(concept) }
            val lineValidity = newConcept?.evaluate()
            if (lineValidity == false) {
                error("Error at line $index: $it")
            }
        }

        return true
    }
}