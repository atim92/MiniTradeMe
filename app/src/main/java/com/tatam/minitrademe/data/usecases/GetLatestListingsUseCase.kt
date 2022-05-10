package com.tatam.minitrademe.data.usecases

import com.tatam.minitrademe.data.network.models.ListingResponse
import com.tatam.minitrademe.data.repos.ListingsRepository
import com.tatam.minitrademe.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLatestListingsUseCase @Inject constructor(
    private val repository: ListingsRepository
) {

    companion object {
        const val DEFAULT_HTTP_EXC_MSG = "An unexpected error occured"
        const val DEFAULT_IO_EXC_MSG = "Couldn't reach server"
    }

    //To call use case as a function
    operator fun invoke(): Flow<Resource<ListingResponse>> = flow {
        try {
            emit(Resource.Loading())
            val listings = repository.getLatestListings()
            emit(Resource.Success(listings))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: DEFAULT_HTTP_EXC_MSG))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: DEFAULT_IO_EXC_MSG))
        }
    }
}