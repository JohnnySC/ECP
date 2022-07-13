package com.github.johnnysc.ecp.data.weather

import com.github.johnnysc.ecp.domain.weather.CityDomain

interface CityData {

    fun <M> map(mapper: Mapper<M>): M

    class Base(private val title: String) : CityData {

        override fun <M> map(mapper: Mapper<M>) = mapper.map(title)
    }

    interface Mapper<M> {

        fun map(title: String): M

        class BaseToDomain : Mapper<CityDomain> {

            override fun map(title: String): CityDomain = CityDomain.Base(title)
        }

        class BaseToString : Mapper<String> {

            override fun map(title: String): String = title
        }
    }
}