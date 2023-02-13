package com.edwinkapkei.formula1.utilities

import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object CustomDateFormatter {
    fun getCurrentYear(): String {
        return DateTimeFormatter
            .ofPattern("yyyy")
            .withZone(ZoneOffset.UTC)
            .format(Instant.now())
    }
}