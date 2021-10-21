package com.adwardstark.myvestiaireweather.data.converters

import androidx.room.TypeConverter
import com.adwardstark.myvestiaireweather.data.DayInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
class DaysForecastTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<DayInfo> {
        val type = object: TypeToken<List<DayInfo>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(list: List<DayInfo>): String {
        val type = object: TypeToken<List<DayInfo>>() {}.type
        return Gson().toJson(list, type)
    }
}