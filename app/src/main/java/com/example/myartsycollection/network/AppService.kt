package com.example.myartsycollection.network

import com.example.myartsycollection.model.ArtworkInfo
import com.example.myartsycollection.model.ArtworksResponse
import com.skydoves.sandwich.ApiResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface AppService {
    @GET("artworks")
    @Headers("X-Xapp-Token: eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IiIsInN1YmplY3RfYXBwbGljYXRpb24iOiI2MWUxMDg5MWY2ZDI5NTAwMGU3YjQ5MmQiLCJleHAiOjE2NDg0MDcyODQsImlhdCI6MTY0NzgwMjQ4NCwiYXVkIjoiNjFlMTA4OTFmNmQyOTUwMDBlN2I0OTJkIiwiaXNzIjoiR3Jhdml0eSIsImp0aSI6IjYyMzc3ODc0MGFhMDc3MDAwZWMyNWJhYiJ9.TQC3nGd9ROsS8DOghbra9wRCobxwO354SwzaIVRoUpM")
    suspend fun fetchArtworks(
        @Query("total_count") count: Int = 1,
        @Query("size") size: Int = 20,
        @Query("offset") offset: Int = 0,
    ): ApiResponse<ArtworksResponse>

    @POST("tokens/xapp_token")
    suspend fun fetchToken(
        @Query("client_id") id: String = "9f68989c3fbac55c0f1a",
        @Query("client_secret") secret: String = "1ad9408d0ffaa33f6b811e0d2da43326"
    ): Response<ResponseBody>

    @GET("artworks/{id}")
    @Headers("X-Xapp-Token: eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IiIsInN1YmplY3RfYXBwbGljYXRpb24iOiI2MWUxMDg5MWY2ZDI5NTAwMGU3YjQ5MmQiLCJleHAiOjE2NDg0MDcyODQsImlhdCI6MTY0NzgwMjQ4NCwiYXVkIjoiNjFlMTA4OTFmNmQyOTUwMDBlN2I0OTJkIiwiaXNzIjoiR3Jhdml0eSIsImp0aSI6IjYyMzc3ODc0MGFhMDc3MDAwZWMyNWJhYiJ9.TQC3nGd9ROsS8DOghbra9wRCobxwO354SwzaIVRoUpM")
    suspend fun fetchArtworkInfo (@Path("id") id:String): ApiResponse<ArtworkInfo>
}