package com.github.johnnysc.ecp.data.weather.cloud

import android.content.Context
import android.content.SharedPreferences
import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.core.ConvertFromJson
import com.github.johnnysc.ecp.core.ConvertRawResourceToPoJo
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
                                404,
                                "some content".toResponseBody("plain/text".toMediaTypeOrNull())
                            )
                        )
                    }
                }
            else
                throw UnknownHostException()
        }

        class FetchWeather(
            convertFromJson: ConvertFromJson<Weather.Base>,
            readRawResource: ReadRawResource
        ) : ConvertRawResourceToPoJo<Weather.Base, RemoteWeather>(readRawResource, convertFromJson) {
            override fun wrapResult(result: Weather.Base) = RemoteWeather.Base(result)
        }


        class WeatherResponseToken : TypeToken<Weather.Base>()

    }


    class MockData(private val context: Context) : ReadRawResource {
        override fun readText(id: Int) =
            context.resources.openRawResource(id).bufferedReader().readText()
    }

    interface InternetConnection {
        interface Write : InternetConnection {
            fun turnOnInternet()
            fun turnOffInternet()
        }

        interface Read : InternetConnection {
            fun isInternetAvailable(): Boolean
        }

        interface Mutable : Write, Read
        class Base(private val sharedPreferences: SharedPreferences) : Mutable {
            companion object {
                const val IS_INTERNET_AVAILABLE = "IS_INTERNET_AVAILABLE_KEY"
            }

            override fun turnOnInternet() {
                sharedPreferences.edit().putBoolean(IS_INTERNET_AVAILABLE, true).apply()
            }

            override fun turnOffInternet() {
                sharedPreferences.edit().putBoolean(IS_INTERNET_AVAILABLE, false).apply()
            }

            override fun isInternetAvailable() =
                sharedPreferences.getBoolean(IS_INTERNET_AVAILABLE, true)
        }
    }
}


