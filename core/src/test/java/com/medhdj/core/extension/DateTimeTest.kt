package com.medhdj.core.extension

import org.amshove.kluent.`should be equal to`
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.junit.Test

class DateTimeTest {

    @Test
    fun `test date to string transformation`() {
        // given
        val date = DateTime(2021, 11, 8, 0, 0).toDate()

        // when
        val stringDate = date.asString()

        // then
        stringDate `should be equal to` "08/11/2021"
    }

    @Test
    fun `test the correct week start-end calculation`() {
        // given
        DateTimeZone.setDefault(DateTimeZone.UTC)
        val date = DateTime(2021, 10, 2, 10, 20)
        val expectedStartOfTheWeek = DateTime(2021, 9, 27, 0, 0)
        val expectedEndOfTheWeek = DateTime(2021, 10, 3, 23, 59, 59, 999)

        // when
        val startOfTheWeek = date.startOfTheWeek()
        val endOfTheWeek = date.endOfTheWeek()

        // then
        startOfTheWeek `should be equal to` expectedStartOfTheWeek
        endOfTheWeek `should be equal to` expectedEndOfTheWeek
    }
}
