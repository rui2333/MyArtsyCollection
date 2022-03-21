package com.example.myartsycollection.repository

import androidx.annotation.WorkerThread
import com.example.myartsycollection.network.AppClient
import com.example.myartsycollection.persistence.ArtworkDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ArtworkRepository @Inject constructor(
    private val appClient: AppClient,
    private val artworkDao: ArtworkDao,
    private val ioDispatcher: CoroutineDispatcher
): Repository {
    @WorkerThread
    fun fetchArtworks(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        var artworks = artworkDao.getArtworks(page)
        if (artworks.isEmpty()) {
            val response = appClient.fetchArtworks(page = page)
            response.suspendOnSuccess {
                artworks = data.content.artworks
                artworks.forEach { artwork -> artwork.page = page }
                artworkDao.insertArtworks(artworks)
                emit(artworkDao.getAllArtworks(page))
            }.onError {
                Timber.d(response.toString())
                Timber.d(errorBody.toString())
            }.onException {
                Timber.d(response.toString())
                Timber.d(exception.toString())
            }
        } else {
            emit(artworkDao.getAllArtworks(page))
        }
    }.onStart {
        onStart()
    }.onCompletion {
        onComplete()
    }.flowOn(
        ioDispatcher
    )
}