/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.rustyrazorblade.code2snippets

import picocli.CommandLine
import java.io.File
import java.nio.file.Files
import java.nio.file.Path




class Main {

    @CommandLine.Parameters(index="0")
    lateinit var inPath: Path

    @CommandLine.Parameters(index="1")
    lateinit var outPath: Path

    var typeMap = mapOf("kt" to "//")

    fun execute() {
        println("Executing")
        val allowedKeys = typeMap.keys

        val files = Files.walk(inPath)
        for(fp in files) {
            val ext = fp.toFile().extension
            if(ext in allowedKeys) {
                println("Scanning ${fp.toFile().absoluteFile}")
                val commentMatcher = CommentMatcher(ext)
                val extractor = SnippetExtractor(commentMatcher, fp.toFile().bufferedReader())
                for(snippet in extractor) {
                    writeSnippet(snippet)
                }
            }
        }


        Files.walk(inPath).use {
            println(it)
        }
        println("Done walking")
    }

    /**
     * Writes the snippet to the outPath
     */
    fun writeSnippet(snippet: Snippet) {
        val output = File(outPath.toFile(), "${snippet.name}.snippet")
        println("Writing ${snippet.name}")
        output.writeText(snippet.normalize())
    }

    fun testBoth() {

    }

}

fun main(args: Array<String>) {
    val mainObject = Main()
    CommandLine(mainObject).parseArgs(*args)
    mainObject.execute()

}
