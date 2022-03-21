package com.example.myartsycollection.network

import com.example.myartsycollection.model.ArtworkInfo
import com.example.myartsycollection.model.ArtworksResponse
import com.skydoves.sandwich.ApiResponse
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class AppClient @Inject constructor(
    private val appService: AppService
) {
    suspend fun fetchArtworks(
        page: Int
    ): ApiResponse<ArtworksResponse> =
        appService.fetchArtworks(
            size = PAGING_SIZE,
            offset = page * PAGING_SIZE,
        )

    suspend fun fetchToken(): Response<ResponseBody> =
        appService.fetchToken()

    suspend fun fetchArtworkInfo(
        id: String
    ): ApiResponse<ArtworkInfo> =
        appService.fetchArtworkInfo(
            id = id
        )

    companion object {
        private const val PAGING_SIZE = 20
    }
}