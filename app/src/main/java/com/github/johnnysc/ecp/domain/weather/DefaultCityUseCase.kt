package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface DefaultCityUseCase {

    suspend fun setDefault(newCity: String): MessageUI
}