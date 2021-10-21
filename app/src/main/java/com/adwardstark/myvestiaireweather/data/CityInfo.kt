package com.adwardstark.myvestiaireweather.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
data class CityInfo(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val cityName: String,
    @SerializedName("country") val country: String,
    @SerializedName("population") val population: Long,
    @SerializedName("timezone") val timezone: Int
)
