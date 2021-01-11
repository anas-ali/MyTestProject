package com.example.test.home.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.test.data.models.ClassifiedItem
import com.example.test.data.repos.MainRepo
import kotlinx.coroutines.launch
import com.example.test.data.remote.Result.*

class HomeViewModel @ViewModelInject constructor(
    private val mainRepo: MainRepo
) : ViewModel() {

    val _classifiedItemsLiveData: MutableLiveData<List<ClassifiedItem>> = MutableLiveData()
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