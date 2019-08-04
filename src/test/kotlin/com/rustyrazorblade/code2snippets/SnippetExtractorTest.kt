package com.rustyrazorblade.code2snippets

import org.assertj.core.api.Assertions.assertThat
import java.io.BufferedReader
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SnippetExtractorTest {

    lateinit var basic : BufferedReader
    lateinit var matcher : CommentMatcher
    lateinit var extractor  : SnippetExtractor

    @BeforeTest
    fun loadBuffers() {
        basic = this.javaClass.getResourceAsStream("Basic.kt").bufferedReader()
        matcher = CommentMatcher("kt")
        extractor = SnippetExtractor(matcher, basic)
    }

    @Test
    fun getSnippets() {

        assertEquals(1, extractor.getSnippets().count())
        val snippet = extractor.getSnippets().first()
        assertThat(snippet.name).isEqualTo("example")
    }
}