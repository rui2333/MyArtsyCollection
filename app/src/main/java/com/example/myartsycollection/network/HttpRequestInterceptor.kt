package com.example.myartsycollection.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HttpRequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .url(originalRequest.url)
            .addHeader("content-type", "application/json")
            .build()
        Timber.d(request.toString())
        return chain.proceed(request)
    }
}