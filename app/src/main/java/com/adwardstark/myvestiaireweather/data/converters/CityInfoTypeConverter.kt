package com.adwardstark.myvestiaireweather.data.converters

import androidx.room.TypeConverter
import com.adwardstark.myvestiaireweather.data.CityInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
class CityInfoTypeConverter {

    @TypeConverter
    fun fromString(value: String): CityInfo {
        val type = object: TypeToken<CityInfo>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(cityInfo: CityInfo): String {
        val type = object: TypeToken<CityInfo>() {}.type
        return Gson().toJson(cityInfo, type)
    }
}