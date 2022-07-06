package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.presentation.weather.commands.setdefault.ParseCity
import com.github.johnnysc.ecp.presentation.weather.commands.setdefault.SetDefaultCity
import com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault.ParseDefaultWeather
import com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault.WeatherInCityNotMentioned
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.ParseWeatherInCity
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.WeatherInCity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class WeatherParsersTest {

    //TODO write tests
    @Test
    fun `success parsings`() = runBlocking {
        val testManageResources = TestManageResources()
        val parseDefaultWeather = ParseDefaultWeather(testManageResources)
        val parseWeatherInCity = ParseWeatherInCity(testManageResources)
        val parseCity = ParseCity(testManageResources)
        Assert.assertEquals(
            WeatherInCityNotMentioned,
            parseDefaultWeather.map("Some test string")
        )
        Assert.assertEquals(
            WeatherInCity("Daytona Beach"),
            parseWeatherInCity.map("Some test string Daytona Beach")
        )
        Assert.assertEquals(
            SetDefaultCity(testManageResources, "Spooky town"),
            parseCity.map("Some test string")
        )
    }

//    private class TestInteractor(private val defaultCitySet: Boolean) : WeatherInteractor {
//
//        var defaultCity = "default one"
//
//        override suspend fun getWeather(city: String): String = "30"
//
//        override suspend fun getWeather(): String = "20"
//
//        override suspend fun setDefault(newCity: String) {
//            defaultCity = newCity
//        }
//    }

    private class TestManageResources : ManageResources {
        override fun string(id: Int): String = "Some test string"
    }
}