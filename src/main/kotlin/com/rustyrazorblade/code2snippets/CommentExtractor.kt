package com.rustyrazorblade.code2snippets

import java.io.BufferedReader

class CommentExtractor(buffer: BufferedReader) {



    val snippets = mapOf<String, Snippet>()
    val snippetsInProgress = mapOf<String, Snippet>()

    init {

        for(line in buffer.lines()) {
            // is it a marker?
            // either end an existing snippet or start a new one
            // if not a marker, add the line to any snippets in progress

        }
    }

    fun getSnippets() = sequence {
        yield(Snippet("test", "test"))
    }
}