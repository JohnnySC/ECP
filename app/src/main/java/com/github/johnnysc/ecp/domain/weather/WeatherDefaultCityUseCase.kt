package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface WeatherDefaultCityUseCase {

    suspend fun getWeather(): MessageUI
}