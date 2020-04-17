package blushlang.concepts

class ConceptFactory private constructor(){

    companion object {
        fun create(keyword : String, line : String, lineNo : Int) : AbstractConcept? {
            when (keyword) {
                "create" -> return CreateConcept(line, lineNo)
            }

            return null
        }
    }

}