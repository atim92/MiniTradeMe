package com.tatam.minitrademe.data.network

import com.tatam.minitrademe.data.network.models.ListingResponse
import retrofit2.http.GET

interface TradeMeService {
    @GET("latest.json")
    suspend fun retrieveListings(): ListingResponse
}