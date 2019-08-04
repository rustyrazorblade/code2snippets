package com.rustyrazorblade.code2snippets

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


internal class CommentMatcherTest {

    val matcher = CommentMatcher("kt")

    @Test
    fun testMatcherCode() {
        val result = matcher.process("test")
        assertThat(result).isInstanceOf(Line.Code::class.java)
    }

    @Test
    fun testMatcherNormalComment() {
        val result = matcher.process("// test")
        assertThat(result).isInstanceOf(Line.Code::class.java)
    }


    @Test
    fun testMatcherMarker() {
        val result = matcher.process("// :test")
        assertThat(result).isInstanceOf(Line.CommentMarker::class.java)
    }

    @Test
    fun testMatcherMarkerWithIndent() {
        val result = matcher.process("      // :test")
        assertThat(result).isInstanceOf(Line.CommentMarker::class.java)
    }
}