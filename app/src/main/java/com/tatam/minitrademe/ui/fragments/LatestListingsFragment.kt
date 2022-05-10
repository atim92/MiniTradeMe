package com.tatam.minitrademe.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tatam.minitrademe.R
import com.tatam.minitrademe.data.network.models.Listing
import com.tatam.minitrademe.databinding.LatestListingsFragmentBinding
import com.tatam.minitrademe.ui.adapters.ListingAdapter
import com.tatam.minitrademe.ui.viewmodels.LatestListingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LatestListingsFragment : Fragment() {

    private lateinit var binding: LatestListingsFragmentBinding
    private val viewModel by viewModels<LatestListingsViewModel>()

    companion object {
        fun newInstance() = LatestListingsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LatestListingsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        //Lambda for clicking on listing item in case further action needed
        val openListingDetails = { listing: Listing ->
            Toast.makeText(requireContext(), "Listing ID ${listing.listingId} Details Pressed!", Toast.LENGTH_SHORT).show()
        }

        //Setting up a list
        val adapter = ListingAdapter(openListingDetails, requireContext())
        binding.rvLatestListings.adapter = adapter

        //Processing states
        viewModel.listingStateLiveData.observe(viewLifecycleOwner) { state ->
            if (state.isLoading) {
                binding.loading.visibility = View.VISIBLE
            }
            if (state.error.isNotBlank()) {
                binding.textError.text = state.error
                binding.loading.visibility = View.GONE
                binding.textError.visibility = View.VISIBLE
            }
            if (state.listings.isNotEmpty()) {
                adapter.setItems(state.listings)
                binding.loading.visibility = View.GONE
                binding.rvLatestListings.visibility = View.VISIBLE
            }
            binding.swipeToRefreshListings.isRefreshing = false
        }

        binding.swipeToRefreshListings.setOnRefreshListener {
            binding.rvLatestListings.removeAllViews()
            viewModel.getListings()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    //AppBar menu icons
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
}