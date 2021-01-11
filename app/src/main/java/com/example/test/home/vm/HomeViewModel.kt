package com.example.test.home.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.test.data.repos.MainRepo
import com.example.test.di.MyPrefs
import com.example.test.utils.ErrorMessageParser

class HomeViewModel @ViewModelInject constructor(
    private val mainRepo: MainRepo,
    private val myPrefs: MyPrefs,
    private val errorMessageParser: ErrorMessageParser,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun init() {

    }

    fun fetchData(forceFetch : Boolean) {

    }
}