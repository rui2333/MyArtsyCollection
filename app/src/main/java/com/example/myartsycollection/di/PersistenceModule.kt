package com.example.myartsycollection.di

import android.app.Application
import androidx.room.Room
import com.example.myartsycollection.persistence.AppDatabase
import com.example.myartsycollection.persistence.ArtworkDao
import com.example.myartsycollection.persistence.ArtworkInfoDao
import com.example.myartsycollection.persistence.TypeResponseConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
        typeResponseConverter: TypeResponseConverter
    ): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "Artworks.db")
            .fallbackToDestructiveMigration()
            .addTypeConverter(typeResponseConverter)
            .build()
    }

    @Provides
    @Singleton
    fun provideArtworkDao(appDatabase: AppDatabase): ArtworkDao {
        return appDatabase.artworkDao()
    }

    @Provides
    @Singleton
    fun provideArtworkInfoDao(appDatabase: AppDatabase) : ArtworkInfoDao {
        return appDatabase.artworkInfoDao()
    }

    @Provides
    @Singleton
    fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter {
        return TypeResponseConverter(moshi)
    }
}