package com.github.hannotify.classyfire.input

import com.github.hannotify.classyfire.input.impl.IngCsvInputReader

class InputReaderFactory {
    companion object {
        fun inputReader(inputFormat: InputFormat): InputReader {
            // TODO: return different implementation based on inputFormat enum.
            return IngCsvInputReader()
        }
    }
}