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
            CityData.Mapper.BaseToDomain(),
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.BaseToString()
        )
        Assert.assertEquals(WeatherDomain.Base(30F), repository.getWeatherInCity("Saint Paul"))
    }

    @Test
    fun `test success get weather in default city`() = runBlocking {
        val repository = BaseWeatherRepository(
            TestCloudDataSource(),
            TestCacheDataSource(true),
            CityData.Mapper.BaseToDomain(),
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.BaseToString()
        )
        Assert.assertEquals(WeatherDomain.Base(20F), repository.getWeatherInDefaultCity())
    }

    @Test
    fun `test success set default city`() = runBlocking {
        val repository = BaseWeatherRepository(
            TestCloudDataSource(),
            TestCacheDataSource(true),
            CityData.Mapper.BaseToDomain(),
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.BaseToString()
        )
        Assert.assertEquals(CityDomain.Base("Saint Paul"), repository.saveDefaultCity("Saint Paul"))
    }

    @Test
    fun `test failed get weather in city`(): Unit = runBlocking {
        val repository = BaseWeatherRepository(
            TestCloudDataSource(),
            TestCacheDataSource(false),
            CityData.Mapper.BaseToDomain(),
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.BaseToString()
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
            CityData.Mapper.BaseToDomain(),
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.BaseToString()
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
            CityData.Mapper.BaseToDomain(),
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.BaseToString()
        )
        Assert.assertThrows(ThereIsNoCityWithSuchTitleException::class.java) {
            runBlocking { repository.saveDefaultCity("Innsmouth") }
        }
    }

    class TestCloudDataSource : WeatherCloudDataSource {

        override suspend fun getWeather(cityName: String): RemoteWeather =
            RemoteWeather.Base(Weather(CurrentWeather(if (cityName == "Hawkins") 20F else if (cityName == "Saint Paul") 30F else null)))
    }

    class TestCacheDataSource(private val isDefaultSet: Boolean) : CityCacheDataSource {

        override fun getDefaultCity(): CityData {
            if (!isDefaultSet)
                throw ThereIsNoDefaultCityException()
            return CityData.Base("Hawkins")
        }

        override fun saveDefaultCity(newDefaultCity: String): CityData = CityData.Base(newDefaultCity)
    }
}