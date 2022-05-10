package com.tatam.minitrademe.data.network

import com.tatam.minitrademe.util.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeaderInterceptor @Inject constructor() : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val request: Request = original.newBuilder()
            .header("Authorization",
                "OAuth " +
                        "oauth_consumer_key=${Constants.CONSUMER_KEY}," +
                        "oauth_signature_method=${Constants.SIGNATURE_METHOD}," +
                        "oauth_signature=${Constants.CONSUMER_SIGNATURE}")
            .method(original.method(), original.body())
            .build()

        return chain.proceed(request)
    }
}