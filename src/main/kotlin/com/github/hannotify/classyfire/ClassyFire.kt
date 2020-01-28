package com.github.hannotify.classyfire

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.hannotify.classyfire.input.InputFormat
import com.github.hannotify.classyfire.input.InputReaderFactory
import java.nio.file.Path

class ClassyFire : CliktCommand() {
    private val inputDataPath: String by option(help = "Path of the input data file.").default("src/test/resources/test.csv")

    override fun run() {
        println(InputReaderFactory.inputReader(InputFormat.ING_CSV).read(Path.of(inputDataPath)))
    }
}

fun main(args: Array<String>) = ClassyFire().main(args)
