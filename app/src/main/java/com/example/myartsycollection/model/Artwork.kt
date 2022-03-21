package com.example.myartsycollection.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import timber.log.Timber

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
data class Artwork(
   var page: Int = 0,
   @field:Json(name="id") @PrimaryKey val id: String,
   @field:Json(name="title") val title: String,
   @field:Json(name="image_versions") val imageVersions: List<String>,
   @field:Json(name = "_links") val links: ArtworkLink
): Parcelable {
   fun getImageUrl(): String {
      val imageVersion = if (imageVersions.contains("square")) "square" else imageVersions[0]
      Timber.d(links.imageLink.ref.replace("{image_version}", imageVersion))
      return links.imageLink.ref.replace("{image_version}", imageVersion)
   }
}
@Parcelize
@JsonClass(generateAdapter = true)
data class ArtworkLink (
   @field:Json(name = "image") val imageLink: Link
) : Parcelable