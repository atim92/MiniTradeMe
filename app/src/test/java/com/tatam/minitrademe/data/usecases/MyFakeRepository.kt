package com.tatam.minitrademe.data.usecases

import com.tatam.minitrademe.data.network.models.Listing
import com.tatam.minitrademe.data.network.models.ListingResponse
import com.tatam.minitrademe.data.repos.ListingsRepository
import kotlinx.coroutines.flow.flow

class MyFakeRepository : ListingsRepository {

    override suspend fun getLatestListings(): ListingResponse {
        val listing = Listing(region = "Auckland")
        val listing1 = Listing()
        val listing2 = Listing()
        return ListingResponse(3, 3, 0, listOf(listing, listing1, listing2))
    }
}