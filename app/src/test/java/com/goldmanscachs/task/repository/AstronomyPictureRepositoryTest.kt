package com.goldmanscachs.task.repository

import com.goldmanscachs.task.api.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify


@RunWith(MockitoJUnitRunner::class)
class AstronomyPictureRepositoryTest {

    private lateinit var astronomyPictureRepository: AstronomyPictureRepository

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        astronomyPictureRepository = AstronomyPictureRepository(apiService)
    }

    @Test
    fun `whenever we call getPictureAPI, it should call getPictureAPI fun in ApiService`() {
        runBlocking {
            astronomyPictureRepository.getPictureAPI("2021-10-22")
            verify(apiService).getPictureOfDate("2021-10-22")
        }
    }
}