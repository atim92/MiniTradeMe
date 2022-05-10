package com.tatam.minitrademe.data.repos

import com.tatam.minitrademe.data.network.TradeMeService
import com.tatam.minitrademe.data.network.models.ListingResponse
import javax.inject.Inject

class ListingsRepositoryImpl @Inject constructor(
    private val api: TradeMeService
) : ListingsRepository {

    override suspend fun getLatestListings(): ListingResponse {
        return api.retrieveListings()
    }
}