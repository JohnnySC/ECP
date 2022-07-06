package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault.ParseDefaultWeather
import com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault.WeatherInCityNotMentioned
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class WeatherParsersTest {

    @Test
    fun `success parsings`() = runBlocking {
        val testManageResources = TestManageResources()
        val defaultParser = ParseDefaultWeather(testManageResources)
        val parseWeatherInCity
        Assert.assertEquals(
            WeatherInCityNotMentioned::class.simpleName,
            defaultParser.map("Some test string")!!::class.simpleName
        )
        Assert.assertEquals(
            WeatherInCityNotMentioned::class.simpleName,
            defaultParser.map("Some test string")!!::class.simpleName
        )
        Assert.assertEquals(
            WeatherInCityNotMentioned::class.simpleName,
            defaultParser.map("Some test string")!!::class.simpleName
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