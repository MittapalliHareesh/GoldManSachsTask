package com.goldmanscachs.task.repository

import com.goldmanscachs.task.api.ApiService
import javax.inject.Inject

class AstronomyPictureRepository @Inject constructor(val apiService: ApiService) {

    suspend fun getPictureAPI(selectedDate: String) = apiService.getPictureOfDate(selectedDate)
}