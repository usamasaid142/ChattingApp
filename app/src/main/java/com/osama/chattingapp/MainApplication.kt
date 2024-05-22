package com.osama.chattingapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication:Application() {

    companion object {
        lateinit var application: MainApplication
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}