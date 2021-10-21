package com.adwardstark.myvestiaireweather.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.adwardstark.myvestiaireweather.R
import com.adwardstark.myvestiaireweather.data.DayInfo
import com.adwardstark.myvestiaireweather.databinding.FragmentDetailsBinding
import com.adwardstark.myvestiaireweather.utils.DateTimeUtils
import com.adwardstark.myvestiaireweather.utils.DateTimeUtils.getFormattedTime
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var viewBinder: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    companion object {
        private val TAG = DetailsFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinder = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return viewBinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "->args() ${args.dailyData}")

        args.dailyData.apply {
            setupHeaderView()
            setupMiddleView()
        }

        viewBinder.backButton.setOnClickListener {
            Navigation.findNavController(viewBinder.root).navigateUp()
        }
    }

    private fun DayInfo.setupHeaderView() {
        viewBinder.dateTxt.text = DateTimeUtils.getFormattedDate(date)

        when(DateTimeUtils.getTimeOfDay()) {
            in 5..11 -> {
                viewBinder.temperatureTxt.text =
                    getString(R.string.celcius, temperature.morning.toInt().toString())
                viewBinder.feelsLikeTxt.text =
                    getString(R.string.feels_like, feelsLike.morning.toInt().toString())
            }
            in 12..16 -> {
                viewBinder.temperatureTxt.text =
                    getString(R.string.celcius, temperature.day.toInt().toString())
                viewBinder.feelsLikeTxt.text =
                    getString(R.string.feels_like, feelsLike.day.toInt().toString())
            }
            in 17..20 -> {
                viewBinder.temperatureTxt.text =
                    getString(R.string.celcius, temperature.evening.toInt().toString())
                viewBinder.feelsLikeTxt.text =
                    getString(R.string.feels_like, feelsLike.evening.toInt().toString())
            } else -> {
            viewBinder.temperatureTxt.text =
                getString(R.string.celcius, temperature.night.toInt().toString())
            viewBinder.feelsLikeTxt.text =
                getString(R.string.feels_like, feelsLike.night.toInt().toString())
        }
        }

        viewBinder.maxMinTxt.text = getString(R.string.max_min,
            temperature.max.toInt().toString(), temperature.min.toInt().toString())
        viewBinder.descriptionTxt.text = weather[0].description.replaceFirstChar(Char::titlecase)

        Glide.with(viewBinder.root)
            .load(getString(R.string.weather_icon_large_url, weather[0].icon))
            .into(viewBinder.icon)
    }

    private fun DayInfo.setupMiddleView() {
        viewBinder.sunriseTxt.text = getFormattedTime(sunriseTime)
        viewBinder.sunsetTxt.text = getFormattedTime(sunsetTime)

        viewBinder.windTxt.text = getString(R.string.speed, speed.toInt().toString())
        viewBinder.humidityTxt.text = getString(R.string.percent, humidity.toString())
        viewBinder.pressureTxt.text = getString(R.string.pressure_unit, pressure.toInt().toString())
    }
}