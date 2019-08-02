package com.rustyrazorblade.code2snippets

sealed class Line {


    class CommentMarker(name: String) : Line() {

    }
    class Code(content: String) : Line() {

    }


}