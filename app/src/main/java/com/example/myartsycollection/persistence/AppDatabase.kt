package com.example.myartsycollection.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myartsycollection.model.Artwork

@Database(entities = [Artwork::class], version = 1, exportSchema = true)
@TypeConverters(value = [TypeResponseConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun artworkDao(): ArtworkDao
}