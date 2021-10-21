package com.adwardstark.myvestiaireweather.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adwardstark.myvestiaireweather.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var viewBinder: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinder = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return viewBinder.root
    }
}