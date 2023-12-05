package com.veroanggra.sampleactivityembeding

import android.app.Application
import android.widget.Toast
import androidx.window.core.ExperimentalWindowApi
import androidx.window.embedding.SplitController

class SampleActivityEmbedingApplication : Application() {

    @OptIn(ExperimentalWindowApi::class)
    override fun onCreate() {
        super.onCreate()
        SplitController.initialize(this, R.xml.split_config)

        val splitController = SplitController.getInstance()
        if (!splitController.isSplitSupported()) {
            Toast.makeText(this, R.string.split_not_supported, Toast.LENGTH_LONG).show()
        } else {

        }
    }
}