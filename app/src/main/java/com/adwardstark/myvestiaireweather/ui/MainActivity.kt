package com.adwardstark.myvestiaireweather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adwardstark.myvestiaireweather.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewBinder: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)
    }
}