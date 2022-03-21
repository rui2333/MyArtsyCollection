package com.example.myartsycollection.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
data class ArtworksResponse (
    @field:Json(name = "total_count") val count: Int,
    @field:Json(name = "_links") val links: Links,
    @field:Json(name = "_embedded") val content: Embedded
)
@JsonClass(generateAdapter = true)
data class Links (
    @field:Json(name = "self") val self: Link,
    @field:Json(name = "next") val next: Link
)
@JsonClass(generateAdapter = true)
data class Embedded(
    @field:Json(name = "artworks") val artworks: List<Artwork>
)
@Parcelize
@JsonClass(generateAdapter = true)
data class Link (
    @field:Json(name = "href") val ref: String
) : Parcelable