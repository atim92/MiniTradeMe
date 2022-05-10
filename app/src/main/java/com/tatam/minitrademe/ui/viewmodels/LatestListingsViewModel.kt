package com.tatam.minitrademe.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatam.minitrademe.data.network.models.Listing
import com.tatam.minitrademe.data.usecases.GetLatestListingsUseCase
import com.tatam.minitrademe.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LatestListingsViewModel @Inject constructor(
    private val getLatestListingUseCase: GetLatestListingsUseCase
) : ViewModel() {

    private val _listingStateLiveData = MutableLiveData<ListingListState>()
    val listingStateLiveData
        get() = _listingStateLiveData

    init {
        retrieveLatestListings()
    }

    fun getListings() {
        retrieveLatestListings()
    }

    private fun retrieveLatestListings() {
        getLatestListingUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _listingStateLiveData.value =
                        ListingListState(listings = result.data?.list?.take(20) ?: emptyList())
                }
                is Resource.Error -> {
                    _listingStateLiveData.value =
                        ListingListState(error = result.message ?: "Unexpected error")
                }
                is Resource.Loading -> {
                    _listingStateLiveData.value = ListingListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class ListingListState(
    val isLoading: Boolean = false,
    val listings: List<Listing> = emptyList(),
    val error: String = ""
)