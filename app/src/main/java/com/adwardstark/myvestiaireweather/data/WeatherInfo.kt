package com.adwardstark.myvestiaireweather.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
data class WeatherInfo(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)
