package com.adwardstark.myvestiaireweather.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adwardstark.myvestiaireweather.databinding.FragmentListBinding
import com.adwardstark.myvestiaireweather.ui.WeatherServiceViewModel
import com.adwardstark.myvestiaireweather.ui.adapters.DailyForecastAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var viewBinder: FragmentListBinding
    private val weatherServiceViewModel: WeatherServiceViewModel by viewModels()
    private val dailyForecastAdapter: DailyForecastAdapter by lazy { DailyForecastAdapter() }

    companion object {
        private val TAG = ListFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinder = FragmentListBinding.inflate(layoutInflater, container, false)
        return viewBinder.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinder.rvWeatherList.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = dailyForecastAdapter
            itemAnimator = DefaultItemAnimator()
        }

        viewBinder.swipeRefreshLayout.isRefreshing = true
        viewBinder.swipeRefreshLayout.setOnRefreshListener {
            weatherServiceViewModel.getDailyForecasts("Paris", 16)
        }

        dailyForecastAdapter.onItemClicked {
            Log.d(TAG, "->onItemClicked() $it")
            Navigation.findNavController(viewBinder.root)
                .navigate(ListFragmentDirections.actionListFragmentToDetailsFragment(it))
        }

        weatherServiceViewModel.dailyForecasts.observe(viewLifecycleOwner) {
            viewBinder.swipeRefreshLayout.isRefreshing = false
            if(it.code == 200) {
                Log.d(TAG, "->dailyForecasts() status: success, data: $it")
                dailyForecastAdapter.newList(it.list)
                viewBinder.cityTxt.text = "${it.city.cityName}, ${it.city.country}"
            } else {
                Log.d(TAG, "->dailyForecasts() status: failed, data: $it")
            }
        }

        weatherServiceViewModel.getDailyForecasts("Paris", 16)
    }
}