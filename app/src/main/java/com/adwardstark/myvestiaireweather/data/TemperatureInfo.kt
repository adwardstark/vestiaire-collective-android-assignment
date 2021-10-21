package com.adwardstark.myvestiaireweather.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
data class TemperatureInfo(
    @SerializedName("day") val day: Double,
    @SerializedName("min") val min: Double,
    @SerializedName("max") val max: Double,
    @SerializedName("night") val night: Double,
    @SerializedName("eve") val evening: Double,
    @SerializedName("morn") val morning: Double
)
