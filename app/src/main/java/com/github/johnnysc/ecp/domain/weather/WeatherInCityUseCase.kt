package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface WeatherInCityUseCase {

    suspend fun getWeather(city: String): MessageUI
}