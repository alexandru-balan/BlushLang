package blushlang.compiler

import blushlang.concepts.ConceptFactory
import java.io.File

object Evaluator {
    var inputFile : File = File("")

    fun evaluate() : Boolean {
        var index = 0
        inputFile.forEachLine {
            index++
            val keyword = it.split(" ")[0]
            val lineValidity = ConceptFactory.create(keyword, it, index)?.evaluate()
            if (lineValidity == false) {
                error("Error at line $index: $it")
            }
        }

        return true
    }

}