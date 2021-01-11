package com.example.test.home.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.test.data.models.ClassifiedItem
import com.example.test.data.repos.MainRepo
import com.example.test.di.MyPrefs
import com.example.test.utils.ErrorMessageParser
import kotlinx.coroutines.launch
import com.example.test.data.remote.Result.*

class HomeViewModel @ViewModelInject constructor(
    private val mainRepo: MainRepo,
    private val myPrefs: MyPrefs,
    private val errorMessageParser: ErrorMessageParser,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _classifiedItemsLiveData: MutableLiveData<List<ClassifiedItem>> = MutableLiveData()
    val classifiedItemsLiveData: LiveData<List<ClassifiedItem>> = _classifiedItemsLiveData
    private val _noItemFoundLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val noItemFoundLiveData: LiveData<Boolean> = _noItemFoundLiveData
    private val _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun fetchData() {
        viewModelScope.launch {
            _loadingLiveData.value = true
            when(val result = mainRepo.getItems()) {
                is Success -> {
                    _noItemFoundLiveData.value = false
                    _classifiedItemsLiveData.value = result.value.results
                    _loadingLiveData.value = false
                }
                is Error-> {
                    _noItemFoundLiveData.value = true
                    _loadingLiveData.value = false
                }
                else -> {
                    _noItemFoundLiveData.value = true
                    _loadingLiveData.value = false
                }
            }
        }
    }
}