package com.example.myartsycollection.initializer

import android.content.Context
import androidx.startup.Initializer
import com.example.myartsycollection.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("Timber initializer installed.")
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}