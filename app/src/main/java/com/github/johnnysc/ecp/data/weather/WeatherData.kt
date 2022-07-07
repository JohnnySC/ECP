package com.github.johnnysc.ecp.data.weather

import com.github.johnnysc.ecp.domain.weather.WeatherDomain

interface WeatherData {

    fun <T> map(mapper: Mapper<T>): T

    data class Base(private val temperature: Float) : WeatherData {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(temperature)
    }

    interface Mapper<T> {
        fun map(temperature: Float): T
        class BaseToDomain : Mapper<WeatherDomain> {

            override fun map(temperature: Float) = WeatherDomain.Base(temperature)

        }
    }
}