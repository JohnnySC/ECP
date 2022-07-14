package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.ecp.presentation.weather.WeatherChain
import com.github.johnnysc.ecp.presentation.weather.WeatherViewModelChain
import com.github.johnnysc.ecp.sl.ProvideViewModelChain

class ProvideWeatherViewModelChain:ProvideViewModelChain<WeatherViewModelChain> {
    override fun viewModelChain()= WeatherViewModelChain(WeatherChain())
}