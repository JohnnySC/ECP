package com.github.johnnysc.ecp.data.weather

import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitleException
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCityException
import com.github.johnnysc.ecp.data.weather.cache.CityCacheDataSource
import com.github.johnnysc.ecp.data.weather.cloud.RemoteWeather
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.github.johnnysc.ecp.data.weather.cloud.CurrentWeather
import com.github.johnnysc.ecp.data.weather.cloud.Weather
import com.github.johnnysc.ecp.domain.weather.CityDomain
import com.github.johnnysc.ecp.domain.weather.WeatherDomain
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class BaseWeatherRepositoryTest {

    @Test
    fun `test success get weather in city`() = runBlocking {
        val repository = BaseWeatherRepository(
            TestCloudDataSource(),
            TestCacheDataSource(true),
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.Base()
        )
        Assert.assertEquals(WeatherDomain.Base(30F), repository.getWeatherInCity("Saint Paul"))
    }

    @Test
    fun `test success get weather in default city`() = runBlocking {
        val repository = BaseWeatherRepository(
            TestCloudDataSource(),
            TestCacheDataSource(true),
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.Base()
        )
        Assert.assertEquals(WeatherDomain.Base(20F), repository.getWeatherInDefaultCity())
    }

    @Test
    fun `test success set default city`() = runBlocking {
        val cache = TestCacheDataSource(true)
        val repository = BaseWeatherRepository(
            TestCloudDataSource(),
            cache,
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.Base()
        )
        repository.saveDefaultCity("Saint Paul")
        Assert.assertEquals(cache.savedCity, "Saint Paul")
    }

    @Test
    fun `test failed get weather in city`(): Unit = runBlocking {
        val repository = BaseWeatherRepository(
            TestCloudDataSource(),
            TestCacheDataSource(false),
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.Base()
        )
        Assert.assertThrows(ThereIsNoCityWithSuchTitleException::class.java) {
            runBlocking { repository.getWeatherInCity("Innsmouth") }
        }
    }

    @Test
    fun `test failed get weather in default city`(): Unit = runBlocking {
        val repository = BaseWeatherRepository(
            TestCloudDataSource(),
            TestCacheDataSource(false),
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.Base()
        )
        Assert.assertThrows(ThereIsNoDefaultCityException::class.java) {
            runBlocking { repository.getWeatherInDefaultCity() }
        }
    }

    @Test
    fun `test failed set default city`(): Unit = runBlocking {
        val repository = BaseWeatherRepository(
            TestCloudDataSource(),
            TestCacheDataSource(false),
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.Base()
        )
        Assert.assertThrows(ThereIsNoCityWithSuchTitleException::class.java) {
            runBlocking { repository.saveDefaultCity("Innsmouth") }
        }
    }

    class TestCloudDataSource : WeatherCloudDataSource {

        override suspend fun getWeather(cityName: String): RemoteWeather =
            RemoteWeather.Base(Weather.Base(CurrentWeather.Base(if (cityName == "Hawkins") 20F else if (cityName == "Saint Paul") 30F else null)))
    }

    class TestCacheDataSource(private val isDefaultSet: Boolean) : CityCacheDataSource {

        var savedCity = "Hawkins"

        override fun getDefaultCity(): CityData {
            if (!isDefaultSet)
                throw ThereIsNoDefaultCityException()
            return CityData.Base("Hawkins")
        }

        override fun saveDefaultCity(newDefaultCity: String) {
            savedCity = newDefaultCity
        }
    }
}