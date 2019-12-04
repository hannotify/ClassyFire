package com.github.hannotify.classyfire.output

import com.github.hannotify.classyfire.data.classification.Classification
import java.nio.file.Path

interface OutputWriter {
    fun write(classifications: Collection<Classification>, outputFilePath: Path)
}