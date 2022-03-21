package com.example.myartsycollection.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myartsycollection.model.Artwork
import com.example.myartsycollection.model.ArtworkInfo

@Database(entities = [Artwork::class, ArtworkInfo::class], version = 1, exportSchema = true)
@TypeConverters(value = [TypeResponseConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun artworkDao(): ArtworkDao
    abstract fun artworkInfoDao(): ArtworkInfoDao
}