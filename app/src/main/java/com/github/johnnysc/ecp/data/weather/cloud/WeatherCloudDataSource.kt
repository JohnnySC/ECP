package com.github.johnnysc.ecp.data.weather.cloud

import android.content.Context
import android.content.SharedPreferences
import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.core.RawResourceReader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

interface WeatherCloudDataSource {

    suspend fun getWeather(cityName: String): RemoteWeather

    class Base(
        handleError: HandleError,
        private val weatherCloud: WeatherCloud
    ) : WeatherCloudDataSource, CloudDataSource.Abstract(handleError) {

        override suspend fun getWeather(cityName: String): RemoteWeather = handle {
            RemoteWeather.Base(weatherCloud.getWeather(cityName, WEATHER_API_KEY, UNITS, INCLUDE))
        }

        companion object {
            private const val WEATHER_API_KEY = "Api key here"

            private const val UNITS = "metric"

            private const val INCLUDE = "current"

        }
    }

    class Mock(
        private val rawResourceReader: RawResourceReader,
        private val gson: Gson,
        private val internetConnection: InternetConnection.Read,
        handleError: HandleError
    ) : WeatherCloudDataSource, CloudDataSource.Abstract(handleError) {
        private val typeToken = WeatherResponseToken()
        override suspend fun getWeather(cityName: String) = handle {
            if (internetConnection.isInternetAvailable())
                when (cityName) {
                    "Екибастуз" -> {
                        fetchWeather(R.raw.wether_succesfull_responce_for_ekibastuz)
                    }
                    "Алматы" -> {
                        fetchWeather(R.raw.weather_succesfull_responce_for_almaty)
                    }
                    else -> {
                        throw HttpException(
                            Response.error<ResponseBody>(
                                404,
                                "some content".toResponseBody("plain/text".toMediaTypeOrNull())
                            )
                        )
                    }
                }
            else
                throw UnknownHostException()
        }

        private fun fetchWeather(idOfResponse: Int): RemoteWeather =
            RemoteWeather.Base(
                gson.fromJson(getMockedResponseString(idOfResponse), typeToken.type)
            )

        private fun getMockedResponseString(idOfResponse: Int): String {

            return rawResourceReader.readText(idOfResponse)
        }

        class WeatherResponseToken : TypeToken<Weather.Base>()
    }

    class MockData(private val context: Context) : RawResourceReader {
        override fun readText(id: Int) =
            context.resources.openRawResource(id).bufferedReader().readText()
    }

    interface InternetConnection {
        interface Write:InternetConnection{
            fun changeInternetAvailable(isAvailable: Boolean)
        }

        interface Read:InternetConnection{
            fun isInternetAvailable(): Boolean
        }

        interface Mutable:Write,Read
        class Base(private val sharedPreferences: SharedPreferences) : Mutable {
            private val IS_INTERNET_AVAILABLE = "IS_INTERNET_AVAILABLE_KEY"
            override fun isInternetAvailable() =
                sharedPreferences.getBoolean(IS_INTERNET_AVAILABLE, true)


            override fun changeInternetAvailable(isAvailable: Boolean) {
                sharedPreferences.edit().putBoolean(IS_INTERNET_AVAILABLE, isAvailable).apply()
            }
        }

    }
}

