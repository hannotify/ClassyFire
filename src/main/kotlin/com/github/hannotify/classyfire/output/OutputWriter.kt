package com.github.hannotify.classyfire.output

import com.github.hannotify.classyfire.classification.Classification
import java.nio.file.Path

interface OutputWriter {
    fun write(classifications: Collection<Classification>, outputFilePath: Path)
}