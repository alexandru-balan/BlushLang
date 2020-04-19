package blushlang.concepts

import java.io.File

/**
 * @author Alexandru Balan
 * @since 2020.alpha.1
 * @last_modified 2020.alpha.2
 *
 * This concept is used for file creation. If the 'here' keyword is detected then the folder will be created inside the
 * current working directory. If a path is specified then the folder will be created at that path, whether the path exists
 * or not at that moment.
 *
 * A valid path is a valid Unix path including with "./" and "../", but you need to type the trailing "/" at the end
 * of the path. For example "./testGround" is not a valid path, but "./testGround/" is.
 *
 * @param [line] : [String] is the line that caused the execution of this concept
 * @param [lineNo] : [Int] is the line number. It is used for error throwing.
 *
 * @sample [create folder 'testFolder' here] | [create folder 'testFolder' at "../parent/folder_2/"]
 */
class FolderConcept(private val line: String, private val lineNo: Int) : AbstractConcept(line, lineNo) {

    private var caseMatched: Int = -1
    private var matchedPath: String = ""
    private var result: Boolean = false

    init {
        super.alias = "folder"

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

    private fun getFolderName(mode: String): String {
        return line
                .replace("$mode folder", "")
                .trim()
                .split(" ")[0]
                .replace(Regex("['\"]"), "")
    }

    override fun execute(mode: String?) {

        val name = getFolderName(mode!!)

        when (mode) {

            "create" -> {
                if (caseMatched != -1) {
                    when (caseMatched) {
                        1 -> result = File("$matchedPath/$name").mkdirs()
                        2 -> result = File(name).mkdir()
                    }
                }
            }

        }

    }
}