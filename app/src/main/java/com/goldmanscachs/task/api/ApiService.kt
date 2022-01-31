package com.goldmanscachs.task.api

import com.goldmanscachs.task.model.ResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /**
    Generated API key (wJ7makiq4OKbjswOdpjxDc88neaI9K3PeciFjN0A) from "https://api.nasa.gov/"
    NASA portal without that too just by passing "DEMO_KEY" getting same response so hardcoded"DEMO_KEY" here.
    In Production we should pass generated API key.
     */
    @GET("apod?api_key=DEMO_KEY")
    suspend fun getPictureOfDate(@Query(value = "date") date: String): ResponseData
}