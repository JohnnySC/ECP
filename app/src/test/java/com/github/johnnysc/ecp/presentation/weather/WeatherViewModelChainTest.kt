package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.data.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import org.junit.Assert.*
import org.junit.Test

internal class WeatherViewModelChainTest {

    @Test
    fun `chotota m`() {
        val testInteractor = TestInteractor(false)
        val testCommands = listOf<Command<WeatherInteractor>>(TestCommand())
        val testManageResources = TestManageResources()
        val weatherViewModelChain = WeatherViewModelChain(testCommands, testInteractor, testManageResources)
    }

    private class TestInteractor(private val defaultCitySet: Boolean) : WeatherInteractor {
        override fun weatherInDefaultCity(): String = "30"

        override fun defaultCitySet(): Boolean = defaultCitySet
    }

    private class TestCommand : Command<WeatherInteractor> {
        override fun handle(useCase: WeatherInteractor): MessageUI = MessageUI.Ai(useCase.weatherInDefaultCity())

        override fun canHandle(message: String): Boolean = true
    }

    private class TestManageResources : ManageResources {
        override fun string(id: Int): String = "resource string"
    }
}