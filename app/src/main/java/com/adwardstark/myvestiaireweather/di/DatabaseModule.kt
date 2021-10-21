package com.adwardstark.myvestiaireweather.di

import android.content.Context
import androidx.room.Room
import com.adwardstark.myvestiaireweather.api.OpenWeatherService
import com.adwardstark.myvestiaireweather.data.WeatherRepository
import com.adwardstark.myvestiaireweather.db.WeatherDB
import com.adwardstark.myvestiaireweather.db.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    @Named("WeatherDB")
    fun provideWeatherDatabase(@ApplicationContext appContext: Context): WeatherDB {
        return Room.databaseBuilder(appContext, WeatherDB::class.java, "weatherDB").build()
    }

    @Provides
    @Named("WeatherDao")
    fun provideWeatherDao(@Named("WeatherDB") weatherDB: WeatherDB): WeatherDao {
        return weatherDB.weatherDao()
    }

    @Provides
    @Singleton
    @Named("WeatherRepo")
    fun provideWeatherRepo(@Named("OpenWeatherService") weatherService: OpenWeatherService,
                           @Named("WeatherDao") weatherDao: WeatherDao): WeatherRepository {
        return WeatherRepository(weatherService, weatherDao)
    }
}