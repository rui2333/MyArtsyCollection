package com.example.myartsycollection.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class ArtworkInfo(
    @field:Json(name="id") @PrimaryKey val id: String,
    @field:Json(name="title") val title: String,
    @field:Json(name="category") val category: String,
    @field:Json(name="medium") val medium: String,
    @field:Json(name="date") val date: String,
    @field:Json(name="sold") val sold: Boolean
) {
    fun getIdString(): String = String.format("#${id.substring(0, 4)}")
    fun getCategoryString(): String = String.format("Category: $category")
    fun getMediumString(): String = String.format("Medium: $medium")
    fun getDateString(): String = String.format("Date: $date")
    fun getSoldString(): String = String.format("Sold: ${if (sold) "Yes" else "No"}")
}