package com.tatam.minitrademe.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tatam.minitrademe.databinding.FragmentWatchlistBinding

class WatchlistFragment : Fragment() {

    private lateinit var binding: FragmentWatchlistBinding

    companion object {
        @JvmStatic
        fun newInstance() = WatchlistFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWatchlistBinding.inflate(layoutInflater)
        return binding.root
    }
}