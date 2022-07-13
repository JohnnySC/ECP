package com.github.johnnysc.ecp.data.weather

import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitleException
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoDefaultCityException
import com.github.johnnysc.ecp.data.weather.local.CityLocalDataSource
import com.github.johnnysc.ecp.data.weather.remote.RemoteWeather
import com.github.johnnysc.ecp.data.weather.remote.WeatherRemoteDataSource
import com.github.johnnysc.ecp.data.weather.remote.models.CurrentWeather
import com.github.johnnysc.ecp.data.weather.remote.models.Weather
import com.github.johnnysc.ecp.domain.weather.CityDomain
import com.github.johnnysc.ecp.domain.weather.WeatherDomain
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class BaseWeatherRepositoryTest {

    @Test
    fun `test success repo calls`() = runBlocking {
        val repository = BaseWeatherRepository(
            TestRemoteDataSource(),
            TestLocalDataSource(true),
            CityData.Mapper.BaseToDomain(),
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.BaseToString()
        )
        Assert.assertEquals(WeatherDomain.Base(30F), repository.getWeatherInCity("Saint Paul"))
        Assert.assertEquals(WeatherDomain.Base(20F), repository.getWeatherInDefaultCity())
        Assert.assertEquals(CityDomain.Base("Saint Paul"), repository.saveDefaultCity("Saint Paul"))
    }

    @Test
    fun `test failed repo calls`(): Unit = runBlocking {
        val repository = BaseWeatherRepository(
            TestRemoteDataSource(),
            TestLocalDataSource(false),
            CityData.Mapper.BaseToDomain(),
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.BaseToString()
        )
        Assert.assertThrows(ThereIsNoCityWithSuchTitleException::class.java) {
            runBlocking { repository.getWeatherInCity("Innsmouth") }
        }
        Assert.assertThrows(ThereIsNoDefaultCityException::class.java) {
            runBlocking { repository.getWeatherInDefaultCity() }
        }
    }

    class TestRemoteDataSource : WeatherRemoteDataSource {

        override suspend fun getWeather(cityName: String): RemoteWeather =
            RemoteWeather.Base(Weather(CurrentWeather(if (cityName == "Hawkins") 20F else if (cityName == "Saint Paul") 30F else null)))
    }

    class TestLocalDataSource(private val isDefaultSet: Boolean) : CityLocalDataSource {

        override fun getDefaultCity(): CityData {
            if (!isDefaultSet)
                throw ThereIsNoDefaultCityException()
            return CityData.Base("Hawkins")
        }

        override fun saveDefaultCity(newDefCity: String): CityData = CityData.Base(newDefCity)
    }

    class TestHandleError : HandleError {

        override fun handle(error: Exception): Exception = error
    }
}