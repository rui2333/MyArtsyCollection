package com.example.myartsycollection.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myartsycollection.model.Artwork

@Dao
interface ArtworkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtworks(artworks: List<Artwork>)

    @Query("SELECT * FROM Artwork WHERE page = :page_")
    suspend fun getArtworks(page_: Int): List<Artwork>

    @Query("SELECT * FROM Artwork WHERE page <= :page_")
    suspend fun getAllArtworks(page_: Int): List<Artwork>
}