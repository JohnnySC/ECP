package com.github.johnnysc.ecp.data.weather.cloud

import android.content.Context
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
            private const val WEATHER_API_KEY = "EDKUXQGPXGXE5Y6Y6UREJFVQA"

            private const val UNITS = "metric"

            private const val INCLUDE = "current"

        }
    }

    class Mock(
        private val rawResourceReader: RawResourceReader,
        private val gson: Gson,
        handleError: HandleError
    ) : WeatherCloudDataSource, CloudDataSource.Abstract(handleError) {
        private val typeToken = WeatherResponseToken()
        override suspend fun getWeather(cityName: String) = handle {
            if (InternetAvailability.getIsInternetAvailable())
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

            val result = rawResourceReader.readText(idOfResponse)

            return result
        }

        class WeatherResponseToken : TypeToken<Weather.Base>()

    }

    class MockData(private val context: Context) : RawResourceReader {
        override fun readText(id: Int) =
            context.resources.openRawResource(id).bufferedReader().readText()
    }

    object InternetAvailability {
        private var isInternetAvailable = false
        fun setInternetAvailable(isInternetAvailable: Boolean) {
            this.isInternetAvailable = isInternetAvailable
        }

        fun getIsInternetAvailable() = isInternetAvailable
    }
}

