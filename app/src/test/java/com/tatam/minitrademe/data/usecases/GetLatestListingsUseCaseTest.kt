package com.tatam.minitrademe.data.usecases

import com.tatam.minitrademe.data.network.models.Listing
import com.tatam.minitrademe.data.repos.ListingsRepository
import kotlinx.coroutines.flow.onEach
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetLatestListingsUseCaseTest {

    private lateinit var listingsRepository : ListingsRepository
    private lateinit var SUT : GetLatestListingsUseCase

    @Before
    fun setUp() {
        listingsRepository = MyFakeRepository()
        SUT = GetLatestListingsUseCase(listingsRepository)
    }

    @Test
    fun check_listing_usecase_success_response_size() {
        val listings = SUT.invoke()
        listings.onEach {
            assertEquals(it.data?.list?.size, 3)
        }
    }

    @Test
    fun check_listing_usecase_check_first_item() {
        val listingToCompare = Listing(region = "Auckland")
        val listings = SUT.invoke()
        listings.onEach {
            assertEquals(it.data?.list?.get(0), listingToCompare)
        }
    }
}
