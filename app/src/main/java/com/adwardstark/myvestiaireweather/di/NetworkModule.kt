package com.adwardstark.myvestiaireweather.di

import com.adwardstark.myvestiaireweather.Constants.BASE_URL
import com.adwardstark.myvestiaireweather.api.OpenWeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named("OpenWeatherService")
    fun provideOpenWeatherService(): OpenWeatherService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherService::class.java)
    }
}