package com.example.myartsycollection.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.example.myartsycollection.R
import com.example.myartsycollection.databinding.ActivityMainBinding
import com.example.myartsycollection.ui.adapter.ArtworkAdapter
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    @get:VisibleForTesting
    internal val viewModel: ArtworkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding {
            adapter = ArtworkAdapter()
            vm = viewModel
        }
    }
}