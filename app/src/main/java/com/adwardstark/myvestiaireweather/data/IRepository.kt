package com.adwardstark.myvestiaireweather.data

/**
 * Created by Aditya Awasthi on 22/10/21.
 * @author github.com/adwardstark
 */
interface IRepository {
    suspend fun getDailyForecast(location: String,
                                  limit: Int,
                                  fromNetwork: Boolean): CityForecast?
}