package blushlang.compiler

import blushlang.concepts.AbstractConcept

object Executor {
    var concepts: MutableList<AbstractConcept?> = MutableList(0) { null }

    fun execute() {
        concepts.forEach {
            it?.execute()
        }
    }
}