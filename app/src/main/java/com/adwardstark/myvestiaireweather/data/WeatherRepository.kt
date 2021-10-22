package com.adwardstark.myvestiaireweather.data

import android.util.Log
import com.adwardstark.myvestiaireweather.Constants
import com.adwardstark.myvestiaireweather.api.OpenWeatherService
import com.adwardstark.myvestiaireweather.db.WeatherDao
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
class WeatherRepository @Inject constructor(
    @Named("OpenWeatherService") private val weatherService: OpenWeatherService,
    @Named("WeatherDao") private val weatherDao: WeatherDao
    ): IRepository {

    companion object {
        private val TAG = WeatherRepository::class.java.simpleName
    }

    override suspend fun getDailyForecast(location: String, limit: Int, fromNetwork: Boolean): CityForecast? {
        Log.d(TAG, "->getDailyForecast() fromNetwork: $fromNetwork")
        return if(!fromNetwork) {
            weatherDao.getDailyForecasts() ?: getDailyForecastFromNetwork(location, limit)
        } else {
            getDailyForecastFromNetwork(location, limit)
        }
    }

    private suspend fun getDailyForecastFromNetwork(location: String,
                                            limit: Int,): CityForecast? {
        try {
            val response = weatherService.getDailyForecast(
                location = location,
                mode = Constants.MODE,
                units = Constants.UNITS,
                count = limit,
                apiKey = Constants.API_KEY,
            )
            return if(response.isSuccessful && response.body() != null) {
                val weatherResponse = response.body() as WeatherResponse
                val cityForecast = CityForecast(city = weatherResponse.city, daysForecast = weatherResponse.list)
                Log.d(TAG, "->getDailyForecastFromNetwork() deleting stored cache.")
                weatherDao.deleteCityForecasts().also {
                    Log.d(TAG, "->getDailyForecastFromNetwork() saving new cache.")
                    weatherDao.insertCityForecasts(cityForecast)
                }
                cityForecast
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e(TAG, "An exception occurred: ${e.message}")
            return null
        }
    }
}