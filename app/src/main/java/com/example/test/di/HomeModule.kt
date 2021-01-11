package com.example.test.di

import com.example.test.data.remote.ApiService
import com.example.test.data.repos.DefaultMainRepo
import com.example.test.data.repos.MainRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object HomeModule {

    @Provides
    fun provideMainApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


}

@Module
@InstallIn(ActivityComponent::class)
abstract class HomeBindings {
    @Binds
    abstract fun bindMainRepo(impl: DefaultMainRepo): MainRepo

}