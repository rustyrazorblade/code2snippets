package com.rustyrazorblade.code2snippets

import org.assertj.core.api.Assertions.assertThat
import java.io.BufferedReader
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CommentExtractorTest {

    lateinit var basic : BufferedReader

    @BeforeTest
    fun loadBuffers() {
        basic = this.javaClass.getResourceAsStream("Basic.kt").bufferedReader()
    }

    @Test
    fun getSnippets() {

        val c = CommentExtractor(basic)
        assertEquals(1, c.getSnippets().count())
        val snippet = c.getSnippets().first()
        assertThat(snippet.name).isEqualTo("example")
    }
}