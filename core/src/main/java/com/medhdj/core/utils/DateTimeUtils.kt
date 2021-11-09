package com.medhdj.core.utils

import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

object DateTimeUtils {
    val SIMPLE_DATE_PATTERN: DateTimeFormatter = DateTimeFormat.forPattern("dd/MM/yyyy")
}
