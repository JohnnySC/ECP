package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.domain.DomainException
import com.github.johnnysc.ecp.domain.weather.CityDomain
import com.github.johnnysc.ecp.domain.weather.WeatherDomain
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.sl.ProvideExceptionChain

interface ProvideWeatherInteractor {
    fun provideWeatherInteractor(): WeatherInteractor

    class Base(
        private val provideWeatherRepository: ProvideWeatherRepository,
        private val manageResources: ManageResources,
        private val provideExceptionChain: ProvideExceptionChain
    ) : ProvideWeatherInteractor {
        override fun provideWeatherInteractor() = WeatherInteractor.Base(
            provideWeatherRepository.provideWeatherRepository(),
            WeatherDomain.Mapper.BaseToMessage(manageResources),
            CityDomain.Mapper.Base(manageResources),
            provideExceptionChain.provideExceptionChain(),
            DomainException.Mapper.Base(manageResources)
        )
    }
}