package com.rustyrazorblade.code2snippets

sealed class Line {
    data class CommentMarker(val name: String) : Line()
    data class Code(val content: String) : Line()


}