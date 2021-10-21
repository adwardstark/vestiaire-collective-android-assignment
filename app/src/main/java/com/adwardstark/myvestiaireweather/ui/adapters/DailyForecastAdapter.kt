package com.adwardstark.myvestiaireweather.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adwardstark.myvestiaireweather.R
import com.adwardstark.myvestiaireweather.data.DayInfo
import com.adwardstark.myvestiaireweather.databinding.LayoutWeatherItemBinding
import com.adwardstark.myvestiaireweather.utils.DateTimeUtils.getFormattedDate
import com.adwardstark.myvestiaireweather.utils.DateTimeUtils.getTimeOfDay
import com.bumptech.glide.Glide

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
class DailyForecastAdapter: RecyclerView.Adapter<DailyForecastAdapter.DailyForecastViewHolder>() {

    inner class DailyForecastViewHolder(val viewBinder: LayoutWeatherItemBinding)
        : RecyclerView.ViewHolder(viewBinder.root)

    private val differCallback = object: DiffUtil.ItemCallback<DayInfo>() {
        override fun areItemsTheSame(oldItem: DayInfo, newItem: DayInfo): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: DayInfo, newItem: DayInfo): Boolean {
            return oldItem == newItem
        }
    }

    private val _differList = AsyncListDiffer(this, differCallback)
    private fun currentList(): List<DayInfo> = _differList.currentList
    fun newList(list: List<DayInfo>) = _differList.submitList(list)

    override fun getItemCount(): Int = _differList.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        return DailyForecastViewHolder(LayoutWeatherItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        with(holder) {
            with(currentList()[position]) {
                val res = itemView.resources
                viewBinder.dateTxt.text = getFormattedDate(date)
                viewBinder.descriptionTxt.text = weather[0].description.replaceFirstChar(Char::titlecase)

                when(getTimeOfDay()) {
                    in 5..11 -> {
                        viewBinder.temperatureTxt.text = res
                            .getString(R.string.celcius, temperature.morning.toInt().toString())
                    }
                    in 12..16 -> {
                        viewBinder.temperatureTxt.text = res
                            .getString(R.string.celcius, temperature.day.toInt().toString())
                    }
                    in 17..20 -> {
                        viewBinder.temperatureTxt.text = res
                            .getString(R.string.celcius, temperature.evening.toInt().toString())
                    } else -> {
                    viewBinder.temperatureTxt.text = res
                        .getString(R.string.celcius, temperature.night.toInt().toString())
                    }
                }

                Glide.with(viewBinder.root)
                    .load(res.getString(R.string.weather_icon_url, weather[0].icon))
                    .into(viewBinder.icon)
            }
        }
    }
}