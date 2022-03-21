package com.example.myartsycollection

import com.example.myartsycollection.MockUtil.mockArtwork
import com.example.myartsycollection.network.AppClient
import com.example.myartsycollection.network.AppService
import com.example.myartsycollection.persistence.ArtworkDao
import com.example.myartsycollection.repository.ArtworkRepository
import com.example.myartsycollection.repository.Repository
import com.example.myartsycollection.ui.main.ArtworkViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtworkViewModelTest {

    private lateinit var viewModel: ArtworkViewModel
    private lateinit var repository: ArtworkRepository
    private val appService: AppService = mock()
    private val appClient: AppClient = AppClient(appService)
    private val artworkDao: ArtworkDao = mock()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutinesRule = MainCoroutineRule()

    @Before
    fun setup() {
        repository = ArtworkRepository(appClient, artworkDao, Dispatchers.IO)
        viewModel = ArtworkViewModel(repository)
    }

    @Test
    fun fetchArtworksTest() = runBlocking {
        val mockData = mockArtwork()
        whenever(artworkDao.getArtworks(
            page_ = 0
        ))
    }
}