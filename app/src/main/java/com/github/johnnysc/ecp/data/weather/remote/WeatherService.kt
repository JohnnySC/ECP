package com.github.johnnysc.ecp.data.weather.remote

import com.github.johnnysc.ecp.data.weather.remote.models.Weather
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("timeline/{location}")
    fun getWeather(
        @Path("location") location: String,
        @Query("key") key: String
    ): Weather
}