package com.adwardstark.myvestiaireweather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adwardstark.myvestiaireweather.data.CityForecast

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
@Database(entities = [CityForecast::class], version = 1)
abstract class WeatherDB: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}