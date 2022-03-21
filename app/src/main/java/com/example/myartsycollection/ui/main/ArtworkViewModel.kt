package com.example.myartsycollection.ui.main

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.example.myartsycollection.model.Artwork
import com.example.myartsycollection.repository.ArtworkRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArtworkViewModel @Inject constructor(
    private val repository: ArtworkRepository,
) : BindingViewModel(){

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    private val artworkFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    private val collectionFlow = artworkFetchingIndex.flatMapLatest { page ->
        repository.fetchArtworks(
            page = page,
            onStart = {isLoading = true},
            onComplete = {isLoading = false},
            onError = {toastMessage = it}
        )
    }

    @get:Bindable
    val collection: List<Artwork> by collectionFlow.asBindingProperty(viewModelScope, emptyList())

    init {
        Timber.d("Initializing ArtworkViewModel")
    }

    @MainThread
    fun fetchNextCollection() {
        if (!isLoading) {
            artworkFetchingIndex.value ++
        }
    }
}