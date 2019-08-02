package code2snippets

import java.io.BufferedReader

class CommentExtractor(buffer: BufferedReader) {

    fun getSnippets() = sequence {
        yield(Snippet("test"))
    }
}