package com.example.myartsycollection.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myartsycollection.model.ArtworkInfo

@Dao
interface ArtworkInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtworkInfo(artworkInfo: ArtworkInfo)

    @Query("SELECT * FROM ArtworkInfo WHERE id = :id_")
    suspend fun getArtworkInfo(id_: String): ArtworkInfo?
}