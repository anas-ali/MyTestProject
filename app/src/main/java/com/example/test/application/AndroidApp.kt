package com.example.test.application

import android.app.Application
import com.squareup.picasso.Picasso
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initPicasso()

    }

    private fun initPicasso() {
        val picasso = Picasso.Builder(this).build()

        Picasso.setSingletonInstance(picasso)
    }
}