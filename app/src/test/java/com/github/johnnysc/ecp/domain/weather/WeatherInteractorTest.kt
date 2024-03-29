package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.domain.NoInternetConnectionException
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitleException
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCityException
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
    private val weatherDomainToMessageUIMapper =
        WeatherDomain.Mapper.BaseToMessage(testManageResource)
    private val cityDomainToMessageUIMapper = CityDomain.Mapper.Base(testManageResource)
    private val domainExceptionToUIMapper = DomainException.Mapper.Base(testManageResource)
    private val city = "Астана"

    @Test
    fun `all methods without internet get MessageUi AiError`() = runBlocking {
        val weatherRepository =
            TestWeatherRepository(isInternetAvailable = false, isDefaultCitySet = true)
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            weatherDomainToMessageUIMapper,
            cityDomainToMessageUIMapper, chain, domainExceptionToUIMapper
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
    fun `get weather in default city without default city get MessageUI AiError`() = runBlocking {
        val weatherRepository =
            TestWeatherRepository(isInternetAvailable = true, isDefaultCitySet = false)
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            weatherDomainToMessageUIMapper,
            cityDomainToMessageUIMapper, chain, domainExceptionToUIMapper
        )

        val expected =
            MessageUI.AiError(testManageResource.string(R.string.weather_no_default_city))

        val actual = weatherInteractor.getWeather()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get weather in default city with default city get MessageUi Ai with temperature`() = runBlocking {
        val weatherRepository =
            TestWeatherRepository(isInternetAvailable = true, isDefaultCitySet = true)
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            weatherDomainToMessageUIMapper,
            cityDomainToMessageUIMapper, chain, domainExceptionToUIMapper
        )

        val expected =
            MessageUI.Ai(testManageResource.string(R.string.weather_response).format(23F))

        val actual = weatherInteractor.getWeather()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get weather with existed city get MessageUi Ai with temperature in city`() = runBlocking {
        val weatherRepository =
            TestWeatherRepository(isInternetAvailable = true, isDefaultCitySet = true)
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            weatherDomainToMessageUIMapper,
            cityDomainToMessageUIMapper, chain, domainExceptionToUIMapper
        )

        val expected =
            MessageUI.Ai(testManageResource.string(R.string.weather_response).format(21F))

        val actual = weatherInteractor.getWeather(city)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get temperature with none existed city get MessageUi AiError with ThereIsNoCityWithSuchTitle exception`() = runBlocking {
        val noneExistedCity = "Ротрстан"
        val weatherRepository =
            TestWeatherRepository(isInternetAvailable = true, isDefaultCitySet = true)
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            weatherDomainToMessageUIMapper,
            cityDomainToMessageUIMapper, chain, domainExceptionToUIMapper
        )

        val expected =
            MessageUI.AiError(testManageResource.string(R.string.there_is_no_city_with_such_title))

        val actual = weatherInteractor.getWeather(noneExistedCity)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `setDefaultCity with existed city get MessageUi Ai with confirmation`() = runBlocking {
        val weatherRepository =
            TestWeatherRepository(isInternetAvailable = true, isDefaultCitySet = true)
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            weatherDomainToMessageUIMapper,
            cityDomainToMessageUIMapper, chain, domainExceptionToUIMapper
        )

        val expected =
            MessageUI.Ai(testManageResource.string(R.string.set_weather_command_success).format(city))
        println(expected)
        val actual = weatherInteractor.setDefault(city)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `setDefaultCity with none existed city get MessageUI AiError with ThereIsNoCityWithSuchTitle message `() = runBlocking {
        val noneExistedCity = "Ротрстан"
        val weatherRepository =
            TestWeatherRepository(isInternetAvailable = true, isDefaultCitySet = true)
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            weatherDomainToMessageUIMapper,
            cityDomainToMessageUIMapper, chain, domainExceptionToUIMapper
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
        private var defaultCity: String = if (isDefaultCitySet)
            "Экибастуз"
        else
            ""
        private val weatherDomainMap = mutableMapOf<String, WeatherDomain>()

        init {
            weatherDomainMap.apply {
                put("Экибастуз", WeatherDomain.Base(23F))
                put("Астана", WeatherDomain.Base(21F))
                put("Алмата", WeatherDomain.Base(35F))
                put("Павлодар", WeatherDomain.Base(21F))
                put("Семипалатинск", WeatherDomain.Base(19F))
            }
        }

        override suspend fun getWeatherInCity(city: String): WeatherDomain {
            checkInternetConnection()
            if (weatherDomainMap.contains(city))
                return weatherDomainMap[city]!!
            throw ThereIsNoCityWithSuchTitleException()
        }

        override suspend fun getWeatherInDefaultCity(): WeatherDomain {
            if (isDefaultCitySet) {
                checkInternetConnection()
                return weatherDomainMap[defaultCity]!!
            }
            throw ThereIsNoDefaultCityException()
        }

        override suspend fun saveDefaultCity(newCity: String) {
            val cities = weatherDomainMap.keys
            checkInternetConnection()
            if (!cities.contains(newCity))
                throw ThereIsNoCityWithSuchTitleException()
        }

        private fun checkInternetConnection() {
            if (isInternetAvailable)
                return
            throw NoInternetConnectionException()
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
                R.string.set_weather_command_success -> "Default city set to %1\$s!"
                else -> "Something went wrong"
            }
        }
    }
}