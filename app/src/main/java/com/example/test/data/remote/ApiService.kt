package com.example.test.data.remote

import com.example.test.data.models.ClassifiedApiResponse
import retrofit2.http.GET

// Some retrofit end point
interface ApiService {

    @GET("default/dynamodb-writer")
    suspend fun getItems(): ClassifiedApiResponse
}
