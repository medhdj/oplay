package com.medhdj.core.extension

import org.amshove.kluent.`should be equal to`
import org.joda.time.DateTime
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
}
