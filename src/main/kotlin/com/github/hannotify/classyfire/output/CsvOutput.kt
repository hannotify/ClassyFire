package com.github.hannotify.classyfire.output

import java.util.stream.Collectors

interface CsvOutput {
    fun getCsvFields(): List<String>
    fun toCsv(delimiter: String): String {
        return getCsvFields().stream()
                .collect(Collectors.joining(delimiter))
    }
}