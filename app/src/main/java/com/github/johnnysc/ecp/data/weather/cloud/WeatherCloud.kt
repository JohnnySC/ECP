package com.github.johnnysc.ecp.data.weather.cloud

import com.github.johnnysc.ecp.data.weather.cloud.models.Weather
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherCloud {

    @GET("timeline/{location}")
    suspend fun getWeather(
        @Path("location") location: String,
        @Query("key") key: String
    ): Weather
}