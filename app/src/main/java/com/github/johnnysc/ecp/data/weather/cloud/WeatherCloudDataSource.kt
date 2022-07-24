package com.github.johnnysc.ecp.data.weather.cloud

import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.core.StringToObject
import com.github.johnnysc.ecp.core.ConvertRawResourceToPoJo
import com.github.johnnysc.ecp.core.InternetConnection
import com.github.johnnysc.ecp.core.ReadRawResource
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
        private val weatherCloud: WeatherService
    ) : WeatherCloudDataSource, CloudDataSource.Abstract(handleError) {

        override suspend fun getWeather(cityName: String): RemoteWeather = handle {
            RemoteWeather.Base(weatherCloud.getWeather(cityName, WEATHER_API_KEY, UNITS, INCLUDE))
        }

        companion object {
            private const val WEATHER_API_KEY = "Api key her"

            private const val UNITS = "metric"

            private const val INCLUDE = "current"

        }
    }

    class Mock(
        private val fetchWeather: ConvertRawResourceToPoJo<Weather.Base, RemoteWeather>,
        private val internetConnection: InternetConnection.Read,
        handleError: HandleError
    ) : WeatherCloudDataSource, CloudDataSource.Abstract(handleError) {

        override suspend fun getWeather(cityName: String) = handle {
            if (internetConnection.isInternetAvailable())
                when (cityName) {
                    "Ekibastuz" -> {
                        fetchWeather.map(R.raw.wether_succesfull_responce_for_ekibastuz)
                    }
                    "Almaty" -> {
                        fetchWeather.map(R.raw.weather_succesfull_responce_for_almaty)
                    }
                    else -> {
                        throw HttpException(
                            Response.error<ResponseBody>(
                                400,
                                "Invalid location found. Please check your location parameter:".toResponseBody(
                                    "plain/text".toMediaTypeOrNull()
                                )
                            )
                        )
                    }
                }
            else
                throw UnknownHostException()
        }

        class FetchWeather(
            stringToObject: StringToObject<Weather.Base>,
            readRawResource: ReadRawResource
        ) : ConvertRawResourceToPoJo<Weather.Base, RemoteWeather>(readRawResource, stringToObject) {
            override fun wrapResult(result: Weather.Base) = RemoteWeather.Base(result)
        }

        class WeatherResponseToken : TypeToken<Weather.Base>()
    }


}


