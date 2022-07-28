package com.github.johnnysc.ecp.presentation.weather.commands.weatherincity

import com.github.johnnysc.ecp.domain.weather.WeatherInCityUseCase
import com.github.johnnysc.ecp.presentation.commands.HandleUseCase
import com.github.johnnysc.ecp.presentation.messages.MessageUI

data class WeatherInCity(private val city: String) : IsEmptyHandleUseCase<WeatherInCityUseCase> {

    override suspend fun handle(useCase: WeatherInCityUseCase): MessageUI = useCase.getWeather(city)
}

interface IsEmptyHandleUseCase<T> : IsEmpty, HandleUseCase<T> {

    class Empty<T> : IsEmptyHandleUseCase<T> {
        override suspend fun handle(useCase: T) = MessageUI.Empty()
        override fun isEmpty() = true
    }
}

interface IsEmpty {//todo move to core
    fun isEmpty(): Boolean = false
}