package com.adwardstark.myvestiaireweather.api

import com.adwardstark.myvestiaireweather.data.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
interface OpenWeatherService {

    @GET("daily")
    suspend fun getDailyForecast(
        @Query("q") location: String,
        @Query("mode") mode: String,
        @Query("units") units: String,
        @Query("cnt") count: Int,
        @Query("appid") apiKey: String
    ): Response<WeatherResponse>
}