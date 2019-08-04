package com.rustyrazorblade.code2snippets

class CommentMatcher(extension: String) {

    val regex : Regex

    class UnsupportedTypeException(message: String) : Throwable()

    companion object {
        val types = mapOf("kt" to CommentDeliminator.DoubleSlash,
                "java" to CommentDeliminator.DoubleSlash,
                "fio" to CommentDeliminator.Hash,
                "py" to CommentDeliminator.Hash,
                "c" to CommentDeliminator.DoubleSlash,
                "rb" to CommentDeliminator.Hash,
                "php" to CommentDeliminator.DoubleSlash,
                "erl" to CommentDeliminator.Percent,
                "rs" to CommentDeliminator.DoubleSlash,
                "js" to CommentDeliminator.DoubleSlash,
                "cpp" to CommentDeliminator.DoubleSlash,
                "swift" to CommentDeliminator.DoubleSlash)
    }

    init {
        val delim = types.getOrElse(extension) {
            throw UnsupportedTypeException("Extention $extension not supported, how did you even get here?")
        }
        // spaces, comment, spaces, :marker spaces
        regex = """\s*${delim.s}\s+:([a-z][a-z0-9]*)\s*""".toRegex()
    }

    /**
     * Processes a String line of code, transforming it to a Line, which may be either a special comment or a normal line
     */
    fun process(line: String) : Line {
        val tmp = regex.find(line)

        if(tmp != null) {
            return Line.CommentMarker(tmp!!.groupValues[1])
        } else {
            return Line.Code(line)
        }
    }


}
