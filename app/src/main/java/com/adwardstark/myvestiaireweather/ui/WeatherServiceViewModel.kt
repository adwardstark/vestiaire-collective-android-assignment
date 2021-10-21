package com.adwardstark.myvestiaireweather.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwardstark.myvestiaireweather.Constants.API_KEY
import com.adwardstark.myvestiaireweather.Constants.MODE
import com.adwardstark.myvestiaireweather.Constants.UNITS
import com.adwardstark.myvestiaireweather.api.OpenWeatherService
import com.adwardstark.myvestiaireweather.data.WeatherResponse
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
    @Named("OpenWeatherService") private val weatherService: OpenWeatherService
    ): ViewModel() {

    companion object {
        private val TAG = WeatherServiceViewModel::class.java.simpleName
    }

    private val _dailyForecasts = MutableLiveData<WeatherResponse>()
    val dailyForecasts: LiveData<WeatherResponse>
        get() = _dailyForecasts

    fun getDailyForecasts(location: String, limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val request = weatherService.getDailyForecast(
                location = location,
                mode = MODE,
                units = UNITS,
                count = limit,
                apiKey = API_KEY,
            )
            if(request.isSuccessful) {
                _dailyForecasts.postValue(request.body())
            } else {
                Log.d(TAG, "->getDailyForecasts() errorCode: ${request.code()}")
            }
        }
    }
}