package blushlang.concepts

import blushlang.compiler.RootConceptsRegistrar
import blushlang.compiler.WorkingDirectory
import java.io.File

/**
 * @author Alexandru Balan
 * @since 2020.alpha.2
 * @last_modified 2020.alpha.3
 *
 * The file concept in similar to the folder concept, but the regex that retrieves the file name will check for an extension
 * which is mandatory.
 * @see [FolderConcept]
 *
 * @param [line] : [String] is the line that triggered the creation of this concept
 * @param [lineNo] : [Int] is the index of the line in the blush script
 *
 * @sample [create file 'file1.txt' here] | [create file 'some_file.blsh' at "./testGround/testGround2/"]
 */
class FileConcept(private val line: String, private val lineNo: Int) : AbstractConcept(line, lineNo) {

    private var caseMatched: Int = -1
    private var matchedPath: String = ""
    private var result: Boolean = false

    // Parent mechanism
    var parent: String = ""

    init {
        super.alias = "file"

        // Setting up the evaluation regular expressions
        super.regexps.add(Regex("'(((((\\.)|(\\.\\.))/)*)|(/))([a-zA-Z0-9_]+/?)*/'"))
        super.regexps.add(Regex("\"(((((\\.)|(\\.\\.))/)*)|(/))([a-zA-Z0-9_]+/?)*/\""))
        super.regexps.add(Regex("here"))
    }

    override fun evaluate(): Boolean {
        super.regexps.forEachIndexed { index, regex ->
            if (regex.containsMatchIn(line)) {
                caseMatched = if (index == 0 || index == 1) {
                    matchedPath = regexps[index].find(line)!!.value.replace("'", "").replace("\"", "")
                    1
                } else {
                    index
                }
                return true
            }
        }

        return false
    }

    private fun getFileName(): String {
        if (!Regex("[a-zA-Z0-9_]+\\.[a-z]+").containsMatchIn(line)) {
            error("Cannot find file name in line $lineNo: $line")
        }

        return line
                .replace("$parent file", "")
                .trim()
                .split(" ")[0]
                .replace("['\"]".toRegex(), "")
    }

    override fun execute(mode: String?) {
        val fileName = getFileName()

        when (mode) {

            RootConceptsRegistrar.CREATE.alias -> {
                if (caseMatched != -1) {
                    when (caseMatched) {
                        1 -> result = File("$WorkingDirectory/$matchedPath/$fileName").createNewFile()
                        2 -> result = File("$WorkingDirectory/$fileName").createNewFile()
                    }
                }
            }

            RootConceptsRegistrar.REMOVE.alias -> {
                if (caseMatched != -1) {
                    when (caseMatched) {
                        1 -> File("$WorkingDirectory/$matchedPath/$fileName").delete()
                        2 -> File("$WorkingDirectory/$fileName").delete()
                    }
                }
            }
        }
    }
}