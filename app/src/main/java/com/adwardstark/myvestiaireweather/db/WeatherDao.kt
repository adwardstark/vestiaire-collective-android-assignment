package com.adwardstark.myvestiaireweather.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adwardstark.myvestiaireweather.data.CityForecast

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
@Dao
interface WeatherDao {

    @Query("SELECT * FROM cityforecast")
    fun getDailyForecasts(): CityForecast?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityForecasts(forecast: CityForecast)

    @Query("DELETE from cityforecast")
    fun deleteCityForecasts()
}