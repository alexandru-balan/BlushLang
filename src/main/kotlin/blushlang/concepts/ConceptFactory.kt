package blushlang.concepts

import blushlang.compiler.RootConceptsRegistrar


class ConceptFactory private constructor() {

    companion object {
        fun create(keyword: String, line: String, lineNo: Int): AbstractConcept? {
            return when (keyword) {
                RootConceptsRegistrar.CREATE.alias -> CreateConcept(line, lineNo)
                RootConceptsRegistrar.REMOVE.alias -> RemoveConcept(line, lineNo)
                RootConceptsRegistrar.NAVIGATE.alias -> NavigationConcept(line, lineNo)
                else -> error("Unrecognized concept at line $lineNo: ${line.split(" ")[0]}")
            }
        }
    }

}