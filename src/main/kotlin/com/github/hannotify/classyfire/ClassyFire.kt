package com.github.hannotify.classyfire

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.hannotify.classyfire.ui.Ui
import java.io.File

class ClassyFire : CliktCommand() {
    private val categoriesFilePath: String by option(
                names = arrayOf("-c", "--categories-file-path"),
                help = "Path of the categories data file (input).")
            .default("src/test/resources/categories/categories.txt")
    private val transactionsFilePath: String by option(
                names = arrayOf("-t", "--transactions-file-path"),
                help = "Path of the transactions data file (input).")
            .default("src/test/resources/transactions/test.csv")
    private val classificationsDirectoryPath: String by option(
                names = arrayOf("-l", "--classifications-directory-path"),
                help = "Path of the classifications directory (output).")
            .default("src/test/resources/classifications")

    override fun run() {
        Ui(categoriesFilePath, transactionsFilePath, buildClassificationsFilePath()).start()
    }

    private fun buildClassificationsFilePath(): String =
            "$classificationsDirectoryPath/${transactionsFilePath.substringAfterLast("/")}"
}

fun main(args: Array<String>) = ClassyFire().main(args)
