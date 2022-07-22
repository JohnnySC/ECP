package com.github.johnnysc.ecp.data.weather.cloud

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("timeline/{location}")
    suspend fun getWeather(
        @Path("location") location: String,
        @Query("key") key: String,
        @Query("unitGroup") units: String,
        @Query("include") include: String
    ): Weather.Base
}