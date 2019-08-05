package com.rustyrazorblade.code2snippets

import java.io.BufferedReader

class SnippetExtractor(commentMatcher: CommentMatcher, buffer: BufferedReader) : Iterable<Snippet> {
    override fun iterator(): Iterator<Snippet> {
        return getSnippets().listIterator()
    }

    val snippets = mutableMapOf<String, Snippet>()
    val snippetsInProgress = mutableMapOf<String, Snippet>()

    init {

        val lines = buffer.lines().map { commentMatcher.process(it) }.forEach {
            when(it) {
                is Line.CommentMarker -> {
                    // if it exists in snippets already, throw exception
                    if(it.name in snippets) throw DuplicateSnippetException(it.name)
                    else if(it.name in snippetsInProgress) {
                        // move to snippets
                        snippets[it.name] = snippetsInProgress[it.name]!!
                        snippetsInProgress.remove(it.name)
                    }
                    else {
                        snippetsInProgress[it.name] = Snippet(it.name)
                    }
                }
                is Line.Code -> {
                    for(s in snippetsInProgress.values) {

                        s.append(it)
                    }
                }
            }

        }
    }

    class DuplicateSnippetException(name: String) : Throwable() {

    }

    fun getSnippets() : List<Snippet> {
        return snippets.values.toList()
    }
}