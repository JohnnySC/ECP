package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.data.weather.CityData
import com.github.johnnysc.ecp.data.weather.WeatherData
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitle
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoConnection
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCity
import com.github.johnnysc.ecp.R
import org.junit.Assert.*
import org.junit.Test

class WeatherInteractorTest {

    fun first() {
        val manageResources = TestManageResource()
        val interactor = WeatherInteractor.Base(
            TestCityRepository(
                true,
                true,
                CityDomain.Mapper.BaseToCityData()
            ),
            TestWeatherRepository(true, CityDomain.Mapper.BaseToCoordinates()),
            CityData.Mapper.MapperToDomain(),
            CityDomain.Mapper.BaseToMessageUi(manageResources),
            WeatherData.Mapper.BaseToDomain(),
            WeatherDomain.Mapper.Base(manageResources),

        )
    }

    class TestCityRepository(
        private val hasDefaultCity: Boolean,
        private val isInternetAvailable: Boolean,
        private val cityDomainToCityData: CityDomain.Mapper<CityData>
    ) : CityRepository {
        var mockedDefaultCity: CityData = CityData.Base("Экибастуз", 123.234F, 124.231F)
        val mapOfCities = mutableMapOf<String, CityData.Base>()

        init {
            mapOfCities.put("Экибастуз", CityData.Base("Экибастуз", 123.234F, 124.231F))
            mapOfCities.put("Астана", CityData.Base("Астана", 131.234F, 111.231F))
            mapOfCities.put("Алмата", CityData.Base("Алмата", 141.234F, 110.231F))
            mapOfCities.put("Павлодар", CityData.Base("Павлодар", 141.234F, 109.231F))
            mapOfCities.put("Семипалатинск", CityData.Base("Семипалатинс", 91.234F, 21.231F))
        }

        override fun getDefaultCity(): CityData {
            if (hasDefaultCity)
                return mockedDefaultCity
            else
                throw ThereIsNoDefaultCity()
        }

        override fun getCityCoordinatesByName(cityName: String): CityData {
            if (isInternetAvailable) {
                if (mapOfCities.contains(cityName))
                    return mapOfCities[cityName]!!
                else
                    throw ThereIsNoCityWithSuchTitle()
            } else
                throw ThereIsNoConnection()
        }

        override fun saveDefaultCity(cityDomain: CityDomain) {
            mockedDefaultCity = cityDomain.map(cityDomainToCityData)

        }
    }

    class TestWeatherRepository(
        private val isInternetAvailable: Boolean,
        private val cityDomainToCoordinates: CityDomain.Mapper<Pair<Float, Float>>
    ) : WeatherRepository {
        private val mutableMap = mutableMapOf<Pair<Float, Float>, WeatherData>()

        init {
            mutableMap.apply {
                put(Pair(123.234F, 124.231F), WeatherData.Base(23F))
                put(Pair(131.234F, 111.231F), WeatherData.Base(21F))
                put(Pair(141.234F, 110.231F), WeatherData.Base(35F))
                put(Pair(141.234F, 109.231F), WeatherData.Base(21F))
                put(Pair(91.234F, 21.231F), WeatherData.Base(19F))
            }

        }

        override fun getWeatherInCity(cityDomain: CityDomain): WeatherData {
            if (isInternetAvailable) {
                return mutableMap[cityDomain.map(cityDomainToCoordinates)]!!
            } else throw ThereIsNoConnection()
        }
    }

    class TestManageResource : ManageResources {
        override fun string(id: Int): String {
            return when (id) {
                R.string.unknown_exception -> "Unknown exception"
                R.string.there_is_no_city_with_such_title -> "There is no city with such title"
                R.string.there_is_no_connection -> "There is no connection"
                R.string.weather_no_default_city -> "Error! Set default city using this command: My city is X, where X is the name of the city"
                R.string.weather_response->"Current temperature: "
                else -> "Something went wrong"
            }
        }
    }


}