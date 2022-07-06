package com.github.johnnysc.ecp.domain.weather

interface DefaultCityUseCase {

    suspend fun setDefault(newCity: String)
}