package com.rustyrazorblade.code2snippets

class Snippet(val name: String) {
    val body = StringBuilder()

    fun append(line: Line.Code) {

        body.appendln(line.content)
    }

    /**
     * Returns the trimmed body
     */
    fun normalize() = body.toString().trimIndent()

}