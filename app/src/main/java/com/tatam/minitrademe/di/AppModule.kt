package com.tatam.minitrademe.di

import com.squareup.moshi.Moshi
import com.tatam.minitrademe.data.network.HeaderInterceptor
import com.tatam.minitrademe.data.repos.ListingsRepositoryImpl
import com.tatam.minitrademe.data.repos.ListingsRepository
import com.tatam.minitrademe.data.network.TradeMeService
import com.tatam.minitrademe.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideTradeMeApi(retrofit: Retrofit): TradeMeService {
        return retrofit.create(TradeMeService::class.java)
    }

    @Provides
    @Singleton
    fun provideListingRepository(api: TradeMeService): ListingsRepository {
        return ListingsRepositoryImpl(api)
    }
}