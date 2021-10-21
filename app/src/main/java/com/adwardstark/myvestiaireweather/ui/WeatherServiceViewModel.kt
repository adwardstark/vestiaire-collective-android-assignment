package com.adwardstark.myvestiaireweather.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwardstark.myvestiaireweather.data.CityForecast
import com.adwardstark.myvestiaireweather.data.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
@HiltViewModel
class WeatherServiceViewModel @Inject constructor(
    @Named("WeatherRepo") private val weatherRepository: WeatherRepository
    ): ViewModel() {

    companion object {
        private val TAG = WeatherServiceViewModel::class.java.simpleName
    }

    private val _dailyForecasts = MutableLiveData<CityForecast?>()
    val dailyForecasts: LiveData<CityForecast?>
        get() = _dailyForecasts

    fun getDailyForecasts(location: String, limit: Int, fromNetwork: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            val forecasts = weatherRepository.getDailyForecast(location, limit, fromNetwork)
            Log.d(TAG, "->getDailyForecasts() response: $forecasts")
            _dailyForecasts.postValue(forecasts)
        }
    }
}