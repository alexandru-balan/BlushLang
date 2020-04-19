package blushlang.concepts

class ConceptFactory private constructor(){

    companion object {
        fun create(keyword : String, line : String, lineNo : Int) : AbstractConcept? {
            when (keyword) {
                "create" -> return CreateConcept(line, lineNo)
                else -> error("Unrecognized concept at line $lineNo: ${line.split(" ")[0]}")
            }
        }
    }

}