package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.data.weather.CityData
import com.github.johnnysc.ecp.data.weather.WeatherData
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitle
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoConnection
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCity
import com.github.johnnysc.ecp.domain.DomainException
import com.github.johnnysc.ecp.domain.ExceptionChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class WeatherInteractorTest {
    private val chain = ExceptionChain.ThereIsNoSuchCityChain(
        ExceptionChain.ThereIsNoDefaultCityChain(
            ExceptionChain.ThereIsNoConnectionChain(
                ExceptionChain.DefaultExceptionChain()
            )
        )
    )
    private val testManageResource = TestManageResource()
    private val weatherDataToMessageUIMapper =
        WeatherData.Mapper.BaseToWeatherUI(testManageResource)
    private val cityDataToMessageUIMapper = CityData.Mapper.BaseToMessageUI(testManageResource)
    private val domainExceptionToUIMapper = DomainException.Mapper.Base(testManageResource)
    private val city = "Астана"


    @Test
    fun `test interactor with no internet`() = runBlocking {
        val weatherRepository =
            TestWeatherRepository(isInternetAvailable = false, isDefaultCitySet = true)
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            weatherDataToMessageUIMapper,
            cityDataToMessageUIMapper, chain, domainExceptionToUIMapper
        )
        val expected = MessageUI.AiError(testManageResource.string(R.string.there_is_no_connection))

        val actualOne = weatherInteractor.getWeather()
        Assert.assertEquals(expected, actualOne)
        val actualTwo = weatherInteractor.getWeather(city)
        Assert.assertEquals(expected, actualTwo)
        val actualThree = weatherInteractor.setDefault(city)
        Assert.assertEquals(expected, actualThree)
    }

    @Test
    fun `test interactor with no default city`() = runBlocking {
        val weatherRepository =
            TestWeatherRepository(isInternetAvailable = true, isDefaultCitySet = false)
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            weatherDataToMessageUIMapper,
            cityDataToMessageUIMapper, chain, domainExceptionToUIMapper
        )

        val expected =
            MessageUI.AiError(testManageResource.string(R.string.weather_no_default_city))

        val actual = weatherInteractor.getWeather()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test interactor with default city`() = runBlocking {
        val weatherRepository =
            TestWeatherRepository(isInternetAvailable = true, isDefaultCitySet = true)
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            weatherDataToMessageUIMapper,
            cityDataToMessageUIMapper, chain, domainExceptionToUIMapper
        )

        val expected =
            MessageUI.Ai(testManageResource.string(R.string.weather_response).format(23F))

        val actual = weatherInteractor.getWeather()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test interactor with existed city`() = runBlocking {
        val weatherRepository =
            TestWeatherRepository(isInternetAvailable = true, isDefaultCitySet = true)
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            weatherDataToMessageUIMapper,
            cityDataToMessageUIMapper, chain, domainExceptionToUIMapper
        )

        val expected =
            MessageUI.Ai(testManageResource.string(R.string.weather_response).format(21F))

        val actual = weatherInteractor.getWeather(city)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test interactor with none existed city`() = runBlocking {
        val noneExistedCity="Ротрстан"
        val weatherRepository =
            TestWeatherRepository(isInternetAvailable = true, isDefaultCitySet = true)
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            weatherDataToMessageUIMapper,
            cityDataToMessageUIMapper, chain, domainExceptionToUIMapper
        )

        val expected =
            MessageUI.AiError(testManageResource.string(R.string.there_is_no_city_with_such_title))

        val actual = weatherInteractor.getWeather(noneExistedCity)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test interactor setDefaultCity`() = runBlocking {
        val weatherRepository =
            TestWeatherRepository(isInternetAvailable = true, isDefaultCitySet = true)
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            weatherDataToMessageUIMapper,
            cityDataToMessageUIMapper, chain, domainExceptionToUIMapper
        )

        val expected =
            MessageUI.Ai(testManageResource.string(R.string.set_weather_command_success))

        val actual = weatherInteractor.setDefault(city)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test interactor setDefaultCity with none existed city`() = runBlocking {
        val noneExistedCity = "Ротрстан"
        val weatherRepository =
            TestWeatherRepository(isInternetAvailable = true, isDefaultCitySet = true)
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            weatherDataToMessageUIMapper,
            cityDataToMessageUIMapper, chain, domainExceptionToUIMapper
        )

        val expected =
            MessageUI.AiError(testManageResource.string(R.string.there_is_no_city_with_such_title))

        val actual = weatherInteractor.setDefault(noneExistedCity)

        Assert.assertEquals(expected, actual)
    }


    class TestWeatherRepository(
        private val isInternetAvailable: Boolean,
        private val isDefaultCitySet: Boolean
    ) : WeatherRepository {
        private var defaultCity: String
        private val weatherDataMap = mutableMapOf<String, WeatherData>()

        init {
            defaultCity = if (isDefaultCitySet)
                "Экибастуз"
            else
                ""
            weatherDataMap.apply {
                put("Экибастуз", WeatherData.Base(23F))
                put("Астана", WeatherData.Base(21F))
                put("Алмата", WeatherData.Base(35F))
                put("Павлодар", WeatherData.Base(21F))
                put("Семипалатинск", WeatherData.Base(19F))
            }

        }

        override suspend fun getWeatherInCity(city: String): WeatherData {
            if (isInternetAvailable)
                if (weatherDataMap.contains(city))
                    return weatherDataMap[city]!!
                else
                    throw ThereIsNoCityWithSuchTitle()
            else
                throw ThereIsNoConnection()

        }

        override suspend fun getWeatherInDefaultCity(): WeatherData {
            if (isDefaultCitySet)
                if (isInternetAvailable)
                    return weatherDataMap[defaultCity]!!
                else throw ThereIsNoConnection()
            else throw ThereIsNoDefaultCity()
        }

        override suspend fun saveDefaultCity(newCity: String): CityData {
            val cities = weatherDataMap.keys
            if (isInternetAvailable)
                if (cities.contains(newCity))
                    return CityData.Base(newCity, 1f, 2f)
                else throw ThereIsNoCityWithSuchTitle()
            else throw ThereIsNoConnection()

        }


    }

    class TestManageResource : ManageResources {
        override fun string(id: Int): String {
            return when (id) {
                R.string.unknown_exception -> "Unknown exception"
                R.string.there_is_no_city_with_such_title -> "There is no city with such title"
                R.string.there_is_no_connection -> "There is no connection"
                R.string.weather_no_default_city -> "Error! Set default city using this command: My city is X, where X is the name of the city"
                R.string.weather_response -> "Current temperature: %1\$s\u2103 "
                R.string.set_weather_command_success->"Default city set!"
                else -> "Something went wrong"
            }
        }
    }


}