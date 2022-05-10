package com.tatam.minitrademe.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tatam.minitrademe.data.network.models.ListingResponse
import com.tatam.minitrademe.data.usecases.GetLatestListingsUseCase
import com.tatam.minitrademe.data.usecases.MyFakeRepository
import com.tatam.minitrademe.util.MainCoroutineScopeRule
import com.tatam.minitrademe.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*

class LatestListingsViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var mockObserver: Observer<ListingListState>

    private lateinit var myViewModel: LatestListingsViewModel

    @Mock
    private lateinit var useCase: GetLatestListingsUseCase

    @Mock
    private lateinit var listingList: ListingResponse

    private lateinit var viewState: ListingListState

    @Captor
    private lateinit var captor: ArgumentCaptor<ListingListState>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewState = ListingListState()
        useCase = GetLatestListingsUseCase(MyFakeRepository())
        myViewModel = LatestListingsViewModel(useCase)
    }

    @Test
    fun onListingRetrieved() {
        runBlocking {
            val flow = flow {
                emit(Resource.Loading())
                delay(10)
                emit(Resource.Success(listingList))
            }

            Mockito.`when`(useCase.invoke()).thenReturn(flow)
            val liveData = myViewModel.listingStateLiveData
            liveData.observeForever(mockObserver)

            Mockito.verify(mockObserver).onChanged(captor.capture())
            assertEquals(true, captor.value.isLoading)
            coroutineScope.advanceTimeBy(10)
            Mockito.verify(mockObserver, Mockito.times(2)).onChanged(captor.capture())
            assertEquals(listingList, captor.value.listings)
        }
    }
}