package com.adwardstark.myvestiaireweather

import android.content.Context
import androidx.room.Room
import org.junit.Test
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adwardstark.myvestiaireweather.data.*
import com.adwardstark.myvestiaireweather.db.WeatherDB
import com.adwardstark.myvestiaireweather.db.WeatherDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RoomDBTest {

    private lateinit var weatherDB: WeatherDB
    private lateinit var weatherDao: WeatherDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        weatherDB = Room.inMemoryDatabaseBuilder(context, WeatherDB::class.java).build()
        weatherDao = weatherDB.weatherDao()
    }

    @After
    fun cleanup() {
        weatherDB.close()
    }

    @Test
    fun testCityForecastShouldBeStoredInDb() = runBlocking {
        val cityInfo = provideCityForecast().first
        val daysInfo = provideCityForecast().second
        val cityForecast = CityForecast(city = cityInfo, daysForecast = daysInfo)
        weatherDao.insertCityForecasts(cityForecast)
        val storedCityForecast = weatherDao.getDailyForecasts()

        assertNotNull(storedCityForecast)
        assertEquals(cityInfo.cityName, storedCityForecast?.city?.cityName)
        assertEquals(cityInfo.country, storedCityForecast?.city?.country)
        assertEquals(cityInfo.timezone, storedCityForecast?.city?.timezone)

        assertEquals(daysInfo[0].date, storedCityForecast!!.daysForecast[0].date)
        assertEquals(daysInfo[0].temperature.max, storedCityForecast.daysForecast[0].temperature.max, 0.0)
        assertEquals(daysInfo[0].temperature.min, storedCityForecast.daysForecast[0].temperature.min, 0.0)
        assertEquals(daysInfo[0].pressure, storedCityForecast.daysForecast[0].pressure)
        assertEquals(daysInfo[0].humidity, storedCityForecast.daysForecast[0].humidity)
        assertEquals(daysInfo[0].date, storedCityForecast.daysForecast[0].date)
    }

    @Test
    fun testCityForecastShouldNotBePresentInDbBeforeInsertion() = runBlocking {
        val storedCityForecast = weatherDao.getDailyForecasts()
        assertNull(storedCityForecast)
    }

    @Test
    fun testCityForecastShouldNotBePresentInDbAfterDeletion() = runBlocking {
        val cityInfo = provideCityForecast().first
        val daysInfo = provideCityForecast().second
        val cityForecast = CityForecast(city = cityInfo, daysForecast = daysInfo)
        weatherDao.insertCityForecasts(cityForecast)
        var storedCityForecast = weatherDao.getDailyForecasts()

        assertNotNull(storedCityForecast)

        weatherDao.deleteCityForecasts()
        storedCityForecast = weatherDao.getDailyForecasts()

        assertNull(storedCityForecast)
    }

    private fun provideCityForecast(): Pair<CityInfo, List<DayInfo>> {
        val cityInfo = CityInfo(0,"Paris","FR",10000, 7200)
        val daysInfo = listOf(DayInfo(0,0,0,
            TemperatureInfo(0.0,0.0,0.0,0.0,0.0,0.0),
            FeelsLikeInfo(0.0,0.0,0.0,0.0),
            0,0,
            listOf(WeatherInfo(0, "Rain", "Light rain", "10d")),
            0.0,0.0,0.0, 0.0, 0.0
        ))
        return Pair(cityInfo, daysInfo)
    }
}