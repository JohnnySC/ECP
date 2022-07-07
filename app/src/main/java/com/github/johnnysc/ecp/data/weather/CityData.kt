package com.github.johnnysc.ecp.data.weather

import com.github.johnnysc.ecp.domain.weather.CityDomain

interface CityData {
    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        private val title: String,
        private val longitude: Float = -1F,
        private val latitude: Float = -1F
    ) : CityData {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(title, longitude, latitude)

    }

    interface Mapper<T> {
        fun map(title: String, longitude: Float, latitude: Float): T

        class MapperToDomain : Mapper<CityDomain> {

            override fun map(title: String, longitude: Float, latitude: Float) =
                CityDomain.Base(title, longitude, latitude)

        }
    }
}
