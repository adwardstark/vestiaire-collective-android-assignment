package com.adwardstark.myvestiaireweather.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adwardstark.myvestiaireweather.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Aditya Awasthi on 21/10/21.
 * @author github.com/adwardstark
 */
@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var viewBinder: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinder = FragmentListBinding.inflate(layoutInflater, container, false)
        return viewBinder.root
    }

}