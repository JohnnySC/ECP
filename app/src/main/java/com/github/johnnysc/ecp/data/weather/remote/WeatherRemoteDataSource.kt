package com.github.johnnysc.ecp.data.weather.remote

import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError

interface WeatherRemoteDataSource {
    fun getWeather(cityName:String):RemoteWeather

    class Base(handleError: HandleError):WeatherRemoteDataSource,CloudDataSource.Abstract(handleError)
    {
        override fun getWeather(cityName: String): RemoteWeather {
            TODO("Not yet implemented")
        }
    }
}