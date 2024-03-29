package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.domain.weather.WeatherDefaultCityUseCase
import com.github.johnnysc.ecp.presentation.weather.commands.setdefault.ParseCity
import com.github.johnnysc.ecp.presentation.weather.commands.setdefault.SetDefaultCity
import com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault.ParseDefaultWeather
import com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault.WeatherInCityNotMentioned
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.IsEmptyHandleUseCase
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.ParseWeatherInCity
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.WeatherInCity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class WeatherParsersTest {

    @Test
    fun `success default weather`() = runBlocking {
        val testManageResources = TestManageResources("What's the weather like")
        val parser = ParseDefaultWeather(testManageResources)
        Assert.assertEquals(WeatherInCityNotMentioned, parser.map("what's The WeaThEr lIKE"))
    }

    @Test
    fun `failed default weather`() = runBlocking {
        val testManageResources = TestManageResources("What's the weather like")
        val parser = ParseDefaultWeather(testManageResources)
        Assert.assertEquals(true, parser.map("Alexa, tell some joke!").isEmpty())
    }

    @Test
    fun `success weather in city`() = runBlocking {
        val testManageResources = TestManageResources("What the weather is like in")
        val parser = ParseWeatherInCity(testManageResources)
        Assert.assertEquals(WeatherInCity("Miami"), parser.map("what the WeATHer is lIKE in Miami"))
    }

    @Test
    fun `failed weather in city`() = runBlocking {
        val testManageResources = TestManageResources("What the weather is like in")
        val parser = ParseWeatherInCity(testManageResources)
        Assert.assertEquals(true, parser.map("Whot da weader is like in Miame").isEmpty())
        Assert.assertEquals(true, parser.map("What the weather is like in     ").isEmpty())
    }

    @Test
    fun `success set city`() = runBlocking {
        val testManageResources = TestManageResources("My city is")
        val parser = ParseCity(testManageResources)
        Assert.assertEquals(SetDefaultCity("Miami"), parser.map("My city is Miami"))
    }

    @Test
    fun `failed set city`() = runBlocking {
        val testManageResources = TestManageResources("My city is")
        val parser = ParseCity(testManageResources)
        Assert.assertEquals(true, parser.map("Mai sity is Daytona beach").isEmpty())
        Assert.assertEquals(true, parser.map("My city is   ").isEmpty())
    }

    private class TestManageResources(private val value: String) : ManageResources {
        override fun string(id: Int): String = value
    }
}