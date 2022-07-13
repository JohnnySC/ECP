package com.github.johnnysc.ecp.data.weather.remote

import com.github.johnnysc.ecp.data.weather.remote.models.Weather
import com.github.johnnysc.ecp.domain.weather.WeatherDomain

interface RemoteWeather {

    fun <T> map(mapper: Mapper<T>): T

    fun isEmpty(): Boolean

    class Base(val weather: Weather) : RemoteWeather {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(weather.currentWeather!!.temp!!)

        override fun isEmpty(): Boolean = weather.currentWeather?.temp == null
    }

    interface Mapper<T> {

        fun map(temperature: Float): T

        class Base : Mapper<WeatherDomain> {

            override fun map(temperature: Float): WeatherDomain = WeatherDomain.Base(temperature)
        }
    }
}