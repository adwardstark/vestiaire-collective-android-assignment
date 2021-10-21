package com.adwardstark.myvestiaireweather.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
data class WeatherResponse(
    @SerializedName("city") val city: CityInfo,
    @SerializedName("cod") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("cnt") val count: Int,
    @SerializedName("list") val list: List<DayInfo>
)
