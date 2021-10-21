package com.adwardstark.myvestiaireweather.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.adwardstark.myvestiaireweather.data.converters.CityInfoTypeConverter
import com.adwardstark.myvestiaireweather.data.converters.DaysForecastTypeConverter
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
@Entity(tableName = "cityforecast")
@TypeConverters(CityInfoTypeConverter::class, DaysForecastTypeConverter::class)
@Parcelize
data class CityForecast(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @SerializedName("city") val city: CityInfo,
    @SerializedName("list") val daysForecast: List<DayInfo>
): Parcelable