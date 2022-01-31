package com.goldmanscachs.task.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.goldmanscachs.task.model.ResponseData
import com.goldmanscachs.task.repository.AstronomyPictureRepository
import com.goldmanscachs.task.util.Resource
import com.goldmanscachs.task.viewModels.AstronomyPictureViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.timeout
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@DelicateCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AstronomyPictureViewModelTest {

    private lateinit var astronomyPictureViewModel: AstronomyPictureViewModel

    @Mock
    private lateinit var astronomyPictureRepository: AstronomyPictureRepository

    @Mock
    private lateinit var astronomyPictureObserver: Observer<Resource<ResponseData>>

    @Mock
    private lateinit var responseData: ResponseData

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        runBlocking {
            whenever(astronomyPictureRepository.getPictureAPI("2022-01-31")).thenReturn(responseData)
        }
        astronomyPictureViewModel = AstronomyPictureViewModel(astronomyPictureRepository)
    }

    @Test
    fun `when getPictureAPI is called , then observer is updated with success`() {
        runBlocking {
            astronomyPictureViewModel.getPictureForSelectedDate("2022-01-31")
                .observeForever(astronomyPictureObserver)
            delay(10)
            verify(astronomyPictureObserver, timeout(50)).onChanged(Resource.loading(null))
            verify(astronomyPictureObserver, timeout(50)).onChanged(Resource.success(responseData))
        }
    }

    @Test
    fun `when getPictureAPI is called , then observer is updated without any response`() {
        runBlocking {
            astronomyPictureViewModel.getPictureForSelectedDate("2022-01-31")
                .observeForever(astronomyPictureObserver)
            delay(10)
            verify(astronomyPictureObserver, timeout(50)).onChanged(Resource.loading(null))
        }
    }
}