package com.example.test.home.ui.vm

import com.example.test.data.models.ClassifiedApiResponse
import com.example.test.data.models.ClassifiedItem
import com.example.test.data.remote.Result
import com.example.test.data.repos.DefaultMainRepo
import com.example.test.data.repos.MainRepo
import com.example.test.home.vm.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private lateinit var subject: HomeViewModel

    @RelaxedMockK private lateinit var mockedMainRepo: DefaultMainRepo
    private lateinit var testCoroutineDispatcher: TestCoroutineDispatcher

    @Before
    fun setup() {
        testCoroutineDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(testCoroutineDispatcher)

        MockKAnnotations.init(this)

        subject = HomeViewModel(
            mainRepo = mockedMainRepo
        )
    }


    @Test
    fun `fetches items list with success`() {
        coEvery { mockedMainRepo.getItems() } returns Result.Success(getSampleResponse())
        subject.fetchData()

        coEvery { mockedMainRepo.getItems() }
    }

    @Test
    fun `fetches items list with error`() {
        coEvery { mockedMainRepo.getItems() } returns Result.Error(400, null)
        subject.fetchData()

        coEvery { mockedMainRepo.getItems() }
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