package com.github.johnnysc.ecp.presentation.weather.commands

import com.github.johnnysc.ecp.domain.weather.SetDefaultCityUseCase

class SetDefaultCity(private val setDefaultCityUseCase: SetDefaultCityUseCase) {

    suspend fun setCity(city: String) = setDefaultCityUseCase.setDefault(city)
}