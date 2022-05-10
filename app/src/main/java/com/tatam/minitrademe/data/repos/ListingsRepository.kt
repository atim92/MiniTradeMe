package com.tatam.minitrademe.data.repos

import com.tatam.minitrademe.data.network.models.ListingResponse

interface ListingsRepository {
    suspend fun getLatestListings() : ListingResponse
}