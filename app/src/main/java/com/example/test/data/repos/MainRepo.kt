package com.example.test.data.repos

import com.example.test.data.models.ClassifiedApiResponse
import com.example.test.data.remote.Result
import com.example.test.data.remote.ApiService
import com.squareup.moshi.Moshi
import javax.inject.Inject

class DefaultMainRepo @Inject constructor(moshi: Moshi, private val apiService: ApiService) :
    DefaultRepo(moshi),
    MainRepo {

    override suspend fun getItems(): Result<ClassifiedApiResponse> {
        return safeApiCall {
            apiService.getItems()
        }
    }
}

interface MainRepo {

    suspend fun getItems(): Result<ClassifiedApiResponse>
}


