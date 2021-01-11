package com.example.test.vm

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel

abstract class ObservableViewModel : ViewModel(), Observable{

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        removeOnPropertyChangedCallback(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        addOnPropertyChangedCallback(callback)
    }
}