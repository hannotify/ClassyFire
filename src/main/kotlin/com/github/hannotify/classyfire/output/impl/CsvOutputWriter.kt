package com.github.hannotify.classyfire.output.impl

import com.github.hannotify.classyfire.data.classification.Classification
import com.github.hannotify.classyfire.output.OutputWriter
import java.io.File
import java.nio.file.Path

class CsvOutputWriter : OutputWriter {
    val VALUE_SEPARATOR = ";"

    override fun write(classifications: Collection<Classification>, outputFilePath: Path) {
        File(outputFilePath.toString()).printWriter().use { out ->
            classifications.forEach {
                out.println("${it.transaction.toCsv(VALUE_SEPARATOR)}${VALUE_SEPARATOR}${it.category.toCsv(VALUE_SEPARATOR)}")
            }
        }
    }
}