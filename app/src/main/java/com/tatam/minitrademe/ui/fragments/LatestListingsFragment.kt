package com.tatam.minitrademe.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tatam.minitrademe.R
import com.tatam.minitrademe.databinding.LatestListingsFragmentBinding
import com.tatam.minitrademe.ui.viewmodels.LatestListingsViewModel

class LatestListingsFragment : Fragment() {

    private lateinit var binding : LatestListingsFragmentBinding

    companion object {
        fun newInstance() = LatestListingsFragment()
    }

    private lateinit var viewModel: LatestListingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LatestListingsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.latest_listing_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cart -> {
                Toast.makeText(requireContext(), "Cart Pressed!", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_search -> {
                Toast.makeText(requireContext(), "Search Pressed!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LatestListingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}