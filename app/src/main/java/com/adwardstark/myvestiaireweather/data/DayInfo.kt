package com.adwardstark.myvestiaireweather.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
@Parcelize
data class DayInfo(
    @SerializedName("dt") val date: Long,
    @SerializedName("sunrise") val sunriseTime: Long,
    @SerializedName("sunset") val sunsetTime: Long,
    @SerializedName("temp") val temperature: TemperatureInfo,
    @SerializedName("feels_like") val feelsLike: FeelsLikeInfo,
    @SerializedName("pressure") val pressure: Long,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("weather") val weather: List<WeatherInfo>,
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val degrees: Double,
    @SerializedName("clouds") val clouds: Double,
    @SerializedName("pop") val pop: Double,
    @SerializedName("rain") val rain: Double
): Parcelable
