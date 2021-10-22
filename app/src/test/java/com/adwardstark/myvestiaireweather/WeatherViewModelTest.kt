package com.adwardstark.myvestiaireweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.adwardstark.myvestiaireweather.data.*
import com.adwardstark.myvestiaireweather.ui.WeatherServiceViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Aditya Awasthi on 22/10/21.
 * @author github.com/adwardstark
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var cityForecastObserver: Observer<CityForecast?>

    @Test
    fun testShouldReturnCityForecast() = testCoroutineRule.runBlockingTest {
        val viewModel = WeatherServiceViewModel(FakeRepository())
        viewModel.getDailyForecasts("Paris", 16, true)
        viewModel.dailyForecasts.observeForever(cityForecastObserver)

        verify(cityForecastObserver).onChanged(FakeRepository.fakeForecast())
        viewModel.dailyForecasts.removeObserver(cityForecastObserver)
    }

    @Test
    fun testShouldReturnNull() = testCoroutineRule.runBlockingTest {
        val viewModel = WeatherServiceViewModel(FakeRepository(true))
        viewModel.getDailyForecasts("Paris", 16, true)
        viewModel.dailyForecasts.observeForever(cityForecastObserver)

        verify(cityForecastObserver).onChanged(null)
        viewModel.dailyForecasts.removeObserver(cityForecastObserver)
    }
}