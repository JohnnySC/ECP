package com.github.johnnysc.ecp.domain.weather

interface SetDefaultCityUseCase {

    suspend fun setDefault(newCity: String)
}