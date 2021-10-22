package com.adwardstark.myvestiaireweather

import com.adwardstark.myvestiaireweather.data.*

/**
 * Created by Aditya Awasthi on 22/10/21.
 * @author github.com/adwardstark
 */
class FakeRepository(private val isNullable: Boolean = false): IRepository {

    companion object {
        fun fakeForecast(): CityForecast {
            val cityInfo = CityInfo(0,"Paris","FR",10000, 7200)
            val daysInfo = listOf(
                DayInfo(0,0,0,
                    TemperatureInfo(0.0,0.0,0.0,0.0,0.0,0.0),
                    FeelsLikeInfo(0.0,0.0,0.0,0.0),
                    0,0,
                    listOf(WeatherInfo(0, "Rain", "Light rain", "10d")),
                    0.0,0.0,0.0, 0.0, 0.0
                )
            )
            return CityForecast(city = cityInfo, daysForecast = daysInfo)
        }
    }

    override suspend fun getDailyForecast(
        location: String,
        limit: Int,
        fromNetwork: Boolean
    ): CityForecast? {
        return if(isNullable) null else fakeForecast()
    }
}