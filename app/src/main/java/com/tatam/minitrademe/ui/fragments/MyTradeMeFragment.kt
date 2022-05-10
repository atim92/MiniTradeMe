package com.tatam.minitrademe.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tatam.minitrademe.databinding.FragmentMyTradeMeBinding

class MyTradeMeFragment : Fragment() {

    private lateinit var binding: FragmentMyTradeMeBinding

    companion object {
        @JvmStatic
        fun newInstance() = MyTradeMeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMyTradeMeBinding.inflate(layoutInflater)
        return binding.root
    }
}