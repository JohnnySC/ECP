package com.github.johnnysc.ecp.data.weather.cloud

import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.core.RawResourceReader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.security.PrivateKey

interface WeatherCloudDataSource {

    suspend fun getWeather(cityName: String): RemoteWeather

    class Base(
        handleError: HandleError,
        private val weatherCloud: WeatherCloud
    ) : WeatherCloudDataSource, CloudDataSource.Abstract(handleError) {

        override suspend fun getWeather(cityName: String): RemoteWeather = handle {
            RemoteWeather.Base(weatherCloud.getWeather(cityName, WEATHER_API_KEY))
        }

        companion object {
            private const val WEATHER_API_KEY = "PLACE YOUR API KEY HERE"
        }
    }

    class Mock(
        private val rawResourceReader: RawResourceReader,
        private val gson: Gson
    ) : WeatherCloudDataSource {
        private val typeToken = WeatherResponseToken()
        override suspend fun getWeather(cityName: String) = when (cityName) {
            "Екибастуз" -> {
                fetchWeather(R.raw.wether_succesfull_responce_for_ekibastuz)
            }
            "Алматы" -> {
                fetchWeather(R.raw.weather_succesfull_responce_for_almaty)
            }
            else -> {
                fetchWeather(R.raw.weather_invalid_loc_response)
            }
        }


        fun fetchWeather(idOfResponse: Int): RemoteWeather =
            gson.fromJson(getMockedResponseString(idOfResponse), typeToken.type)


        fun getMockedResponseString(idOfResponse: Int) =
            rawResourceReader.readText(idOfResponse)

        class WeatherResponseToken : TypeToken<RemoteWeather>()

    }
}

