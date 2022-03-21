package com.example.myartsycollection.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.example.myartsycollection.R
import com.example.myartsycollection.databinding.ActivityArtworkBinding
import com.example.myartsycollection.model.Artwork
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationCompat.onTransformationEndContainerApplyParams
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationEndContainer
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BindingActivity<ActivityArtworkBinding>(R.layout.activity_artwork) {
    @set:Inject
    internal lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory

    @get:VisibleForTesting
    internal val viewModel: DetailViewModel by viewModels {
        DetailViewModel.provideFactory(detailViewModelFactory, artworkId = artworkItem.id)
    }

    private val artworkItem: Artwork by bundleNonNull(EXTRA_ARTWORK)

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationEndContainerApplyParams(this)
        super.onCreate(savedInstanceState)
        binding {
            artwork = artworkItem
            vm = viewModel
        }
    }

    companion object {
        @VisibleForTesting
        internal const val EXTRA_ARTWORK = "EXTRA_ARTWORK"

        fun startActivity(transformationLayout: TransformationLayout, artwork: Artwork) =
            transformationLayout.context.intentOf<DetailActivity> {
                putExtra(EXTRA_ARTWORK to artwork)
                TransformationCompat.startActivity(transformationLayout, intent)
            }
    }
}