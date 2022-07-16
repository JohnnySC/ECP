package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.ecp.domain.ExceptionChain
import com.github.johnnysc.ecp.sl.ProvideExceptionChain

class ProvideWeatherExceptionChain:ProvideExceptionChain {
    override fun provideExceptionChain(): ExceptionChain {
        return ExceptionChain.ThereIsNoConnectionChain(ExceptionChain.ThereIsNoSuchCityChain(ExceptionChain.ThereIsNoDefaultCityChain(ExceptionChain.DefaultExceptionChain())))
    }
}