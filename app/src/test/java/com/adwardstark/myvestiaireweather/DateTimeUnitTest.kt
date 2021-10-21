package com.adwardstark.myvestiaireweather

import com.adwardstark.myvestiaireweather.utils.DateTimeUtils
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DateTimeUnitTest {

    @Test
    fun testFormattedDateIsCorrect() {
        val dayAndDate = DateTimeUtils.getFormattedDate(1634814000)
        assertEquals("Thursday, 21 October", dayAndDate)
    }

    @Test
    fun testFormattedTimeIsCorrect() {
        val time = DateTimeUtils.getFormattedTime(1634814000,
            TimeZone.getTimeZone("GMT"))
        assertEquals("11:00 am", time)
    }
}