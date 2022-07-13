package com.github.johnnysc.ecp.data.weather

import com.github.johnnysc.ecp.domain.weather.CityDomain

interface CityData {

    fun <T> map(mapper: Mapper<T>): T

    class Base(private val title: String) : CityData {

        override fun <T> map(mapper: Mapper<T>) = mapper.map(title)
    }

    interface Mapper<T> {

        fun map(title: String): T

        class BaseToDomain : Mapper<CityDomain> {

            override fun map(title: String): CityDomain = CityDomain.Base(title)
        }

        class BaseToString : Mapper<String> {

            override fun map(title: String): String = title
        }
    }
}