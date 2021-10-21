package com.adwardstark.myvestiaireweather.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
object DateTimeUtils {

    fun getTimeOfDay(): Int {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    }

    fun getFormattedDate(timestamp: Long): String {
        return try {
            val date = Date(timestamp * 1000)
            SimpleDateFormat("EEEE, dd MMMM", Locale.getDefault()).format(date)
        } catch (e: Exception) {
            "-"
        }
    }

    fun getFormattedTime(timestamp: Long, timeZone: TimeZone? = null): String {
        return try {
            val date = Date(timestamp * 1000)
            val time = SimpleDateFormat("hh:mm a", Locale.getDefault())
            if (timeZone != null)
                time.timeZone = timeZone
            return time.format(date)
        } catch (e: Exception) {
            "-"
        }
    }
}