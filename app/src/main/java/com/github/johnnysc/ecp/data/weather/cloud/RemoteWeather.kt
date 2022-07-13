package com.github.johnnysc.ecp.data.weather.cloud

import com.github.johnnysc.ecp.domain.weather.WeatherDomain

interface RemoteWeather {

    fun <T> map(mapper: Mapper<T>): T

    class Base(private val temperature: Float) : RemoteWeather {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(temperature)
    }

    interface Mapper<T> {

        fun map(temperature: Float): T

        class Base : Mapper<WeatherDomain> {

            override fun map(temperature: Float): WeatherDomain = WeatherDomain.Base(temperature)
        }
    }
}