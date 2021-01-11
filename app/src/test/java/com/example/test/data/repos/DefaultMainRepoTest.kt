package com.example.test.data.repos

import com.example.test.data.models.ClassifiedApiResponse
import com.example.test.data.models.ClassifiedItem
import com.example.test.data.remote.ApiService
import com.example.test.data.remote.Result
import com.example.test.home.vm.HomeViewModel
import com.squareup.moshi.Moshi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DefaultMainRepoTest {
    private lateinit var subject: DefaultMainRepo

    @RelaxedMockK private lateinit var mockedApiService: ApiService
    @RelaxedMockK private lateinit var mockedMoshi: Moshi

    private lateinit var testCoroutineDispatcher: TestCoroutineDispatcher

    @Before
    fun setup() {
        testCoroutineDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(testCoroutineDispatcher)

        MockKAnnotations.init(this)

        subject = DefaultMainRepo(
            apiService = mockedApiService,
            moshi = mockedMoshi
        )
    }

    @Test
    fun `verifies call with success result`() = runBlocking {
        coEvery { subject.getItems() } returns Result.Success(getSampleResponse())

        subject.getItems()

        coVerify { mockedApiService.getItems() }
    }

    @Test
    fun `verifies call with failure result`() = runBlocking {
        coEvery { subject.getItems() } returns Result.Error(12, null)

        subject.getItems()

        coVerify { mockedApiService.getItems() }
    }

    private fun getSampleResponse() = ClassifiedApiResponse(
        results = listOf(
            ClassifiedItem(
                name = "name",
                created_at = "time",
                price = "20 AED",
                id = 1,
                imagesList = listOf("url")
            )
        )
    )
}