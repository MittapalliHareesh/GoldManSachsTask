package com.goldmanscachs.task.util


/**
 * It indicates whether API request is success or fail. Based on status API response data will be
 * populated.
 *
 * @param status indicates state. Either it will be SUCCESS or ERROR or LOADING.
 * @param data If API response was successfully using data field can fetch information.
 * @param message In case of error response using message field we can fetch error message.
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(status = Status.SUCCESS, data = data, message = null)
        }

        fun <T> error(data: T?, message: String): Resource<T> {
            return Resource(status = Status.ERROR, data = data, message = message)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(status = Status.LOADING, data = data, message = null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}