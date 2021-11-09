package com.medhdj.core.extension

import com.medhdj.core.utils.DateTimeUtils
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import org.joda.time.format.DateTimeFormatter
import java.util.*


fun Date.asString(formatter: DateTimeFormatter = DateTimeUtils.SIMPLE_DATE_PATTERN): String =
    formatter.print(time)

fun DateTime.withTimeAtEndOfDay(): DateTime = millisOfDay().withMaximumValue()
