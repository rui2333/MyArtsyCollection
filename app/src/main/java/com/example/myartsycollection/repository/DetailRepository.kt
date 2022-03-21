package com.example.myartsycollection.repository

import androidx.annotation.WorkerThread
import com.example.myartsycollection.model.ArtworkInfo
import com.example.myartsycollection.network.AppClient
import com.example.myartsycollection.persistence.ArtworkInfoDao
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import timber.log.Timber
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val appClient: AppClient,
    private val artworkInfoDao: ArtworkInfoDao,
    private val ioDispatcher: CoroutineDispatcher
) : Repository{
    @WorkerThread
    fun fetchArtworkInfo(
        id: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ) = flow<ArtworkInfo?> {
        val artworkInfo = artworkInfoDao.getArtworkInfo(id_ = id)
        if (artworkInfo == null) {
            val response = appClient.fetchArtworkInfo(id = id)
            response.suspendOnSuccess {
                artworkInfoDao.insertArtworkInfo(data)
                emit(data)
            }.onError {
                // TODO
                Timber.d(errorBody.toString())
                onError(errorBody.toString())
            }.onException {
                Timber.d(exception.localizedMessage)
            }
        } else {
            emit(artworkInfo)
        }
    }.onCompletion {
        onComplete()
    }.flowOn(ioDispatcher)
}