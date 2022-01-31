package com.goldmanscachs.task.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.goldmanscachs.task.repository.AstronomyPictureRepository
import com.goldmanscachs.task.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AstronomyPictureViewModel @Inject constructor(private val repository: AstronomyPictureRepository) :
    ViewModel() {

    fun getPictureForSelectedDate(selectedDate: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getPictureAPI(selectedDate)))
        } catch (httpException: HttpException) {
            emit(
                Resource.error(
                    data = null,
                    message = getErrorMessage(httpException.code())
                )
            )
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred while fetching data!"
                )
            )
        }
    }

    private fun getErrorMessage(httpCode: Int): String {
        return when (httpCode) {
            400 -> "Date must be between Jun 16, 1995 and Jan 29, 2022."
            401 -> "Accessing Unauthorised request"
            404 -> "Not Found.\n\nPlease validate the requested URL."
            500 -> "Server not found Error"
            else -> "Something went wrong!!"
        }
    }
}