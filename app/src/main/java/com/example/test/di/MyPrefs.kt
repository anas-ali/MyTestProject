package com.example.test.di

import android.content.Context
import com.example.test.Configs.LAST_FETCH_TIME
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class MyPrefs  @Inject constructor(@ApplicationContext context : Context) {
    val prefs = context.getSharedPreferences("main_prefs", Context.MODE_PRIVATE)

    fun getLastFetchedTime(): Long {
        return prefs.getLong(LAST_FETCH_TIME, 0)
    }
    fun setLastFetchedTime(time: Long) {
        prefs.edit().putLong(LAST_FETCH_TIME, time).apply()
    }

}