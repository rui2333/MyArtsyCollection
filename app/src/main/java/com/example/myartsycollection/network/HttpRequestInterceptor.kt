package com.example.myartsycollection.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HttpRequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IiIsInN1YmplY3RfYXBwbGljYX" +
                "Rpb24iOiI2MWUxMDg5MWY2ZDI5NTAwMGU3YjQ5MmQiLCJleHAiOjE2NDc3Mjk3MDEsImlhdCI6MTY0NzEy" +
                "NDkwMSwiYXVkIjoiNjFlMTA4OTFmNmQyOTUwMDBlN2I0OTJkIiwiaXNzIjoiR3Jhdml0eSIsImp0aSI6" +
                "IjYyMmQyMWE1MTg3ZGRlMDAwYzRjZDQ2MSJ9.2llxw2B44OSMaVojT6yFGYvNFLHgEeeHuQUOZhWHxaw"
        val request = originalRequest.newBuilder()
            .url(originalRequest.url)
            .addHeader("content-type", "application/json")
            //.addHeader("X-XAPP-Token", "Bearer $token")
            .build()
        Timber.d(request.toString())
        return chain.proceed(request)
    }
}