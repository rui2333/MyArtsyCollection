package com.example.myartsycollection.ui.detail

import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myartsycollection.model.ArtworkInfo
import com.example.myartsycollection.repository.DetailRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class DetailViewModel @AssistedInject constructor(
    detailRepository: DetailRepository,
    @Assisted private val artworkId: String
) : BindingViewModel() {
    @get:Bindable
    var isLoading: Boolean by bindingProperty(true)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    private val artworkInfoFlow: Flow<ArtworkInfo?> = detailRepository.fetchArtworkInfo(
        id = artworkId,
        onComplete = { isLoading = false },
        onError = { toastMessage = it },
    )

    @get:Bindable
    val artworkInfo: ArtworkInfo? by artworkInfoFlow.asBindingProperty(viewModelScope, null)

    init {
        Timber.d("init DetailViewModel")
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(artworkId: String): DetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            artworkId: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(artworkId) as T
            }
        }
    }
}