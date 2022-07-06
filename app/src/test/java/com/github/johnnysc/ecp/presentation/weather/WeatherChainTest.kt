package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class WeatherChainTest {

    @Test
    fun `success requests in default city`() = runBlocking {
        val testInteractor = TestInteractor(false)
        val testManageResources = TestManageResources()
        val weatherChain = WeatherChain(testInteractor, testManageResources)
        Assert.assertEquals(MessageUI.Ai("20"), weatherChain.handle())
    }

    private class TestInteractor(private val defaultCitySet: Boolean) : WeatherInteractor {

        var defaultCity = "default one"

        override suspend fun getWeather(city: String): String = "30"

        override suspend fun getWeather(): String = "20"

        override suspend fun setDefault(newCity: String) {
            defaultCity = newCity
        }
    }

    private class TestManageResources : ManageResources {
        override fun string(id: Int): String = "Some message"
    }
}